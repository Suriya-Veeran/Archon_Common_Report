package com.p3solutions.archon_report_utility.event_handler;

import static com.p3solutions.archon_report_utility.builder.DividerBeanBuilder.buildDividerInputBean;
import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.beans.DividerBean;
import com.p3solutions.archon_report_utility.beans.HeaderBean;
import com.p3solutions.archon_report_utility.enums.DividerType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class HeaderEventHandler implements IEventHandler {

    private final HeaderBean headerBean;
    private Document document;

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();
        float width = pageSize.getWidth();
        float y = pageSize.getHeight() - headerBean.getTopMargin();

    DividerBean dividerBean =
        buildDividerInputBean(y, 1f, "E9E9E9", 1, DividerType.PAGE_TO_PAGE);
        headerBean.setDividerBean(dividerBean);

        try {
            PdfCanvas pdfCanvas = new PdfCanvas(page);
            Canvas canvas = new Canvas(pdfCanvas, pageSize);

            Table headerTable = new Table(UnitValue.createPercentArray(new float[]{70, 30}))
                    .setWidth(width)
                    .setFixedPosition(0,pageSize.getHeight() - 45, width)
                    .setBackgroundColor(hexaDecimalToRGB(headerBean.getBackgroundColor()));

            PdfFont font = PdfFontFactory.createFont(headerBean.getFontProgram(),
                    PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);

            Paragraph title = new Paragraph(headerBean.getTitle())
                    .setFontSize(headerBean.getFontSize())
                    .setFont(font)
                    .setFontColor(hexaDecimalToRGB(headerBean.getFontColor()))
                    .setTextAlignment(headerBean.getTextAlignment());

            Cell titleCell = new Cell().add(title)
                    .setBorder(null)
                    .setPaddingTop(headerBean.getPaddingTop())
                    .setPaddingBottom(headerBean.getPaddingBottom())
                    .setPaddingLeft(headerBean.getPaddingLeft())
                    .setBackgroundColor(hexaDecimalToRGB(headerBean.getBackgroundColor()))
                    .setVerticalAlignment(headerBean.getVerticalAlignment());
            headerTable.addCell(titleCell);

            if (headerBean.isLogoNeeded()) {
                Image logo = loadImage(headerBean.getImagePath(), headerBean.getFitWidth(), headerBean.getFitHeight());

                if (logo != null) {
                    logo.setHorizontalAlignment(headerBean.getLogoHorizontalAlignment());
                    Cell logoCell = new Cell()
                            .add(logo)
                            .setPaddingRight(headerBean.getLogoPaddingRight())
                            .setPaddingTop(headerBean.getLogoPaddingTop())
                            .setPaddingBottom(headerBean.getLogoPaddingBottom())
                            .setBackgroundColor(hexaDecimalToRGB(headerBean.getBackgroundColor()))
                            .setBorder(null);
                    headerTable.addCell(logoCell);
                } else {
                    headerTable.addCell(new Cell().setBorder(null));
                }
            } else {
                headerTable.addCell(new Cell().setBorder(null));
            }

            if (Boolean.TRUE.equals(headerBean.getIsDividerNeeded())) {
                PdfCanvas dividerCanvas = new PdfCanvas(page);
                dividerCanvas.setStrokeColor(hexaDecimalToRGB(headerBean.getDividerBean().getHexDecimal()));
                dividerCanvas.setLineWidth(headerBean.getDividerBean().getLineWidth());

                dividerCanvas.moveTo(0, y);
                dividerCanvas.lineTo(pageSize.getWidth(), y);
                dividerCanvas.closePathStroke();
            }
            canvas.add(headerTable);
            canvas.close();

        } catch (IOException e) {
            log.error("Error in HeaderEventHandler: {}", e.getMessage(), e);
        }
    }


    private Image loadImage(String imagePath, float fitWidth, float fitHeight) {
        try {
            if (imagePath == null || imagePath.isBlank()) {
                log.warn("No image path provided for logo.");
                return null;
            }

            byte[] imageBytes = Objects.requireNonNull(
                    this.getClass().getClassLoader().getResourceAsStream(imagePath)
            ).readAllBytes();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            ImageData imageData = ImageDataFactory.create(ImageIO.read(inputStream), null);
            return new Image(imageData).scaleToFit(fitWidth, fitHeight);

        } catch (IOException | NullPointerException e) {
            log.error("Failed to load image '{}': {}", imagePath, e.getMessage(), e);
            return null;
        }
    }
}
