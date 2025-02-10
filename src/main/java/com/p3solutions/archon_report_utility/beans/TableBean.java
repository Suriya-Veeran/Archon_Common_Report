package com.p3solutions.archon_report_utility.beans;

import com.p3solutions.archon_report_utility.enums.PaddingType;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Builder.Default private PaddingInputBean paddingInputBean = new PaddingInputBean();

  @Builder.Default private JobStatusInputBean jobStatusInputBean = new JobStatusInputBean();

  @Builder.Default private AlignmentBean alignmentBean = new AlignmentBean();

  @Builder.Default private Boolean isDividerNeeded = true;

  @Builder.Default private PaddingType paddingType = PaddingType.COMMON_PADDING;
}
