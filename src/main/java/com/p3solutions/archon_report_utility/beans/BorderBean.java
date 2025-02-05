package com.p3solutions.archon_report_utility.beans;

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
}
