package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummaryBean implements ReportBean {

  @Builder.Default private String summaryText = "Header";

  @Builder.Default
  private String hexaDecimal = "030303";

  @Builder.Default
  private int fontSize = 10;

  @Builder.Default
  private String fontFamily = "Helvetica-Bold";

  @Builder.Default
  private TextAlignment textAlignment = TextAlignment.LEFT;

  @Builder.Default
  private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
}
