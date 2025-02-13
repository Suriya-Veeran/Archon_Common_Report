package com.p3solutions.archon_report_utility.beans;

import com.p3solutions.archon_report_utility.enums.DividerType;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DividerBean implements ReportBean {
  @Builder.Default private String hexDecimal = "000000";

  @Builder.Default private float lineWidth = 1L;

  @Builder.Default private int pageNumber = 1;

  @Builder.Default private float height = 0L;

  @Builder.Default private DividerType dividerType = DividerType.PAGE_TO_PAGE;

  @Builder.Default private float startXAxis = 18;

  @Builder.Default private float endXAxis = 18;

  @Builder.Default private float bottomYAxis = 30;

  @Builder.Default private float bottomXAxis = 0;

  @Builder.Default
  private int emptyLines = 1;

}
