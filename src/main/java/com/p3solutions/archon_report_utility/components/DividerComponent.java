package com.p3solutions.archon_report_utility.components;

import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;

import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.p3solutions.archon_report_utility.beans.DividerBean;
import com.p3solutions.archon_report_utility.enums.DividerType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DividerComponent implements ReportComponent {
  private DividerBean inputBean;

  public void render(Document document) {
    PdfPage pdfPage = document.getPdfDocument().getPage(inputBean.getPageNumber());
    PdfCanvas canvas = new PdfCanvas(pdfPage);
    canvas.setStrokeColor(hexaDecimalToRGB(inputBean.getHexDecimal()));

    if (inputBean.getDividerType() == DividerType.CONTENT) {
      canvas.moveTo(20, inputBean.getHeight());
      canvas.lineTo(pdfPage.getPageSize().getWidth() - 18, inputBean.getHeight());
    } else if (inputBean.getDividerType() == DividerType.PAGE_TO_PAGE) {
      canvas.moveTo(0, inputBean.getHeight());
      canvas.lineTo(pdfPage.getPageSize().getWidth(), inputBean.getHeight());
      document.flush();
    }
    canvas.setLineWidth(inputBean.getLineWidth());
    canvas.closePathStroke();
    document.flush();
  }
}
