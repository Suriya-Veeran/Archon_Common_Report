package com.p3solutions.archon_report_utility.factory;

import com.p3solutions.archon_report_utility.beans.*;
import com.p3solutions.archon_report_utility.beans.charts.PieChartBean;
import com.p3solutions.archon_report_utility.core.ReportBuilder;
import com.p3solutions.archon_report_utility.enums.ComponentType;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportComponentFactory {

    public static ReportComponent createComponent(ComponentType type, ReportBean bean){
        try {
            switch (type) {
                case HEADER:
                    return ReportBuilder.addComponent((HeaderBean) bean);
                case SUMMARY:
                    return ReportBuilder.addComponent((SummaryBean) bean);
                case FOOTER:
                    return ReportBuilder.addComponent((FooterBean) bean);
                case TABLE:
                    return ReportBuilder.addComponent((TableBean) bean);
                case DIVIDER:
                    return ReportBuilder.addComponent((DividerBean) bean);
                case PIE_CHART:
                    return ReportBuilder.addComponent((PieChartBean) bean);
                default:
                    throw new IllegalArgumentException("Unknown component type: " + type);
            }
        }catch (Exception exception){
            throw new IllegalArgumentException("Component Builder failed due to mismatch of : " + type);
        }
    }
}
