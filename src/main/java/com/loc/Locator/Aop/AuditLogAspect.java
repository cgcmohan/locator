package com.loc.Locator.Aop;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loc.Locator.Entity.AuditLog;
import com.loc.Locator.Repository.AuditlogRepository;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuditLogAspect {

	@Autowired
	private AuditlogRepository auditLogRepository;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("execution(* com.loc.Locator.controller.LocationController.*(..))")
    public void requestLogger() {
    	
    }
    
    @Before("requestLogger()")
    public void logRequest(JoinPoint joinPoint) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String contentType = request.getContentType();
        BufferedReader reader = request.getReader();
       // String requestContent = extractRequestContent(contentType, reader);
        

        // Serialize request data as JSON
        String requestJson = objectMapper.writeValueAsString(new RequestLog(method, uri, queryString, "Mohan"));

     // Create an audit log entry and save it to the database
        AuditLog logEntry = new AuditLog();
        logEntry.setTimestamp(LocalDateTime.now());
        logEntry.setMethod(method);
        logEntry.setRequest(requestJson);
        logEntry.setResponse(""); // Initialize as empty, to be filled in logResponse

        auditLogRepository.save(logEntry);
    }

    @AfterReturning(pointcut = "requestLogger()",
                    returning = "response")
    public void logResponse(JoinPoint joinPoint, Object response) throws JsonProcessingException {
        // Serialize response data as JSON
        String responseJson = objectMapper.writeValueAsString(response);

        // Retrieve the most recent audit log entry
        AuditLog logEntry = findMostRecentAuditLog();

        if (logEntry != null) {
            // Update the response field in the audit log entry
            logEntry.setResponse(responseJson);
            auditLogRepository.save(logEntry);
        }
    }

    private AuditLog findMostRecentAuditLog() {
        // Implement a method to retrieve the most recent audit log entry here
        // You can use other criteria, such as timestamps, to identify the most recent entry
        // This implementation depends on your specific database and application logic
        return auditLogRepository.findTopByOrderByTimestampDesc();
    }
    private String extractRequestContent(String contentType,  BufferedReader reader) throws IOException {
        // Extract and serialize request parameters, body, or other relevant data
        // You can implement this method based on your requirements
        // For example, you can read the request body as a string
        // For multipart/form-data, you may need to handle it differently
    	if(contentType != null && contentType.contains("application/json")) {
    		StringBuilder content = new StringBuilder();
    	    String line;
    	    while((line = reader.readLine())!=null) {
    	    	content.append(line);
    	    }

    	}
    	if(contentType != null)
        return contentType.toString();
    	else 
    		return null;
    }

    // Define a class to represent request logs
    private static class RequestLog {
        private String method;
        private String uri;
        private String queryString;
        private String content;

        public RequestLog(String method, String uri, String queryString, String content) {
            this.method = method;
            this.uri = uri;
            this.queryString = queryString;
            this.content = content;
        }

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

		public String getQueryString() {
			return queryString;
		}

		public void setQueryString(String queryString) {
			this.queryString = queryString;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

        
        // Getters and setters
        
    }
}
