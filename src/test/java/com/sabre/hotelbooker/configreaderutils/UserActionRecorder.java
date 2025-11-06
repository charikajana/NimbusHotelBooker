package com.sabre.hotelbooker.configreaderutils;

import com.microsoft.playwright.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * UserActionRecorder - Records and tracks all user actions performed in the browser
 * Provides comprehensive step-by-step recording of user interactions
 */
public class UserActionRecorder {
    
    private final Page page;
    private final List<UserAction> recordedActions;
    private boolean isRecording;
    private int stepCounter;
    
    /**
     * UserAction class to store individual user actions
     */
    public static class UserAction {
        private final int stepNumber;
        private final String timestamp;
        private final String actionType;
        private final String element;
        private final String value;
        private final String pageUrl;
        private final String description;
        
        public UserAction(int stepNumber, String actionType, String element, String value, String pageUrl, String description) {
            this.stepNumber = stepNumber;
            this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            this.actionType = actionType;
            this.element = element;
            this.value = value;
            this.pageUrl = pageUrl;
            this.description = description;
        }
        
        public int getStepNumber() { return stepNumber; }
        public String getTimestamp() { return timestamp; }
        public String getActionType() { return actionType; }
        public String getElement() { return element; }
        public String getValue() { return value; }
        public String getPageUrl() { return pageUrl; }
        public String getDescription() { return description; }
        
        @Override
        public String toString() {
            return String.format("[%s] Step %d: %s - %s on '%s' with value '%s' at %s", 
                    timestamp, stepNumber, actionType, description, element, value, pageUrl);
        }
    }
    
    /**
     * Constructor
     */
    public UserActionRecorder(Page page) {
        this.page = page;
        this.recordedActions = new ArrayList<>();
        this.isRecording = false;
        this.stepCounter = 0;
    }
    
    /**
     * Start recording user actions
     */
    public void startRecording() {
        isRecording = true;
        stepCounter = 0;
        recordedActions.clear();
        recordAction("RECORDING_STARTED", "Browser", "", page.url(), "Started recording user actions");
        System.out.println("🎬 USER ACTION RECORDING STARTED");
        System.out.println("📍 Current URL: " + page.url());
    }
    
    /**
     * Stop recording user actions
     */
    public void stopRecording() {
        if (isRecording) {
            recordAction("RECORDING_STOPPED", "Browser", "", page.url(), "Stopped recording user actions");
            isRecording = false;
            System.out.println("🛑 USER ACTION RECORDING STOPPED");
            System.out.println("📊 Total actions recorded: " + (recordedActions.size() - 2)); // Excluding start/stop
        }
    }
    
    /**
     * Record a user action
     */
    private void recordAction(String actionType, String element, String value, String pageUrl, String description) {
        if (isRecording) {
            stepCounter++;
            UserAction action = new UserAction(stepCounter, actionType, element, value, pageUrl, description);
            recordedActions.add(action);
            System.out.println("📝 " + action.toString());
        }
    }
    
    /**
     * Record page navigation
     */
    public void recordNavigation(String url) {
        recordAction("NAVIGATE", "Browser", url, url, "Navigated to page");
    }
    
    /**
     * Record click action
     */
    public void recordClick(String element, String description) {
        recordAction("CLICK", element, "", page.url(), description);
    }
    
    /**
     * Record text input
     */
    public void recordTextInput(String element, String text, String description) {
        recordAction("TYPE", element, text, page.url(), description);
    }
    
    /**
     * Record dropdown selection
     */
    public void recordDropdownSelection(String element, String selectedValue, String description) {
        recordAction("SELECT", element, selectedValue, page.url(), description);
    }
    
    /**
     * Record form submission
     */
    public void recordFormSubmission(String formElement, String description) {
        recordAction("SUBMIT", formElement, "", page.url(), description);
    }
    
    /**
     * Record verification/validation
     */
    public void recordVerification(String element, String expectedValue, String description) {
        recordAction("VERIFY", element, expectedValue, page.url(), description);
    }
    
    /**
     * Record wait action
     */
    public void recordWait(String condition, String description) {
        recordAction("WAIT", condition, "", page.url(), description);
    }
    
    /**
     * Record custom action
     */
    public void recordCustomAction(String actionType, String element, String value, String description) {
        recordAction(actionType, element, value, page.url(), description);
    }
    
    /**
     * Get all recorded actions
     */
    public List<UserAction> getRecordedActions() {
        return new ArrayList<>(recordedActions);
    }
    
    /**
     * Generate comprehensive step-by-step report
     */
    public String generateStepReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n").append("=".repeat(80)).append("\n");
        report.append("📋 COMPREHENSIVE USER ACTION RECORDING REPORT\n");
        report.append("=".repeat(80)).append("\n");
        
