package runner.builder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;
import runner.enums.ReportNameConstants;

@UtilityClass
public class GridValueBuilder {

  public static Map<String, List<String>> buildGridValue(ReportNameConstants reportNameConstants) {
    Map<String, List<String>> gridValue = new LinkedHashMap<>();
    switch (reportNameConstants) {
      case TABLE_OPTIMIZATION_REPORT:
        gridValue.put("Description", List.of("Data Snapshots", "Data Files", "Size of Table"));
        gridValue.put("Count of pre-data optimization", List.of("2", "8", "85.432 KB"));
        gridValue.put("Count of post-data optimization", List.of("1", "1", "25.1 KB"));
        gridValue.put(
            "Message",
            List.of(
                "1 snapshot removed",
                "8 data files merged as 1",
                "Table size reduced to 23.745 KB"));
        break;
      case PURGE_REPORT:
        gridValue.put("Table Name", List.of("Employees", "Jobs", "Job History"));
        gridValue.put(
            "Retention Sets", List.of("Retention_set_1713", "Retention_set_171385",
                        "145_ret,Retention_set_1713347"));
        gridValue.put("Qualification Date", List.of("N/A", "N/A", "N/A"));
        gridValue.put("Records Qualified", List.of("3", "2", "5"));
        gridValue.put("Records Disposed", List.of("0", "0", "0"));
        gridValue.put(
            "Status", List.of("Disposed Success", "Disposed Failure", "Disposed Success"));
        gridValue.put("Failure Reason", List.of("N/A", "Record in hold has another retention which is not expired", "N/A"));
        break;
      case ROLLBACK_REPORT:
        gridValue.put("Table Name", List.of("PROC_CODE,CLAIMS_SYS_DBO.PROC_CODE", "DX_CODE,CLAIMS_SYS_DBO.DX_CODE",
                "ADDRESS,CLAIMS_SYS_DBO.ADDRESS", "PROVIDER,CLAIMS_SYS_DBO.PROVIDER"));
        gridValue.put("Ingested Record Count (for the session)", List.of("100,Content: 0", "100,Content: 0",
                "0,Content: 0", "0,Content: 0"));
        gridValue.put("Record Count after rollback (for the session)", List.of("0,Content: 0", "0,Content: 0",
                "0,Content: 0", "0,Content: 0"));
        gridValue.put(
            "Time Taken",
            List.of("00:02:28.025", "00:03:28.025", "00:04:28.025", "00:05:28.025"));
        gridValue.put("Status", List.of("Success", "Failed", "Failed", "Success"));
        break;
      case INGESTION_REPORT:
        gridValue.put("Table Name", List.of("Table1", "Table2", "Table3", "Table4"));
        gridValue.put("File Count", List.of("1", "2", "3", "4"));
        gridValue.put("Ingested Record Count (for the session)", List.of("1", "2", "3", "4"));
        gridValue.put(
            "Elapsed Time",
            List.of("00:02:28.025", "00:03:28.025", "00:04:28.025", "00:05:28.025"));
        gridValue.put("Status", List.of("Success", "Success", "Success", "Success"));
        break;
      default:
        throw new IllegalArgumentException("Unsupported report name: " + reportNameConstants);
    }

    return gridValue;
  }

  public static Map<String, List<String>> buildGridValue(ReportNameConstants reportNameConstants, String type){
    Map<String, List<String>> gridValue = new LinkedHashMap<>();
    switch (reportNameConstants) {
      case CHAIN_OF_CUSTODY_REPORT :
        if(type.equalsIgnoreCase("Schema")){
          gridValue.put("SI No", List.of("1"));
          gridValue.put("Schema Name", List.of("Schema1"));
          gridValue.put("Ingestion Status", List.of("Success"));
          gridValue.put("Message", List.of("3/18 tables ingested"));
          return gridValue;
        } else if (type.equalsIgnoreCase("Table")) {
            gridValue.put("SI No", List.of("1","2","3"));
            gridValue.put("Schema Name", List.of("Schema1", "Schema2", "Schema 3"));
            gridValue.put("Table Name", List.of("Table1", "Table2", "Table3"));
            gridValue.put("Ingestion Status", List.of("Ingested", "Not Ingested", "Ingested"));
            return gridValue;
        }
        break;
      default:
        throw new IllegalArgumentException("Unsupported report name: " + reportNameConstants);
    }
    return gridValue;
  }

}
