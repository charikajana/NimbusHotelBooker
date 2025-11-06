package com.sabre.hotelbooker.extentreportutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.sabre.hotelbooker.configreaderutils.ConfigReader;
import com.sabre.hotelbooker.loggerutils.LogDirectoryManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extent;
    public static String reportDir;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Get environment from system property, default to DEV
            String environment = System.getProperty("env", "DEV").toUpperCase();
            
            String date = new SimpleDateFormat("ddMMMyy").format(new Date()).toUpperCase();
            String time = new SimpleDateFormat("HHmmss").format(new Date());
            reportDir = "reports" + File.separator + date + File.separator + time;
            new File(reportDir).mkdirs();
            
            // Initialize logging to the same directory as ExtentReports
            LogDirectoryManager.initializeLogging(reportDir);
            
            String reportPath = reportDir + File.separator + "ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Hotel Booker Automation Report - " + environment + " Environment");
            sparkReporter.config().setReportName("Test Execution Results - " + environment + " Environment - Tag Filtering Enabled");
            
            // Add custom CSS for better tag filtering appearance and environment indicator
            sparkReporter.config().setCss(
                ".env-indicator { position: absolute; top: 10px; right: 20px; background: " + getEnvironmentColor(environment) + "; color: white; padding: 8px 15px; border-radius: 20px; font-weight: bold; font-size: 14px; box-shadow: 0 2px 4px rgba(0,0,0,0.2); z-index: 1000; }" +
                ".category-filters { margin: 15px 0; padding: 15px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }" +
                ".filter-title { color: white; font-weight: bold; margin-bottom: 10px; font-size: 16px; }" +
                ".tag-filter-btn { margin: 3px; padding: 8px 12px; background: rgba(255,255,255,0.2); color: white; border: 2px solid rgba(255,255,255,0.3); border-radius: 20px; cursor: pointer; transition: all 0.3s ease; font-weight: 500; }" +
                ".tag-filter-btn.active { background: #28a745; border-color: #28a745; box-shadow: 0 2px 4px rgba(40,167,69,0.3); }" +
                ".tag-filter-btn:hover { background: rgba(255,255,255,0.3); transform: translateY(-1px); }" +
                ".category-name { background: #007bff; color: white; padding: 2px 8px; border-radius: 12px; font-size: 11px; margin: 2px; display: inline-block; }" +
                ".system-info .info-item { margin-bottom: 8px; padding: 5px 10px; background: #f8f9fa; border-left: 4px solid " + getEnvironmentColor(environment) + "; border-radius: 4px; }"
            );
            
            // Add JavaScript for dynamic tag filtering and environment indicator
            sparkReporter.config().setJs(
                "$(document).ready(function() {" +
                "  // Add environment indicator" +
                "  $('body').prepend('<div class=\"env-indicator\">" + environment + " Environment</div>');" +
                "  " +
                "  setTimeout(function() {" +
                "    var categories = [];" +
                "    $('.category-name').each(function() {" +
                "      var cat = $(this).text().trim();" +
                "      if (categories.indexOf(cat) === -1 && cat !== '') categories.push(cat);" +
                "    });" +
                "    " +
                "    if (categories.length > 0) {" +
                "      var filterHtml = '<div class=\"category-filters\">';" +
                "      filterHtml += '<div class=\"filter-title\">Filter Tests by Tags:</div>';" +
                "      filterHtml += '<button class=\"tag-filter-btn active\" data-filter=\"all\">All Tests (' + $('.test-item').length + ')</button>';" +
                "      categories.forEach(function(cat) {" +
                "        var count = $('.test-item').filter(function() { return $(this).find(\".category-name:contains(\\\"\" + cat + \"\\\")\").length > 0; }).length;" +
                "        filterHtml += '<button class=\"tag-filter-btn\" data-filter=\"' + cat + '\">@' + cat + ' (' + count + ')</button>';" +
                "      });" +
                "      filterHtml += '</div>';" +
                "      $('.test-view .container-fluid').prepend(filterHtml);" +
                "      " +
                "      $('.tag-filter-btn').click(function() {" +
                "        $('.tag-filter-btn').removeClass('active');" +
                "        $(this).addClass('active');" +
                "        var filter = $(this).data('filter');" +
                "        if (filter === 'all') {" +
                "          $('.test-item').show();" +
                "        } else {" +
                "          $('.test-item').hide();" +
                "          $('.test-item').each(function() {" +
                "            if ($(this).find('.category-name').filter(function() { return $(this).text().trim() === filter; }).length > 0) {" +
                "              $(this).show();" +
                "            }" +
                "          });" +
                "        }" +
                "      });" +
                "    }" +
                "  }, 1000);" +
                "});"
            );
            
            // Set dashboard as default view with category view enabled
            sparkReporter.viewConfigurer().viewOrder()
                .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY, ViewName.AUTHOR, ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG })
                .apply();
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // Add comprehensive system information including environment details
            extent.setSystemInfo("Environment", environment);
            extent.setSystemInfo("User", System.getProperty("user.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Operating System", System.getProperty("os.name") + " " + System.getProperty("os.version"));
            extent.setSystemInfo("Browser", "Playwright Chromium");
            extent.setSystemInfo("Execution Date", new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()));
            
            // Add environment-specific configuration
            try {
                String hotelBookerURL = ConfigReader.getProperty("HotelBookerURL");
                String browser = ConfigReader.getProperty("browserName");
                if (hotelBookerURL != null) {
                    extent.setSystemInfo("Hotel Booker URL", hotelBookerURL);
                }
                if (browser != null) {
                    extent.setSystemInfo("Configured Browser", browser.toUpperCase());
                }
            } catch (Exception e) {
                extent.setSystemInfo("Configuration", "Unable to load environment properties: " + e.getMessage());
            }
            
            // Add Maven command information
            String tags = System.getProperty("cucumber.filter.tags");
            if (tags != null && !tags.trim().isEmpty()) {
                extent.setSystemInfo("Executed Tags", tags);
            }
        }
        return extent;
    }
    
    /**
     * Get environment-specific color for UI elements
     */
    private static String getEnvironmentColor(String environment) {
        switch (environment.toUpperCase()) {
            case "PROD":
                return "#dc3545"; // Red for production
            case "CERT":
                return "#fd7e14"; // Orange for certification
            case "INT":
                return "#ffc107"; // Yellow for integration
            case "DEV":
            default:
                return "#28a745"; // Green for development
        }
    }
}