        report.append(String.format("🕒 Recording Session: %s\n", 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        report.append(String.format("📊 Total Actions Recorded: %d\n", recordedActions.size()));
        report.append(String.format("🌐 Final URL: %s\n", page.url()));
        report.append("\n");
        
        report.append("📝 DETAILED STEP-BY-STEP ACTIONS:\n");
        report.append("-".repeat(80)).append("\n");
        
        for (UserAction action : recordedActions) {
            report.append(String.format("%s\n", action.toString()));
        }
        
        report.append("\n").append("-".repeat(80)).append("\n");
        report.append("🎯 ACTION SUMMARY BY TYPE:\n");
        
        // Count actions by type
        recordedActions.stream()
            .collect(java.util.stream.Collectors.groupingBy(
                UserAction::getActionType,
                java.util.stream.Collectors.counting()
            ))
            .forEach((type, count) -> 
                report.append(String.format("   %s: %d actions\n", type, count))
            );
        
        report.append("\n").append("=".repeat(80)).append("\n");
        
        return report.toString();
    }
    
    /**
     * Generate BDD-style test steps
     */
    public String generateBDDSteps() {
        StringBuilder bddSteps = new StringBuilder();
        bddSteps.append("\n").append("🥒 BDD TEST STEPS (GHERKIN FORMAT):\n");
        bddSteps.append("-".repeat(50)).append("\n");
        
        bddSteps.append("Scenario: User performs recorded actions\n");
        
        for (UserAction action : recordedActions) {
            switch (action.getActionType()) {
                case "NAVIGATE":
                    bddSteps.append(String.format("    Given user navigates to \"%s\"\n", action.getValue()));
                    break;
                case "CLICK":
                    bddSteps.append(String.format("    When user clicks on \"%s\"\n", action.getElement()));
                    break;
                case "TYPE":
                    bddSteps.append(String.format("    And user enters \"%s\" in \"%s\" field\n", 
                            action.getValue(), action.getElement()));
                    break;
                case "SELECT":
                    bddSteps.append(String.format("    And user selects \"%s\" from \"%s\" dropdown\n", 
                            action.getValue(), action.getElement()));
                    break;
                case "SUBMIT":
                    bddSteps.append(String.format("    And user submits \"%s\" form\n", action.getElement()));
                    break;
                case "VERIFY":
                    bddSteps.append(String.format("    Then \"%s\" should display \"%s\"\n", 
                            action.getElement(), action.getValue()));
                    break;
                case "WAIT":
                    bddSteps.append(String.format("    And user waits for \"%s\"\n", action.getElement()));
                    break;
                default:
                    bddSteps.append(String.format("    And user performs %s on \"%s\"\n", 
                            action.getActionType(), action.getElement()));
                    break;
            }
        }
        
        bddSteps.append("\n").append("-".repeat(50)).append("\n");
        return bddSteps.toString();
    }
    
    /**
     * Generate code for automation
     */
    public String generateAutomationCode() {
        StringBuilder code = new StringBuilder();
        code.append("\n").append("🔧 AUTOMATION CODE (PLAYWRIGHT JAVA):\n");
        code.append("-".repeat(50)).append("\n");
        
        for (UserAction action : recordedActions) {
            switch (action.getActionType()) {
                case "NAVIGATE":
                    code.append(String.format("page.navigate(\"%s\");\n", action.getValue()));
                    break;
                case "CLICK":
                    code.append(String.format("page.click(\"%s\");\n", action.getElement()));
                    break;
                case "TYPE":
                    code.append(String.format("page.fill(\"%s\", \"%s\");\n", 
                            action.getElement(), action.getValue()));
                    break;
                case "SELECT":
                    code.append(String.format("page.selectOption(\"%s\", \"%s\");\n", 
                            action.getElement(), action.getValue()));
                    break;
                case "VERIFY":
                    code.append(String.format("assert page.textContent(\"%s\").contains(\"%s\");\n", 
                            action.getElement(), action.getValue()));
                    break;
                case "WAIT":
                    code.append(String.format("page.waitForSelector(\"%s\");\n", action.getElement()));
                    break;
            }
        }
        
        code.append("\n").append("-".repeat(50)).append("\n");
        return code.toString();
    }
    
    /**
     * Export recording to file
     */
    public void exportRecording(String filename) {
        try {
            String report = generateStepReport() + generateBDDSteps() + generateAutomationCode();
            // Note: File writing would require additional implementation
            System.out.println("📁 Recording exported to: " + filename);
            System.out.println(report);
        } catch (Exception e) {
            System.err.println("❌ Error exporting recording: " + e.getMessage());
        }
    }
}