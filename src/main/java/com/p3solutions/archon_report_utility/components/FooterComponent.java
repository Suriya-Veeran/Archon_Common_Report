package com.p3solutions.archon_report_utility.components;

import static com.p3solutions.archon_report_utility.builder.DividerBeanBuilder.buildDividerInputBean;
import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.DividerBean;
import com.p3solutions.archon_report_utility.beans.FooterBean;
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
public class FooterComponent implements ReportComponent {
    private FooterBean inputBean;

    public void render(Document document) {
        if (inputBean == null) {
            return;
        }

        int numberOfPages = document.getPdfDocument().getNumberOfPages();

        for (int i = 1; i <= numberOfPages; i++) {
            applyFooterToPage(document, i, numberOfPages);
        }

        document.flush();
    }

    private void applyFooterToPage(Document document, int pageIndex, int totalPages) {

        DividerBean dividerBean = buildDividerInputBean(30L, 1L, "E2E2E2", 1, DividerType.PAGE_TO_PAGE);
        inputBean.setDividerBean(dividerBean);

        PdfPage page = document.getPdfDocument().getPage(pageIndex);
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
                        .setFontColor(hexaDecimalToRGB(inputBean.getFontColor()))
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

        if (Boolean.TRUE.equals(inputBean.getIsDividerNeeded())) {
            PdfCanvas dividerCanvas = new PdfCanvas(page);
            dividerCanvas.setStrokeColor(hexaDecimalToRGB(inputBean.getDividerBean().getHexDecimal()));
            dividerCanvas.setLineWidth(inputBean.getDividerBean().getLineWidth());

            float dividerYPosition = 30;
            dividerCanvas.moveTo(0, dividerYPosition);
            dividerCanvas.lineTo(pageSize.getWidth(), dividerYPosition);
            dividerCanvas.closePathStroke();
        }

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
                .setFontColor(hexaDecimalToRGB(footerInputBean.getFontColor()))
                .add(footerInputBean.getCopyRightText())
                .add(link)
                .add(footerInputBean.getAllRightsReservedText());
    }
}
