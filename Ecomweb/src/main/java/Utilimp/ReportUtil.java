package Utilimp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportUtil {
	private static final String REPORT_PATH = "reports/report.txt";

    public static synchronized void write(String message) {

        try (BufferedWriter bw =
                 new BufferedWriter(new FileWriter(REPORT_PATH, true))) {

            bw.write(message);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
