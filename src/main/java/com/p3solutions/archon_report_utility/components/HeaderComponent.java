package com.p3solutions.archon_report_utility.components;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.p3solutions.archon_report_utility.beans.HeaderBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderComponent implements ReportComponent {
  private HeaderBean inputBean;

  public void render(Document document) throws IOException {
    

    document.add(new Paragraph(inputBean.getTitle())
            .setFontSize(inputBean.getFontSize())
            .setVerticalAlignment(inputBean.getVerticalAlignment())
            .setTextAlignment(inputBean.getTextAlignment())
            .setFontSize(inputBean.getFontSize())
            .setFont(PdfFontFactory.createFont(inputBean.getFont().getFontName()))
    );

    document.flush();
  }
}
