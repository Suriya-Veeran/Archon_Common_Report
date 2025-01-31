package com.p3solutions.archon_report_utility.components;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.FooterBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FooterComponent implements ReportComponent {
    private FooterBean inputBean;

    public void render(Document document) {
        if (inputBean == null) {
            return;
        }

        // Get the total number of pages to apply footer to
        int numberOfPages = document.getPdfDocument().getNumberOfPages();

        // Loop through each page and apply footer
        for (int i = 1; i <= numberOfPages; i++) {
            applyFooterToPage(document, i, numberOfPages);
        }

        document.flush();
    }

    private void applyFooterToPage(Document document, int pageIndex, int totalPages) {
        Rectangle pageSize = document.getPdfDocument().getPage(pageIndex).getPageSize();
        float width = pageSize.getWidth();

        Rectangle rectangle =
                new Rectangle(inputBean.getRectangleWidth(), inputBean.getRectangleHeight());

        Paragraph footerText = createFooterParagraph(inputBean, rectangle);
        document.showTextAligned(
                footerText,
                inputBean.getTextAlignmentWidth(),
                inputBean.getTextAlignmentHeight(),
                pageIndex,
                inputBean.getTextAlignment(),
                inputBean.getVerticalAlignment(),
                0);

        String pageText = "Page " + pageIndex + " Of " + totalPages;
        Paragraph pageNumberParagraph =
                new Paragraph(pageText)
                        .setFontSize(inputBean.getFontSize())
                        .setFontColor(inputBean.getFontColor())
                        .setTextAlignment(TextAlignment.LEFT)
                        .setVerticalAlignment(VerticalAlignment.BOTTOM);
        document.showTextAligned(
                pageNumberParagraph,
                width - 60,
                inputBean.getPageAlignmentHeight(),
                pageIndex,
                inputBean.getTextAlignment(),
                inputBean.getVerticalAlignment(),
                0);

    }

    private Paragraph createFooterParagraph(FooterBean footerInputBean, Rectangle rectangle) {

        PdfLinkAnnotation annotation = new PdfLinkAnnotation(rectangle);
        annotation.setBorder(new PdfArray(new int[]{0, 0, 0}));

        PdfAction action = PdfAction.createURI(footerInputBean.getUrl());
        annotation.setAction(action);

        Link link = new Link(footerInputBean.getLinkText(), annotation);
        link.setBorder(Border.NO_BORDER);

        return new Paragraph()
                .setWidth(UnitValue.createPercentValue(footerInputBean.getWidth()))
                .setTextAlignment(footerInputBean.getTextAlignment())
                .setVerticalAlignment(footerInputBean.getVerticalAlignment())
                .setFontSize(footerInputBean.getFontSize())
                .setBorder(footerInputBean.getBorder())
                .setFontColor(footerInputBean.getFontColor())
                .add(footerInputBean.getCopyRightText())
                .add(link)
                .add(footerInputBean.getAllRightsReservedText());
    }
}
