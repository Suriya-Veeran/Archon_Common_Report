package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HeaderTextBean {

    @Builder.Default
    private String headerText = "";

    @Builder.Default
    private String hexaDecimal = "000000";

    @Builder.Default
    private int fontSize = 10;

    @Builder.Default
    private String fontFamily = "Helvetica";

    @Builder.Default
    private TextAlignment textAlignment = TextAlignment.LEFT;

    @Builder.Default
    private VerticalAlignment verticalAlignment = VerticalAlignment.TOP;


}
