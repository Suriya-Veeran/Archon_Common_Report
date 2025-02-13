package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.properties.TextAlignment;
import com.p3solutions.archon_report_utility.enums.JobStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobStatusInputBean {

  @Builder.Default private JobStatusEnum jobStatus = JobStatusEnum.SUCCESS;

  @Builder.Default private String errorMessage = "Schema Ads is not found";

  @Builder.Default private float fontSize = 7;

  @Builder.Default private TextAlignment textAlignment = TextAlignment.LEFT;

  @Builder.Default private float marginLeft = 4f;

  @Builder.Default private String header = "Job Status: ";

  @Builder.Default private String fontProgram = "src/main/resources/fonts/Roboto-Italic.ttf";

  @Builder.Default private String errorHeader = "Error Message: ";

  @Builder.Default private String errorFontProgram = "src/main/resources/fonts/Roboto-Regular.ttf";

  @Builder.Default private int rowSpan = 1;

  @Builder.Default private int columnSpan = 3;

  @Builder.Default private String errorBackgroundColor = "FFEAEA";

  @Builder.Default private Border border = Border.NO_BORDER;

  @Builder.Default private String jobStatusFontColor = "FFFFFF";


}
