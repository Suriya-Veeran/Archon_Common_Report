package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.borders.Border;
import com.p3solutions.archon_report_utility.enums.JobStatusEnum;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.enums.TableTypeEnum;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableBean implements ReportBean {

  @Builder.Default private float width = 100;
  @Builder.Default private boolean keepTogether = false;
  @Builder.Default private Border border = Border.NO_BORDER;
  @Builder.Default private int numberOfColumns = 1;
  @Builder.Default private float[] pointColumnWidth = new float[] {1L};
  @Builder.Default private Map<String, String> parameters = new LinkedHashMap<>();
  @Builder.Default private TableTypeEnum type = TableTypeEnum.NUMBER_OF_COLUMNS;
  @Builder.Default private CellInputBean cellInputBean = new CellInputBean();

  @Builder.Default private DividerBean dividerBean = new DividerBean();

  @Builder.Default private Boolean isDividerNeeded = true;

  @Builder.Default private Boolean isJobStatusTableNeeded = true;

  @Builder.Default private JobStatusEnum jobStatus = JobStatusEnum.SUCCESS;

  @Builder.Default private String errorMessage = "Schema Ads is not found";

  @Builder.Default private TableType tableType = TableType.HEADER;
}
