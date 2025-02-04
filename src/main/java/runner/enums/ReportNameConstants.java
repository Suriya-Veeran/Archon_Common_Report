package runner.enums;

import com.p3solutions.archon_report_utility.exception.EnumNotFound;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportNameConstants {
  MATERIALIZED_VIEW_REFRESH_REPORT(
      "Materialized View Refresh Report", "Materialized_View_Refresh.pdf"),

  TABLE_OPTIMIZATION_REPORT("Table Data Optimization Report", "Table_Optimization.pdf"),

  PURGE_REPORT("Purge Report", "Purge_Report.pdf"),

  INGESTION_REPORT("Ingestion Report", "Ingestion_Report.pdf"),

  LICENSE_VOLUME_STATISTICS_REPORT(
      "License Volume Statistics Report", "License_Volume_Statistics.pdf"),

  SOURCE_TO_VALIDATION_REPORT("Source To Validation Report", "Source_To_Validation_Report.pdf");

  private final String reportName;
  private final String fileName;

  public static ReportNameConstants getReportNameConstants(String reportName) {
    for (ReportNameConstants constants : ReportNameConstants.values()) {
      if (constants.getReportName().equals(reportName)) {
        return constants;
      }
    }
    throw new EnumNotFound(reportName);
  }
}
