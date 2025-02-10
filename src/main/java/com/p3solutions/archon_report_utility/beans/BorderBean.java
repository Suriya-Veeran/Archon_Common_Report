package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.properties.BorderRadius;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorderBean {

  @Builder.Default private String solidBorderColor = "DCDCDC";

  @Builder.Default private float solidBorderWidth = 1;

  @Builder.Default private String solidBorderBottomColor = "DCDCDC";

  @Builder.Default private float solidBorderBottomWidth = 0.5f;

  @Builder.Default private Border border = Border.NO_BORDER;

  @Builder.Default private Border borderBottom = Border.NO_BORDER;

  @Builder.Default private Border borderTop = Border.NO_BORDER;

  @Builder.Default private Border borderLeft = Border.NO_BORDER;

  @Builder.Default private Border borderRight = Border.NO_BORDER;

  @Builder.Default private BorderRadius borderRadius = new BorderRadius(0f);

  @Builder.Default private BorderRadius borderTopRightRadius = new BorderRadius(0f);

  @Builder.Default private BorderRadius borderTopLeftRadius = new BorderRadius(0f);

  @Builder.Default private BorderRadius borderBottomRightRadius = new BorderRadius(0f);

  @Builder.Default private BorderRadius borderBottomLeftRadius = new BorderRadius(0f);
}
