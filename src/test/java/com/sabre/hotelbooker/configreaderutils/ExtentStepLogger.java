package com.sabre.hotelbooker.configreaderutils;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

import java.util.concurrent.ConcurrentHashMap;
import com.sabre.hotelbooker.loggerutils.LoggerUtil;

public class ExtentStepLogger implements EventListener {
    // Map to store the current step text for each thread
    public static final ConcurrentHashMap<Long, String> stepTextMap = new ConcurrentHashMap<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::handleStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleStepFinished);
    }

    private void handleStepStarted(TestStepStarted event) {
        TestStep testStep = event.getTestStep();
        if (testStep instanceof PickleStepTestStep) {
            PickleStepTestStep pickleStep = (PickleStepTestStep) testStep;
            if (pickleStep != null && pickleStep.getStep() != null && pickleStep.getStep().getKeyword() != null && pickleStep.getStep().getText() != null) {
                String stepText = pickleStep.getStep().getKeyword() + pickleStep.getStep().getText();
                stepTextMap.put(Thread.currentThread().getId(), stepText);
                LoggerUtil.logDebug("[ExtentStepLogger] Set step text for thread {}: {}", Thread.currentThread().getId(), stepText);
            }
        }
    }

    private void handleStepFinished(TestStepFinished event) {
        // Do not remove step text here; removal will be handled after node creation in Hooks.afterStep
        LoggerUtil.logDebug("[ExtentStepLogger] (handleStepFinished) Step text NOT removed here for thread {}", Thread.currentThread().getId());
    }
}
