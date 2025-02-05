package com.p3solutions.archon_report_utility.components;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.p3solutions.archon_report_utility.beans.HeaderBean;
import com.p3solutions.archon_report_utility.event_handler.HeaderEventHandler;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class HeaderComponent implements ReportComponent {
  private HeaderBean inputBean;

  public void render(Document document) throws IOException {
    PdfDocument pdfDocument = document.getPdfDocument();
    pdfDocument.addEventHandler(
        PdfDocumentEvent.START_PAGE, new HeaderEventHandler(inputBean, document));
  }
}
