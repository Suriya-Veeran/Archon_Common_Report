package com.p3solutions.archon_report_utility.components.charts;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.p3solutions.archon_report_utility.beans.charts.PieChartBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieChartComponent implements ReportComponent {
  private PieChartBean inputBean;

  @Override
  public void render(Document document) {
    document.add(new Paragraph("Summary"));
  }
}
