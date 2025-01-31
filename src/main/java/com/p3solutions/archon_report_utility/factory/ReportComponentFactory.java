package com.p3solutions.archon_report_utility.factory;

import com.p3solutions.archon_report_utility.beans.*;
import com.p3solutions.archon_report_utility.beans.charts.HtmlCreationInfoBean;
import com.p3solutions.archon_report_utility.beans.charts.PieChartBean;
import com.p3solutions.archon_report_utility.core.ReportBuilder;
import com.p3solutions.archon_report_utility.enums.ComponentType;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportComponentFactory {

    public static ReportComponent createComponent(ComponentType type, ReportBean bean) {
        try {
            return switch (type) {
                case HEADER -> ReportBuilder.addComponent((HeaderBean) bean);
                case SUMMARY -> ReportBuilder.addComponent((SummaryBean) bean);
                case FOOTER -> ReportBuilder.addComponent((FooterBean) bean);
                case TABLE -> ReportBuilder.addComponent((TableBean) bean);
                case DIVIDER -> ReportBuilder.addComponent((DividerBean) bean);
                case PIE_CHART -> ReportBuilder.addComponent((ChartCreationConfig) bean);
            };
        } catch (Exception exception) {
            throw new IllegalArgumentException("Component Builder failed due to mismatch of : " + type);
        }
    }
}
