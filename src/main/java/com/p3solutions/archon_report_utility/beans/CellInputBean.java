package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.enums.FontType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CellInputBean {

  @Builder.Default private String content = "";
  @Builder.Default private int fontSize = 8;
  @Builder.Default private Color backgroundColor = ColorConstants.WHITE;
  @Builder.Default private Border border = Border.NO_BORDER;
  @Builder.Default private String font = FontType.HELVETICA.getFontName();
  @Builder.Default private Color fontColor = ColorConstants.WHITE;
  @Builder.Default private TextAlignment textAlignment = TextAlignment.LEFT;
  @Builder.Default private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
  @Builder.Default private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;
  @Builder.Default private Table table = new Table(1);
  @Builder.Default private boolean isHeader = false;

  @Builder.Default private int rowSpan = 0;
  @Builder.Default private int columnSpan = 0;
  @Builder.Default private int cellHeight = 30;

  @Builder.Default private boolean isJobSummaryHeader = false;
  @Builder.Default private boolean valueHeader = true;
  @Builder.Default private String jobStatus = "Success";
}
