package com.sabre.hotelbooker.stepdefinitions;
import com.sabre.hotelbooker.configreaderutils.ExtentStepLogger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sabre.hotelbooker.extentreportutils.ExtentReportManager;
import com.sabre.hotelbooker.extentreportutils.TestExecutionState;
import com.sabre.hotelbooker.loggerutils.LogDirectoryManager;
import com.sabre.hotelbooker.loggerutils.LoggerUtil;
import com.sabre.hotelbooker.playwrightbase.PlayWrightBaseTest;
import io.cucumber.java.AfterAll;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Status;

import java.util.concurrent.atomic.AtomicInteger;


import java.nio.file.*;
import java.io.IOException;

public class Hooks {
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final java.util.Map<String, ExtentTest> featureParentMap = new java.util.concurrent.ConcurrentHashMap<>();
    private static final java.util.Set<String> featureSet = java.util.Collections.synchronizedSet(new java.util.HashSet<>());
    private static final java.util.Set<String> scenarioSet = java.util.Collections.synchronizedSet(new java.util.HashSet<>());
    private static final AtomicInteger stepCount = new AtomicInteger(0);
    private static final AtomicInteger passCount = new AtomicInteger(0);
    private static final AtomicInteger failCount = new AtomicInteger(0);
    private static final AtomicInteger skipCount = new AtomicInteger(0);
    private String getFeatureTitle(Scenario scenario) {
        try {
            // Cucumber's Scenario object does not expose the feature title directly, so parse from the URI
            java.nio.file.Path featurePath = java.nio.file.Paths.get(scenario.getUri());
            java.util.List<String> lines = java.nio.file.Files.readAllLines(featurePath);
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("Feature:")) {
                    return line;
                }
            }
        } catch (Exception e) {
            return getFeatureName(scenario);
        }
        return getFeatureName(scenario);
    }

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        if (extent == null) {
            extent = ExtentReportManager.getInstance();
        }
        String baseScenarioName = scenario.getName();
        String scenarioId = scenario.getId();
        String exampleSuffix = "";
        if (scenarioId != null && scenarioId.contains(";")) {
            String[] parts = scenarioId.split(";");
            if (parts.length > 1) {
                exampleSuffix = " [Example: " + parts[1] + "]";
            }
        }
        String scenarioName = baseScenarioName + exampleSuffix;

        String featureTitle = getFeatureTitle(scenario); // e.g., 'Feature: ...' from the .feature file
        LoggerUtil.logDebug("[DEBUG] Creating ExtentTest for scenario: '{}' in feature: '{}'", scenarioName, featureTitle);
        LoggerUtil.setTestContext(scenarioName, featureTitle);
        LoggerUtil.logInfo("Starting test scenario: {}", scenarioName);

        // Get or create the parent node for the feature (using the Gherkin Feature title)
        ExtentTest parentNode = featureParentMap.computeIfAbsent(featureTitle, fn -> {
            ExtentTest parent = extent.createTest(fn);
            LoggerUtil.logDebug("[DEBUG] Created parent ExtentTest for feature: {}", fn);
            return parent;
        });

        // Create a child node for the scenario under the feature parent
        ExtentTest scenarioNode = parentNode.createNode(scenarioName);

                        // Track unique features and scenarios
                        featureSet.add(featureTitle);
                        scenarioSet.add(scenarioName);
        // Add tags to the scenario node for filtering
        if (scenario.getSourceTagNames() != null && !scenario.getSourceTagNames().isEmpty()) {
            for (String tag : scenario.getSourceTagNames()) {
                String cleanTag = tag.replace("@", "");
                scenarioNode.assignCategory(cleanTag);
                LoggerUtil.logDebug("[DEBUG] Assigned tag/category: {}", cleanTag);
            }
        }

        test.set(scenarioNode);
        TestExecutionState.resetCriticalFailureState();
        LoggerUtil.logDebug("[DEBUG] ExtentTest set in ThreadLocal: {}", (test.get() != null));
        PlayWrightBaseTest.initialize();
    }

    /**
     * After each step, log the step to Extent report as a child node with correct status
     */
    @AfterStep
    public void afterStep(io.cucumber.java.Scenario scenario) {
        String stepName = ExtentStepLogger.stepTextMap.get(Thread.currentThread().getId());
        LoggerUtil.logDebug("[Hooks.afterStep] Retrieved step text for thread {}: {}", Thread.currentThread().getId(), stepName);
        if (stepName == null || stepName.isEmpty()) {
            stepName = "[Step]";
        }
        ExtentStepLogger.stepTextMap.remove(Thread.currentThread().getId());
        String coloredStep = stepName.replaceAll("^(Given|When|Then|And)", "<span style='color:#0074D9;font-weight:bold;'>$1</span>");

        ExtentTest currentTest = test.get();
        if (currentTest == null) return;

        Status status = scenario.getStatus();
        stepCount.incrementAndGet();
        switch (status) {
            case PASSED:
                passCount.incrementAndGet();
                currentTest.pass(coloredStep);
                break;
            case FAILED:
                failCount.incrementAndGet();
                currentTest.fail(coloredStep);
                break;
            case SKIPPED:
                skipCount.incrementAndGet();
                currentTest.skip(coloredStep);
                break;
            case PENDING:
                currentTest.warning(coloredStep);
                break;
            default:
                currentTest.info(coloredStep);
        }
    }


    /**
     * Central critical failure handling - runs before each step
     * This eliminates the need for TestExecutionState checks in individual step definitions
     */
    @Before(order = 10)
    public void checkCriticalFailureBeforeStep() {
        if (TestExecutionState.hasCriticalFailureOccurred()) {
            LoggerUtil.logWarn("CRITICAL FAILURE DETECTED - Aborting step execution");
            throw new org.opentest4j.TestAbortedException("SKIPPED: Previous critical failure occurred - remaining steps aborted");
        }
    }
    
    private String getFeatureName(Scenario scenario) {
        try {
            String uri = scenario.getUri().toString();
            String fileName = uri.substring(uri.lastIndexOf("/") + 1);
            return fileName.replace(".feature", "");
        } catch (Exception e) {
            return "UnknownFeature";
        }
    }

    @After
    public void tearDownScenario(Scenario scenario) {
        LoggerUtil.logInfo("Completing test scenario");

        // Capture screenshot if test failed
        if (scenario.isFailed()) {
            PlayWrightBaseTest.captureScreenshotWithInfo(PlayWrightBaseTest.page, "Test case Failed", Hooks.test.get());
        }
        LoggerUtil.clearTestContext();
        LoggerUtil.cleanup();
        PlayWrightBaseTest.tearDown();
        test.remove();
        TestExecutionState.cleanup();
    }
    @AfterAll
    public static void tearDownAll() {
        if (extent != null) {
            extent.flush();
        }
        LogDirectoryManager.closeLogFiles();
        LoggerUtil.logInfo("Test execution completed - all log files closed");
    }
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (extent != null) {
                extent.flush();
                LoggerUtil.getLogger(Hooks.class).info("[HOOK] ExtentReports flushed in shutdown hook.");
            }
        }));
    }

    

    /**
     * Log custom info to the current ExtentTest node. Safe to call from any class (including PageObjects).
     */
    public static void Extent_INFO(String message) {
        ExtentTest t = test.get();
        if (t != null) {
            t.info(message);

        }
    }
    public static void Extent_WARNING(String message) {
        ExtentTest t = test.get();
        if (t != null) {
            t.warning(message);

        }
    }
    public static void Extent_FAIL(String message) {
        ExtentTest t = test.get();
        if (t != null) {
            t.fail(message);

        }
    }
}
