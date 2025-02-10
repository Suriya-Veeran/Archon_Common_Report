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

  @Builder.Default private TableConfigBean tableConfigBean = new TableConfigBean();

  @Builder.Default private FontConfigBean fontConfigBean = new FontConfigBean();

  @Builder.Default private CellInputBean cellInputBean = new CellInputBean();

  @Builder.Default private DividerBean dividerBean = new DividerBean();

  @Builder.Default private MarginBean marginBean = new MarginBean();

  @Builder.Default private BorderBean borderBean = new BorderBean();

  @Builder.Default private JobStatusInputBean jobStatusInputBean = new JobStatusInputBean();

  @Builder.Default private Boolean isDividerNeeded = true;

  @Builder.Default private Boolean isJobStatusTableNeeded = true;

}
