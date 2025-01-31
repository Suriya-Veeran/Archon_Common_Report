package com.p3solutions.archon_report_utility.builder;

import com.p3solutions.archon_report_utility.beans.DividerBean;
import com.p3solutions.archon_report_utility.enums.DividerType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DividerBeanBuilder {

    public static DividerBean buildDividerInputBean(
            float height, float lineWidth, String hexaDecimal, int pageNumber, DividerType dividerType) {
        return DividerBean.builder()
                .pageNumber(pageNumber)
                .hexDecimal(hexaDecimal)
                .height(height)
                .lineWidth(lineWidth)
                .dividerType(dividerType)
                .build();
    }
}
