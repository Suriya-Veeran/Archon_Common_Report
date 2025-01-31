package com.p3solutions.archon_report_utility.builder;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.SummaryBean;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SummaryBeanBuilder {

    public static SummaryBean buildSummaryBean(String summaryText,
                                               String hexaDecimal,
                                               int fontSize,
                                               String fontFamily,
                                               TextAlignment textAlignment,
                                               VerticalAlignment verticalAlignment) {

        return SummaryBean.builder()
                .summaryText(summaryText)
                .hexaDecimal(hexaDecimal)
                .fontSize(fontSize)
                .fontFamily(fontFamily)
                .textAlignment(textAlignment)
                .verticalAlignment(verticalAlignment).build();

    }

}
