package runner.process;

import com.p3solutions.archon_report_utility.exception.EnumNotFound;
import com.p3solutions.archon_report_utility.exception.ReportGenerationException;
import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.factory.ReportGeneratorFactory;
import runner.services.CommonRunner;
import runner.yaml_utils.ConfigLoader;

@Slf4j
public class Runner {

  public static void main(String[] args) throws ReportGenerationException {
    String reportName = ConfigLoader.getInstance().getReportName();
    ReportNameConstants reportNameConstants =
        ReportNameConstants.getReportNameConstants(reportName);
    runComponent(ConfigLoader.getInstance().getLocation(), reportNameConstants);
  }

  private static void runComponent(String location, ReportNameConstants reportNameConstants) {
    try {
      CommonRunner reportGenerator = ReportGeneratorFactory.getReportGenerator(reportNameConstants);
      reportGenerator.generateReport(location, reportNameConstants);
    } catch (IllegalArgumentException e) {
      throw new EnumNotFound("Unsupported report type " + reportNameConstants.getReportName());
    }
  }
}
