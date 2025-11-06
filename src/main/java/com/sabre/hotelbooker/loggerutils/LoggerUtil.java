package com.sabre.hotelbooker.loggerutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Logger Utility class for HotelBooker Automation Framework
 * Provides centralized logging functionality with context support
 */
public class LoggerUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    
    // Thread-safe logger cache
    private static final ThreadLocal<Logger> loggerCache = new ThreadLocal<>();
    
    /**
     * Get logger for the calling class
     * @return Logger instance
     */
    public static Logger getLogger() {
        Logger cachedLogger = loggerCache.get();
        if (cachedLogger == null) {
            // Get the calling class name
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String className = stackTrace[2].getClassName();
            cachedLogger = LoggerFactory.getLogger(className);
            loggerCache.set(cachedLogger);
        }
        return cachedLogger;
    }
    
    /**
     * Get logger for specific class
     * @param clazz Class for which logger is needed
     * @return Logger instance
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
    
    /**
     * Set test context information for logging
     * @param scenarioName Current scenario name
     * @param featureName Current feature name
     */
    public static void setTestContext(String scenarioName, String featureName) {
        MDC.put("scenario", scenarioName);
        MDC.put("feature", featureName);
        logger.info("=== Starting Test Scenario: {} in Feature: {} ===", scenarioName, featureName);
    }
    
    /**
     * Set step context information for logging
     * @param stepName Current step name
     */
    public static void setStepContext(String stepName) {
        MDC.put("step", stepName);
    }
    
    /**
     * Clear test context
     */
    public static void clearTestContext() {
        MDC.remove("scenario");
        MDC.remove("feature");
        MDC.remove("step");
        logger.info("=== Test Scenario Completed ===");
    }
    
    /**
     * Log test step execution
     * @param stepDescription Step description
     */
    public static void logStep(String stepDescription) {
        Logger stepLogger = LoggerFactory.getLogger("TEST_STEPS");
        stepLogger.info("STEP: {}", stepDescription);
        
        // Also write to custom log file in reports directory
        LogDirectoryManager.writeToTestExecution("STEP: " + stepDescription);
    }
    
    /**
     * Log page action
     * @param action Action being performed
     * @param element Element on which action is performed
     */
    public static void logPageAction(String action, String element) {
        Logger pageLogger = LoggerFactory.getLogger("PAGE_ACTIONS");
        pageLogger.debug("ACTION: {} on element: {}", action, element);
        
        // Also write to custom log file in reports directory
        LogDirectoryManager.writeToPageActions("ACTION: " + action + " on element: " + element);
    }
    
    /**
     * Log validation result
     * @param validation Validation description
     * @param result Validation result
     */
    public static void logValidation(String validation, boolean result) {
        Logger validationLogger = LoggerFactory.getLogger("VALIDATIONS");
        if (result) {
            validationLogger.info("VALIDATION PASSED: {}", validation);
            LogDirectoryManager.writeToTestExecution("VALIDATION PASSED: " + validation);
        } else {
            validationLogger.error("VALIDATION FAILED: {}", validation);
            LogDirectoryManager.writeToErrors("VALIDATION FAILED: " + validation);
        }
    }
    
    /**
     * Log critical failure
     * @param message Failure message
     * @param throwable Exception details
     */
    public static void logCriticalFailure(String message, Throwable throwable) {
        Logger criticalLogger = LoggerFactory.getLogger("CRITICAL_FAILURES");
        criticalLogger.error("CRITICAL FAILURE: {}", message, throwable);
        
        // Also write to custom log file in reports directory
        LogDirectoryManager.writeToErrors("CRITICAL FAILURE: " + message + " - " + 
                                          (throwable != null ? throwable.getMessage() : "No exception details"));
    }
    
    /**
     * Log test data
     * @param dataType Type of test data
     * @param data Actual data
     */
    public static void logTestData(String dataType, String data) {
        Logger dataLogger = LoggerFactory.getLogger("TEST_DATA");
        dataLogger.debug("TEST DATA - {}: {}", dataType, data);
    }
    
    /**
     * Log performance metrics
     * @param operation Operation name
     * @param duration Duration in milliseconds
     */
    public static void logPerformance(String operation, long duration) {
        Logger perfLogger = LoggerFactory.getLogger("PERFORMANCE");
        perfLogger.info("PERFORMANCE - {}: {} ms", operation, duration);
    }
    
    /**
     * Log general information message
     * @param message Information message
     * @param args Optional arguments for message formatting
     */
    public static void logInfo(String message, Object... args) {
        Logger infoLogger = LoggerFactory.getLogger("GENERAL_INFO");
        infoLogger.info(message, args);
        
        // Format message for file logging
        String formattedMessage = args.length > 0 ? 
            String.format(message.replace("{}", "%s"), args) : message;
        LogDirectoryManager.writeToTestExecution("INFO: " + formattedMessage);
    }
    
    /**
     * Log debug message
     * @param message Debug message
     * @param args Optional arguments for message formatting
     */
    public static void logDebug(String message, Object... args) {
        Logger debugLogger = LoggerFactory.getLogger("DEBUG");
        debugLogger.debug(message, args);
        
        // Format message for file logging
        String formattedMessage = args.length > 0 ? 
            String.format(message.replace("{}", "%s"), args) : message;
        LogDirectoryManager.writeToTestExecution("DEBUG: " + formattedMessage);
    }
    
    /**
     * Log error message
     * @param message Error message
     * @param args Optional arguments for message formatting
     */
    public static void logError(String message, Object... args) {
        Logger errorLogger = LoggerFactory.getLogger("ERRORS");
        errorLogger.error(message, args);
        
        // Format message for file logging
        String formattedMessage = args.length > 0 ? 
            String.format(message.replace("{}", "%s"), args) : message;
        LogDirectoryManager.writeToErrors("ERROR: " + formattedMessage);
    }
    
    /**
     * Log error message with throwable
     * @param message Error message
     * @param throwable Exception/error details
     * @param args Optional arguments for message formatting
     */
    public static void logError(String message, Throwable throwable, Object... args) {
        Logger errorLogger = LoggerFactory.getLogger("ERRORS");
        errorLogger.error(message, throwable, args);
        
        // Format message for file logging
        String formattedMessage = args.length > 0 ? 
            String.format(message.replace("{}", "%s"), args) : message;
        LogDirectoryManager.writeToErrors("ERROR: " + formattedMessage + " - " + 
                                          (throwable != null ? throwable.getMessage() : "No exception details"));
    }
    
    /**
     * Log warning message
     * @param message Warning message
     * @param args Optional arguments for message formatting
     */
    public static void logWarn(String message, Object... args) {
        Logger warnLogger = LoggerFactory.getLogger("WARNINGS");
        warnLogger.warn(message, args);
        
        // Format message for file logging
        String formattedMessage = args.length > 0 ? 
            String.format(message.replace("{}", "%s"), args) : message;
        LogDirectoryManager.writeToErrors("WARN: " + formattedMessage);
    }

    /**
     * Clean up thread local logger cache
     */
    public static void cleanup() {
        loggerCache.remove();
        clearTestContext();
    }
}