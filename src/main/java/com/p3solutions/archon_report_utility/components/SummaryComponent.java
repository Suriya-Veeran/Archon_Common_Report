package com.p3solutions.archon_report_utility.components;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.p3solutions.archon_report_utility.beans.SummaryBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryComponent implements ReportComponent {
    private SummaryBean inputBean;

    public void render(Document document) throws IOException {
        document.add(new Paragraph(inputBean.getSummaryText())
                .setTextAlignment(inputBean.getTextAlignment())
                .setFontColor(hexaDecimalToRGB(inputBean.getHexaDecimal()))
                .setFont(PdfFontFactory.createFont(inputBean.getFontFamily(), PdfEncodings.WINANSI))
                .setFontSize(inputBean.getFontSize())

        );
        document.flush();
    }

}