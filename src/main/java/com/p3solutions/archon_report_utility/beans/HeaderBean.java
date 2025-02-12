package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.properties.HorizontalAlignment;
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

  @Builder.Default private String title = "License Volume Statistics Report";
  @Builder.Default private TextAlignment textAlignment = TextAlignment.LEFT; // Text alignment for header content
  @Builder.Default private float rightMargin = 32; // Margin for the right side
  @Builder.Default private float topMargin = 45; // Margin for the top
  @Builder.Default private float leftMargin = 18; // Margin for the left
  @Builder.Default private String fontColor = "030303"; // Background color of the header
  @Builder.Default private int fontSize = 16; // Font size for header content
  @Builder.Default private VerticalAlignment verticalAlignment = VerticalAlignment.MIDDLE;
  @Builder.Default private FontType font = FontType.HELVETICA_BOLD; // Enum FontType for font type
  @Builder.Default private boolean isLogoNeeded = true;
  @Builder.Default private String imagePath = "Archon-Datastore rasterized.png";
  @Builder.Default private float fitWidth = 52;
  @Builder.Default private float fitHeight = 25;
  @Builder.Default private float logoWidth = 90;
  @Builder.Default private float logoHeight = 35;
  @Builder.Default private TextAlignment logoTextAlignment = TextAlignment.LEFT;
  @Builder.Default private VerticalAlignment logoVerticalAlignment = VerticalAlignment.TOP;
  @Builder.Default private HorizontalAlignment logoHorizontalAlignment = HorizontalAlignment.RIGHT;
  @Builder.Default private DividerBean dividerBean = new DividerBean();
  @Builder.Default private Boolean isDividerNeeded = true;
}
