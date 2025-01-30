package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.enums.FontType;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeaderBean implements ReportBean {

  @Builder.Default private String title = "Archon Report";

  @Builder.Default
  private TextAlignment textAlignment = TextAlignment.LEFT; // Text alignment for header content

  @Builder.Default private float rightMargin = 0.0f; // Margin for the right side

  @Builder.Default private float topMargin = 0.0f; // Margin for the top

  @Builder.Default private float leftMargin = 0.0f; // Margin for the left

  @Builder.Default private String backgroundColor = "#FFFFFF"; // Background color of the header

  @Builder.Default private int fontSize = 12; // Font size for header content

  @Builder.Default private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;

  @Builder.Default private FontType font = FontType.HELVETICA; // Enum FontType for font type
}
