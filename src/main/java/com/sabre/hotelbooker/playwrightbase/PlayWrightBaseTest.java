
package com.sabre.hotelbooker.playwrightbase;

import com.microsoft.playwright.*;
import com.sabre.hotelbooker.extentreportutils.ExtentReportManager;
import com.sabre.hotelbooker.configreaderutils.ConfigReader;


public class PlayWrightBaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    public static Page page;
    
    public static void initialize() {
        String browserName = ConfigReader.getProperty("browserName");
        playwright = Playwright.create();
        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = browser.newContext();
                page = context.newPage();
                // Fallback: maximize viewport for Firefox
                try {
                    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                    page.setViewportSize(screenSize.width, screenSize.height);
                } catch (Exception e) {
                    page.setViewportSize(1910, 1050);
                }
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = browser.newContext();
                page = context.newPage();
                // Fallback: maximize viewport for Webkit
                try {
                    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                    page.setViewportSize(screenSize.width, screenSize.height);
                } catch (Exception e) {
                    page.setViewportSize(1920, 1080);
                }
                break;
            default:
                int width = 1620;
                int height = 1080;
                try {
                    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                    width = screenSize.width;
                    height = screenSize.height;
                } catch (Exception e) { }
                browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(java.util.Arrays.asList("--window-position=0,0", "--window-size=" + width + "," + height))
                );
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
                page = context.newPage();
        }
    }

    public static void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public static void captureScreenshotWithInfo(com.microsoft.playwright.Page page, String info, com.aventstack.extentreports.ExtentTest test) {
        String currentTimestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new java.util.Date());
        String screenshotPath = captureScreenshotToFile(page, currentTimestamp);
        String relativePath = screenshotPath.replace(ExtentReportManager.reportDir + java.io.File.separator, "");
        if (test != null) {
            test.info(info + " <a href='" + relativePath + "' target='_blank'>Screenshot</a>");
        } else {
            System.err.println("[WARN] ExtentTest is null in captureScreenshotWithInfo. Info: " + info);
        }
    }

    private static String captureScreenshotToFile(com.microsoft.playwright.Page page, String scenarioName) {
        try {
            // Always use the same timestamp folder as ExtentReport.html for screenshots
            String reportDir = ExtentReportManager.reportDir;
            if (reportDir == null) {
                // fallback if reportDir is not initialized
                String date = new java.text.SimpleDateFormat("ddMMMyy").format(new java.util.Date()).toUpperCase();
                String time = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
                reportDir = "reports" + java.io.File.separator + date + java.io.File.separator + time;
            }
            String screenshotDir = reportDir + java.io.File.separator + "Screenshot";
            new java.io.File(screenshotDir).mkdirs();
            String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + System.currentTimeMillis() + ".png";
            String filePath = screenshotDir + java.io.File.separator + fileName;
            page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(new java.io.File(filePath).toPath()).setFullPage(true));
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static byte[] captureScreenshot(Page page) {
        if (page == null) return null;
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }
}