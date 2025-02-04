package runner.process;

import com.p3solutions.archon_report_utility.exception.ReportGenerationException;
import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.yaml_utils.ConfigLoader;

import java.io.File;

@Slf4j
public class Runner {

  public static void main(String[] args) throws ReportGenerationException {
    String reportName = ConfigLoader.getInstance().getReportName();
    ReportNameConstants reportNameConstants =
        ReportNameConstants.getReportNameConstants(reportName);
    String filePath =
        ConfigLoader.getInstance().getLocation()
            + File.separator
            + reportNameConstants.getFileName();
    runComponent(reportName);
  }

  private static void runComponent(String reportName) {
    switch (reportName){

    }
  }


}
