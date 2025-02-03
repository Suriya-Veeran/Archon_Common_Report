package com.p3solutions.archon_report_utility.factory;

import com.p3solutions.archon_report_utility.beans.*;
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
      ReportComponent component = null;
      switch (type) {
        case HEADER:
          component = ReportBuilder.addComponent((HeaderBean) bean);
          break;
        case SUMMARY:
          component = ReportBuilder.addComponent((SummaryBean) bean);
          break;
        case FOOTER:
          component = ReportBuilder.addComponent((FooterBean) bean);
          break;
        case TABLE:
          component = ReportBuilder.addComponent((TableBean) bean);
          break;
        case DIVIDER:
          component = ReportBuilder.addComponent((DividerBean) bean);
          break;
        case PIE_CHART:
          component = ReportBuilder.addComponent((ChartCreationConfig) bean);
          break;
        case GRID_TABLE:
          component = ReportBuilder.addComponent((GridTableBean) bean);
          break;
        default:
          throw new IllegalArgumentException("Unexpected value: " + type);
      }
      return component;
    } catch (Exception exception) {
      throw new IllegalArgumentException("Component Builder failed due to mismatch of : " + type);
    }
  }
}
