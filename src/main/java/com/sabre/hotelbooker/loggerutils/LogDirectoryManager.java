package com.sabre.hotelbooker.loggerutils;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class to manage dynamic log directory creation
 * This ensures log files are created in the same directory as ExtentReports
 */
public class LogDirectoryManager {
    
    private static String currentLogDirectory;
    private static FileWriter testExecutionLogger;
    private static FileWriter pageActionsLogger;
    private static FileWriter errorLogger;
    
    /**
     * Set the log directory dynamically and create log files
     * This should be called when the report directory is created
     */
    public static void setLogDirectory(String reportDirectory) {
        // Place all logs in a 'logs' subfolder inside the report directory
        String logsDir = reportDirectory + File.separator + "logs";
        currentLogDirectory = logsDir;

        // Update logback configuration with the new directory
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.putProperty("DYNAMIC_LOG_DIR", logsDir);

        // Ensure the logs directory exists
        new File(logsDir).mkdirs();

        // Create log files in the logs directory
        try {
            testExecutionLogger = new FileWriter(new File(logsDir, "test-execution.log"), true);
            pageActionsLogger = new FileWriter(new File(logsDir, "page-actions.log"), true);
            errorLogger = new FileWriter(new File(logsDir, "errors.log"), true);

            // Write initial headers
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            testExecutionLogger.write("=== TEST EXECUTION LOG STARTED AT " + timestamp + " ===\n");
            pageActionsLogger.write("=== PAGE ACTIONS LOG STARTED AT " + timestamp + " ===\n");
            errorLogger.write("=== ERROR LOG STARTED AT " + timestamp + " ===\n");

            testExecutionLogger.flush();
            pageActionsLogger.flush();
            errorLogger.flush();

        } catch (IOException e) {
            System.err.println("Failed to create log files: " + e.getMessage());
        }

        System.out.println("Log directory set to: " + logsDir);
    }
    
    /**
     * Write a message to the test execution log
     */
    public static void writeToTestExecution(String message) {
        writeToLogFile(testExecutionLogger, message);
    }
    
    /**
     * Write a message to the page actions log
     */
    public static void writeToPageActions(String message) {
        writeToLogFile(pageActionsLogger, message);
    }
    
    /**
     * Write a message to the error log
     */
    public static void writeToErrors(String message) {
        writeToLogFile(errorLogger, message);
    }
    
    /**
     * Helper method to write to log files with timestamp
     */
    private static void writeToLogFile(FileWriter writer, String message) {
        if (writer != null) {
            try {
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
                writer.write(timestamp + " - " + message + "\n");
                writer.flush();
            } catch (IOException e) {
                System.err.println("Failed to write to log file: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get the current log directory
     */
    public static String getCurrentLogDirectory() {
    return currentLogDirectory;
    }
    
    /**
     * Create a default log directory if none has been set
     */
    public static String createDefaultLogDirectory() {
        if (currentLogDirectory == null) {
            String date = new SimpleDateFormat("ddMMMyy").format(new Date()).toUpperCase();
            String time = new SimpleDateFormat("HHmmss").format(new Date());
            String logsDir = "reports" + File.separator + date + File.separator + time + File.separator + "logs";
            currentLogDirectory = logsDir;
        }
        return currentLogDirectory;
    }
    
    /**
     * Initialize logging with dynamic directory
     * Call this early in test execution
     */
    public static void initializeLogging(String reportDirectory) {
        setLogDirectory(reportDirectory);
        
        // Log initialization message
        System.out.println("Logging initialized for directory: " + reportDirectory);
        System.out.println("Log files will be created:");
        System.out.println("   • " + reportDirectory + "/test-execution.log (All test logs)");
        System.out.println("   • " + reportDirectory + "/page-actions.log (Page object logs)");
        System.out.println("   • " + reportDirectory + "/errors.log (Error logs only)");
        
        // Write initial test start message
        writeToTestExecution("Test execution started");
        writeToPageActions("Page actions logging started");
        writeToErrors("Error logging started");
    }
    
    /**
     * Close all log files
     */
    public static void closeLogFiles() {
        try {
            if (testExecutionLogger != null) {
                testExecutionLogger.write("=== TEST EXECUTION LOG ENDED ===\n");
                testExecutionLogger.close();
            }
            if (pageActionsLogger != null) {
                pageActionsLogger.write("=== PAGE ACTIONS LOG ENDED ===\n");
                pageActionsLogger.close();
            }
            if (errorLogger != null) {
                errorLogger.write("=== ERROR LOG ENDED ===\n");
                errorLogger.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close log files: " + e.getMessage());
        }
    }
}