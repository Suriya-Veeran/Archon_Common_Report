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

    @Builder.Default
    private String header = "";

    @Builder.Default
    private String content = "";

    @Builder.Default
    private String generatedTime = new Date().toString();

    @Builder.Default
    private Map<String, String> parameters = new LinkedHashMap<>();

    @Builder.Default
    private CardType cardType = CardType.SINGLE_DETAILS_INFO;

    @Builder.Default
    private CellInputBean cellInputBean = new CellInputBean();
}
