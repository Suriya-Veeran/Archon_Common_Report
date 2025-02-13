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

  @Builder.Default private String hexaDecimal = "030303";

  @Builder.Default private int fontSize = 10;

  @Builder.Default private String fontFamily = "Helvetica-Bold";

  @Builder.Default private TextAlignment textAlignment = TextAlignment.LEFT;

  @Builder.Default private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;

  @Builder.Default private float paddingLeft = -17f;

  @Builder.Default private float paddingRight = 10;

  @Builder.Default private float paddingTop = -3f;

  @Builder.Default private float paddingBottom = 10;

  @Builder.Default private float padding = 10;

  @Builder.Default private String backgroundColor = "#f0f0f0";

  @Builder.Default private float backgroundOpacity = 1.0f;

  @Builder.Default private BorderBean borderBean = new BorderBean();
}
