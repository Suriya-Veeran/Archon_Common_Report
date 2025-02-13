package com.p3solutions.archon_report_utility.components;

import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.p3solutions.archon_report_utility.beans.SummaryBean;
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
public class SummaryComponent implements ReportComponent {
    private SummaryBean inputBean;

    public void render(Document document) throws IOException {
    document.add(
        new Paragraph(inputBean.getSummaryText())
            .setTextAlignment(inputBean.getTextAlignment())
            .setFontColor(hexaDecimalToRGB(inputBean.getHexaDecimal()))
            .setFont(PdfFontFactory.createFont(inputBean.getFontFamily(), PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED))
            .setFontSize(inputBean.getFontSize())
            .setPaddingLeft(inputBean.getPaddingLeft())
                .setPaddingTop(inputBean.getPaddingTop())
            .setBorderBottomLeftRadius(new BorderRadius(inputBean.getBorderBean().getBorderBottomLeftRadius())));
        document.flush();
    }

}
