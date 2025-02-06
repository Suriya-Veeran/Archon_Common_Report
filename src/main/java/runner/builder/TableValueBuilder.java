package runner.builder;

import lombok.experimental.UtilityClass;
import runner.enums.ReportNameConstants;

import java.util.*;

@UtilityClass
public class TableValueBuilder {
  public static Map<String, String> buildContentForJobSummary(ReportNameConstants type) {
    Date scheduledTime = new Date();
    Map<String, String> contentMap = new LinkedHashMap<>();
    Date startTime = new Date();

    switch (Objects.requireNonNull(type)) {
      case MATERIALIZED_VIEW_REFRESH_REPORT:
        contentMap.put("Job Type", "Materialized View Refresh Report");
        addCommonContent(contentMap, startTime, scheduledTime);
        contentMap.put("Job Name", "Materialized View");
        break;
      case TABLE_OPTIMIZATION_REPORT:
        contentMap.put("Job Instance Id", "0123546474-5252");
        contentMap.put("Job Type", "Table Data Optimization");
        addCommonContent(contentMap, startTime, scheduledTime);
        contentMap.put("Table Name", "Address Table");
        break;
      case INGESTION_REPORT:
        addCommonContent(contentMap, startTime, scheduledTime);
        addCommonContentForIngestionAndSourceValidation(contentMap);
        break;
      case LICENSE_VOLUME_STATISTICS_REPORT:
        contentMap.put("Scheduled By", "Sysadmin");
        contentMap.put("Scheduled Time", scheduledTime.toString());
        contentMap.put("Start Time", startTime.toString());
        contentMap.put("End Time", new Date().toString());
        contentMap.put("Total Time", totalTimeCalculation(startTime, new Date()));
        break;
      case SOURCE_TO_TARGET_VALIDATION_REPORT:
        contentMap.put("Ingestion Session Id", "1234567890123");
        addCommonContent(contentMap, startTime, scheduledTime);
        addCommonContentForIngestionAndSourceValidation(contentMap);
        break;
      case PURGE_REPORT:
        contentMap.put("Purge list name", "PURGE_LIST - (1713852484696)");
        contentMap.put("Retention policy", "EB -15");
        contentMap.put("Purge level", "Record/Group");
        contentMap.put("Time of purge", new Date().toString());
        break;
      case ROLLBACK_REPORT:
        contentMap.put("Scheduled By", "Sysadmin");
        contentMap.put("Scheduled Time", scheduledTime.toString());
        contentMap.put("Start Time", new Date().toString());
        contentMap.put("End Time", new Date().toString());
        contentMap.put("Total Time", totalTimeCalculation(startTime, new Date()));
        contentMap.put("Application Name", "App name");
        contentMap.put("Ingestion Session Id", "1234567890123");
        contentMap.put("Ingestion type", "Rest");
        contentMap.put("Rollback Reason", "Data duplicated");
        contentMap.put("Rollback Notes", "notes for rollback");
        break;
      case CONSOLIDATED_INGESTION_VALIDATION_REPORT:
        contentMap.put("Job Session Id", "1ef9b1d5-7533-4689-83fc-b");
        contentMap.put("Job Type", "Consolidated Ingestion Validation Report");
        contentMap.put("Scheduled By", "Sysadmin");
        contentMap.put("Scheduled Time", scheduledTime.toString());
        contentMap.put("Start Time", new Date().toString());
        contentMap.put("End Time", new Date().toString());
        contentMap.put("Total Time", totalTimeCalculation(startTime, new Date()));
        contentMap.put("Application Name", "App name");
        contentMap.put("Schema Regex", "N/A");
        contentMap.put("Table Regex", "N/A");
        contentMap.put("Number of schemas involved", "1");
        contentMap.put("Number of tables involved", "1");
        contentMap.put("Number of ingestion sessions involved", "1");
        break;
      case CHAIN_OF_CUSTODY_REPORT:
        contentMap.put("Job Type", "Sysadmin");
        contentMap.put("Scheduled By", "Sysadmin");
        contentMap.put("Scheduled Time", scheduledTime.toString());
        contentMap.put("Start Time", new Date().toString());
        contentMap.put("End Time", new Date().toString());
        contentMap.put("Total Time", totalTimeCalculation(startTime, new Date()));
        contentMap.put("Application Name", "App name");
        break;
      default:
        throw new IllegalArgumentException("Unsupported content type: " + type);
    }
    return contentMap;
  }

  private static void addCommonContentForIngestionAndSourceValidation(
      Map<String, String> contentMap) {
    contentMap.put("Table Name", "Claim");
    contentMap.put("Ingestion Type", "REST");
    contentMap.put("Ingestion Mode", "Ingest Data");
    contentMap.put("Source Data Storage Profile ", "Local File System");
    contentMap.put("Storage Type", "Default Storage");
    contentMap.put("Bucket Name", "N/A");
    contentMap.put("Bucket Type", "N/A");
    contentMap.put("Region", "N/A");
    contentMap.put("Source path", "src/main/resources");
    contentMap.put("Files Count Per Set", "10");
  }

  private static void addCommonContent(
      Map<String, String> contentMap, Date startTime, Date scheduledTime) {
    contentMap.put("Scheduled By", "Sysadmin");
    contentMap.put("Scheduled Time", scheduledTime.toString());
    contentMap.put("Start Time", new Date().toString());
    contentMap.put("End Time", new Date().toString());
    contentMap.put("Total Time", totalTimeCalculation(startTime, new Date()));
    contentMap.put("Application Name", "App name");
    contentMap.put("Schema Name", "Schema Name");
  }

  private static String totalTimeCalculation(Date startTime, Date endTime) {
    long difference = startTime.getTime() - endTime.getTime();
    return Long.toString((difference / 1000) % 60);
  }


  public static Map<String,String> headerTableParameters(){
    Map<String, String> parameters = new LinkedHashMap<>();
    parameters.put("Generated By", "Sysadmin");
    parameters.put("Job Name", "Test Job");
    parameters.put("Report Generated Time", new Date().toString());
    return parameters;
  }

  public static Map<String,String> additionalTableParameters(){
    Map<String, String> additionalParameters = new LinkedHashMap<>();
    additionalParameters.put("Record Count Before Refresh", "0");
    additionalParameters.put("Record Count After Refresh", "720");
    return additionalParameters;
  }

  public static Map<String,String> approvalDetailTableParameters(){
    Map<String, String> approvalDetailParameters = new LinkedHashMap<>();
    approvalDetailParameters.put("Approved By", "System");
    approvalDetailParameters.put("Approved Date", new Date().toString());
    approvalDetailParameters.put("Approved Mode", "Auto Approved");
    approvalDetailParameters.put("Attachments", "0");
    return approvalDetailParameters;
  }

}
