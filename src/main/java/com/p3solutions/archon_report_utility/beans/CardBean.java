package com.p3solutions.archon_report_utility.beans;

import com.p3solutions.archon_report_utility.enums.CardType;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBean implements ReportBean {

  @Builder.Default private String header = "";

  @Builder.Default private String content = "";

  @Builder.Default private String generatedTime = new Date().toString();

  @Builder.Default private Map<String, String> parameters = new LinkedHashMap<>();

  @Builder.Default private CardType cardType = CardType.SINGLE_DETAILS_INFO;

  @Builder.Default private CellInputBean cellInputBean = new CellInputBean();

  @Builder.Default private MarginBean marginBean = new MarginBean();

  @Builder.Default private BorderBean borderBean = new BorderBean();

  @Builder.Default private int emptyLines = 1;

  @Builder.Default private String cardBackground = "E8EDF7";

  @Builder.Default private String borderColor = "DCDCDC";

  @Builder.Default private String successColor = "007D2B";

  @Builder.Default private String headerValueColor = "2C2C2C";

  @Builder.Default private String valueColor = "000000";

  @Builder.Default private String headerFontPath = "src/main/resources/fonts/Roboto-Medium.ttf";

  @Builder.Default private String valueFontPath = "src/main/resources/fonts/Roboto-Regular.ttf";
}
