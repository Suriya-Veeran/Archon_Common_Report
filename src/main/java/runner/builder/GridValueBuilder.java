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
            "Retention Sets", List.of("Retention_set_1", "Retention_set_2", "Retention_set_1"));
        gridValue.put("Qualification Date", List.of("N/A", "N/A", "N/A"));
        gridValue.put("Records Qualified", List.of("3", "2", "5"));
        gridValue.put("Records Disposed", List.of("0", "0", "0"));
        gridValue.put(
            "Status", List.of("Disposed Success", "Disposed Failure", "Disposed Success"));
        break;
      case ROLLBACK_REPORT:
        gridValue.put("Table Name", List.of("Table1", "Table2", "Table3", "Table4"));
        gridValue.put("Ingested Record Count (for the session)", List.of("1", "2", "3", "4"));
        gridValue.put("Record Count", List.of("1", "2", "3", "4"));
        gridValue.put(
            "Elapsed Time",
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
      case CHAIN_OF_CUSTODY_REPORT:
        gridValue.put("SI No", List.of("1","2","3","4"));
        gridValue.put("Schema Name", List.of("Schema1", "Schema2", "Schema3", "Schema4"));
        gridValue.put("Table Name", List.of("Table1", "Table2", "Table3", "Table4"));
        gridValue.put("Ingestion Status", List.of("Success", "Failed", "Failed", "Failed"));
        break;
      default:
        throw new IllegalArgumentException("Unsupported report name: " + reportNameConstants);
    }

    return gridValue;
  }
}
