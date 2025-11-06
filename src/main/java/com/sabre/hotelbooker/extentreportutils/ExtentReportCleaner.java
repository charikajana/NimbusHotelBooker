package com.sabre.hotelbooker.extentreportutils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtentReportCleaner {
    public static void main(String[] args) throws IOException {
        String reportPath = findLatestExtentReport();
        if (reportPath == null) {
            System.err.println("No ExtentReport.html found in reports directory.");
            return;
        }
        String html = readFile(reportPath);

        // Count test cases and steps
        int testCaseCount = countOccurrences(html, "li class=\"test-item");
        int stepPassed = countOccurrences(html, "badge log pass-bg");
        int stepFailed = countOccurrences(html, "badge log fail-bg");
        int stepOthers = countOccurrences(html, "badge log info-bg");
        int featureCount = 1; // You can enhance this to count unique features if needed

        // Correct regex string escaping for Java
        String dashboardRegex =
            "(<div class=\"row\">\\s*"
            + "<div class=\"col-md-3\">[\\s\\S]*?</div>\\s*"
            + "<div class=\"col-md-3\">[\\s\\S]*?</div>\\s*"
            + "<div class=\"col-md-3\">[\\s\\S]*?</div>\\s*"
            + "<div class=\"col-md-3\">[\\s\\S]*?</div>\\s*</div>)";
        Pattern pattern = Pattern.compile(dashboardRegex);
        Matcher matcher = pattern.matcher(html);

        String replacement = "<div class=\"row\">\n" +
                "    <div class=\"col-md-4\">\n" +
                "        <div class=\"card\">\n" +
                "            <div class=\"card-header\">\n" +
                "                <h6 class=\"card-title\">Features</h6>\n" +
                "            </div>\n" +
                "            <div class=\"card-body\">\n" +
                "                <div class=\"\">\n" +
                "                    <canvas id='parent-analysis' width='115' height='90'></canvas>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"card-footer\">\n" +
                "                <div><small data-tooltip='100%'>\n" +
                "<b>" + featureCount + "</b> Feature passed\n" +
                "</small>\n" +
                "                </div>\n" +
                "                <div>\n" +
                "                    <small data-tooltip='0%'><b>0</b> Feature failed,<br><b>0</b> skipped, <b data-tooltip='0%'>0</b> others\n" +
                "</small>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"col-md-4\">\n" +
                "        <div class=\"card\">\n" +
                "            <div class=\"card-header\">\n" +
                "                <h6 class=\"card-title\">TestCases</h6>\n" +
                "            </div>\n" +
                "            <div class=\"card-body\">\n" +
                "                <div class=\"\">\n" +
                "                    <canvas id='child-analysis' width='115' height='90'></canvas>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"card-footer\">\n" +
                "                <div><small data-tooltip='100%'><b>" + testCaseCount + "</b> TestCases passed</small></div>\n" +
                "                <div>\n" +
                "                    <small data-tooltip='0%'><b>0</b> TestCases failed,<br><b>0</b> skipped, <b data-tooltip='%'>0</b> others\n" +
                "</small>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"col-md-4\">\n" +
                "        <div class=\"card\">\n" +
                "            <div class=\"card-header\">\n" +
                "                <h6 class=\"card-title\">Step events</h6>\n" +
                "            </div>\n" +
                "            <div class=\"card-body\">\n" +
                "                <div class=\"\">\n" +
                "                    <canvas id='events-analysis' width='115' height='90'></canvas>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"card-footer\">\n" +
                "                <div><small data-tooltip='75%'><b>" + stepPassed + "</b> Step passed</small></div>\n" +
                "                <div>\n" +
                "                    <small data-tooltip='0%'><b>" + stepFailed + "</b> Step failed,<br><b data-tooltip='%'>" + stepOthers + "</b> others\n" +
                "</small>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";

        String newHtml = matcher.replaceFirst(Matcher.quoteReplacement(replacement));
        writeFile(reportPath, newHtml);
        System.out.println("Extent report cleaned and dashboard updated: " + reportPath);
    }

    private static int countOccurrences(String str, String sub) {
        int count = 0, idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    private static String readFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
    }

    private static void writeFile(String path, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            writer.write(content);
        }
    }

    private static String findLatestExtentReport() {
        File reportsDir = new File("reports");
        if (!reportsDir.exists() || !reportsDir.isDirectory()) return null;
        File latestReport = null;
        long latestMod = Long.MIN_VALUE;
        for (File dateDir : reportsDir.listFiles(File::isDirectory)) {
            for (File timeDir : dateDir.listFiles(File::isDirectory)) {
                File reportFile = new File(timeDir, "ExtentReport.html");
                if (reportFile.exists() && reportFile.lastModified() > latestMod) {
                    latestMod = reportFile.lastModified();
                    latestReport = reportFile;
                }
            }
        }
        return latestReport != null ? latestReport.getPath() : null;
    }
}
