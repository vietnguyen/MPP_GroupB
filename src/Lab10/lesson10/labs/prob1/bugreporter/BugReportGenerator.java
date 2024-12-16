package lesson10.labs.prob1.bugreporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.logging.Logger;

import lesson10.labs.prob1.classfinder.ClassFinder;
import lesson10.labs.prob1.javapackage.Circle;
import lesson10.labs.prob1.javapackage.ClosedCurve;
import lesson10.labs.prob1.javapackage.DataMiner;
import lesson10.labs.prob1.javapackage.Rectangle;

/**
 * This class scans the package lesson10.labs.prob2.javapackage for classes with annotation
 *
 * @BugReport. It then generates a bug report bugreport.txt, formatted like this:
 * <p>
 * Joe Smith reportedBy: classname: description: severity:
 * <p>
 * reportedBy: classname: description: severity:
 * <p>
 * Tom Jones reportedBy: classname: description: severity:
 * <p>
 * reportedBy: classname: description: severity:
 */
public class BugReportGenerator {

  private static final Logger LOG = Logger.getLogger(BugReportGenerator.class.getName());
  private static final String PACKAGE_TO_SCAN = "lesson10.labs.prob1.javapackage";
  private static final String REPORT_NAME = "bug_report.txt";
  private static final String REPORTED_BY = "reportedBy: ";
  private static final String CLASS_NAME = "classname: ";
  private static final String DESCRIPTION = "description: ";
  private static final String SEVERITY = "severity: ";

  public void reportGenerator() {
    List<Class<?>> classes = ClassFinder.find(PACKAGE_TO_SCAN);

    Map<String, List<BugReportInfo>> map = new HashMap<>();
    classes.forEach(c -> {
      if (c.isAnnotationPresent(BugReport.class)) {
        BugReport annotation = c.getAnnotation(BugReport.class);
        BugReportInfo bugReportInfo = new BugReportInfo(
            annotation.assignedTo(),
            c.getSimpleName(),
            annotation.severity(),
            annotation.description(),
            annotation.reportedBy());
        map.computeIfAbsent(annotation.assignedTo(), k -> new ArrayList<>()).add(bugReportInfo);
      }
    });
    try (PrintWriter writer = new PrintWriter(new FileWriter(REPORT_NAME, false))) {
         saveToFile(writer, map);
    } catch (IOException e) {
      LOG.warning("Error writing to file: " + e.getMessage());
    }
  }

  private void saveToFile(PrintWriter writer, Map<String, List<BugReportInfo>> map) {
    map.forEach((assignedTo, bugReportInfos) -> {
      writer.println(assignedTo);
      bugReportInfos.forEach(bugReportInfo -> {
        writer.println("    " + REPORTED_BY + bugReportInfo.reportedBy);
        writer.println("    " + CLASS_NAME + bugReportInfo.className);
        writer.println("    " + DESCRIPTION + bugReportInfo.description);
        writer.println("    " + SEVERITY + bugReportInfo.severity);
        writer.println();
      });
    });
  }

  record BugReportInfo(String assignedTo, String className, int severity, String description,
                       String reportedBy) {

  }

}
