package com.p3solutions.archon_report_utility.components;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.p3solutions.archon_report_utility.beans.FooterBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FooterComponent implements ReportComponent {
  private FooterBean inputBean;

  public void render(Document document) {
    document.add(new Paragraph("Footer"));
    document.flush();
  }
}
