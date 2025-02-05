package com.p3solutions.archon_report_utility.event_handler;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
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
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.HeaderBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class HeaderEventHandler implements IEventHandler {

//    private final HeaderBean headerBean;
//    private Document document;
//
//    @Override
//    public void handleEvent(Event event) {
//        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
//        PdfPage page = docEvent.getPage();
//        Rectangle pageSize = page.getPageSize();
//        float width = pageSize.getWidth();
//        float height = pageSize.getHeight();
//        float x = headerBean.getLeftMargin();
//        float y = height - headerBean.getTopMargin();
//
//        try {
//            Paragraph title = new Paragraph(headerBean.getTitle())
//                    .setFontSize(headerBean.getFontSize())
//                    .setFont(PdfFontFactory.createFont(headerBean.getFont().getFontName()))
//                    .setTextAlignment(headerBean.getTextAlignment());
//
//            document.showTextAligned(title, x, y-10, headerBean.getTextAlignment());
//
//            // Logo (if needed)
//            if (headerBean.isLogoNeeded()) {
//                Image logo = loadImage(headerBean.getImagePath(), headerBean.getFitWidth(), headerBean.getFitHeight());
//                if (logo != null) {
//                    document.showTextAligned(new Paragraph().add(logo),
//                            width - headerBean.getFitWidth() - 17,
//                            y - headerBean.getTopMargin(),  // Adjusting position based on header height
//                            headerBean.getLogoTextAlignment());
//                }
//            }
//        } catch (IOException e) {
//            log.error("Error in HeaderEventHandler: {}", e.getMessage());
//        }
//    }
//
//    private Image loadImage(String imagePath, float fitWidth, float fitHeight) {
//        try (ByteArrayInputStream inputStream =
//                     new ByteArrayInputStream(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imagePath)).readAllBytes())) {
//            ImageData imageData = ImageDataFactory.create(ImageIO.read(inputStream), null);
//            return new Image(imageData).scaleToFit(fitWidth, fitHeight);
//        } catch (IOException e) {
//            log.info("Error loading image: {}", e.getMessage());
//            throw new IllegalArgumentException("Failed to load Image : ", e);
//        }
//    }

    private final HeaderBean headerBean;
    private Document document;

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();

        float width = pageSize.getWidth();
        float y = pageSize.getHeight() - headerBean.getTopMargin(); // Header position at the top

        try {
            PdfCanvas pdfCanvas = new PdfCanvas(page);
            Canvas canvas = new Canvas(pdfCanvas, pageSize);

            Table headerTable = new Table(UnitValue.createPercentArray(new float[]{80, 20})).useAllAvailableWidth();

            Paragraph title = new Paragraph(headerBean.getTitle())
                    .setFontSize(headerBean.getFontSize())
                    .setFont(PdfFontFactory.createFont(headerBean.getFont().getFontName()))
                    .setTextAlignment(TextAlignment.LEFT);

            Cell titleCell = new Cell().add(title).setBorder(null)
                    .setMarginLeft(-18)
                    .setMarginTop(10)
                    .setPaddingBottom(5)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE);
            headerTable.addCell(titleCell);

            if (headerBean.isLogoNeeded()) {
                Image logo = loadImage(headerBean.getImagePath(), headerBean.getFitWidth(), headerBean.getFitHeight());
                if (logo != null) {
                    logo.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                    Cell logoCell = new Cell().add(logo).setBorder(null).setTextAlignment(TextAlignment.RIGHT)
                            .setPaddingBottom(5);
                    headerTable.addCell(logoCell);
                } else {
                    headerTable.addCell(new Cell().setBorder(null)); // Empty cell for alignment
                }
            } else {
                headerTable.addCell(new Cell().setBorder(null)); // Empty cell if no logo
            }

            canvas.add(headerTable.setFixedPosition(18, y - 20, width - 42));
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
