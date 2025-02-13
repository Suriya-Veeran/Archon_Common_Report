package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.borders.Border;
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

  @Builder.Default private float borderRadius = 0f;

  @Builder.Default private float borderTopRightRadius = 0f;

  @Builder.Default private float borderTopLeftRadius = 0f;

  @Builder.Default private float borderBottomRightRadius = 0f;

  @Builder.Default private float borderBottomLeftRadius = 1f;
}
