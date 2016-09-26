package lesson10.labs.prob2.bugreporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import lesson10.labs.prob2.classfinder.ClassFinder;

/**
 * This class scans the package lesson10.labs.prob2.javapackage
 * for classes with annotation @BugReport. It then generates a bug report
 * bugreport.txt, formatted like this:
 * <p>
 * Joe Smith
 * reportedBy:
 * classname:
 * description:
 * severity:
 * <p>
 * reportedBy:
 * classname:
 * description:
 * severity:
 * <p>
 * Tom Jones
 * reportedBy:
 * classname:
 * description:
 * severity:
 * <p>
 * reportedBy:
 * classname:
 * description:
 * severity:
 */
public class BugReportGenerator {
    private static final Logger LOG = Logger.getLogger(BugReportGenerator.class.getName());
    private static final String PACKAGE_TO_SCAN = "lesson10.labs.prob2.javapackage";
    private static final String REPORT_NAME = "bug_report.txt";
    private static final String REPORTED_BY = "reportedBy: ";
    private static final String CLASS_NAME = "classname: ";
    private static final String DESCRIPTION = "description: ";
    private static final String SEVERITY = "severity: ";

    public void reportGenerator() {
        List<Class<?>> classes = ClassFinder.find(PACKAGE_TO_SCAN);
        //implement

        try (PrintWriter pw = new PrintWriter(new FileWriter("report.txt"))) {
            classes.stream()
                    .map(cl -> cl.getAnnotation(BugReport.class))
                    .collect(Collectors.groupingBy(a -> a.assignedTo()))
                    .forEach((a, b) -> {
                        pw.println(a);
                        b.stream().forEach((f) -> {
                            pw.println("    reportedBy: " + f.reportedBy());
                            pw.println("    classname: " + b.getClass());
                            pw.println("    description: " + f.reportedBy());
                            pw.println("    severity: " + f.severity());
                            pw.println();
                        });
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
