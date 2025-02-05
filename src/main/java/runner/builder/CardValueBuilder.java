package runner.builder;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;

@Slf4j
@UtilityClass
public class CardValueBuilder {

  public static Map<String, String> buildParametersForCard(
      ReportNameConstants reportNameConstants) {
    Map<String, String> parameters = new LinkedHashMap<>();
    switch (reportNameConstants) {
      case CONSOLIDATED_INGESTION_VALIDATION_REPORT:
        parameters.put("Ingestion Session Id", "1ef9b1d5-7533-4689-83fc");
        parameters.put("Scheduled By", "System");
        parameters.put("Scheduled Time", new Date().toString());
        parameters.put("Job Name", "Test Job");
        parameters.put("App Name", "Application");
        parameters.put("Schema Name", "Schema");
        parameters.put("Status", "Success");
        break;
      default:
        throw new IllegalArgumentException("Unsupported report type " + reportNameConstants);
    }
    return parameters;
  }
}
