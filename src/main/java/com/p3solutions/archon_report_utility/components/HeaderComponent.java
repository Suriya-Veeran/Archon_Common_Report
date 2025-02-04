package com.p3solutions.archon_report_utility.components;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.p3solutions.archon_report_utility.beans.HeaderBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;

import static com.p3solutions.archon_report_utility.utils.CommonUtils.addEmptyLines;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class HeaderComponent implements ReportComponent {
    private HeaderBean inputBean;

    public void render(Document document) throws IOException {
        int numberOfPages = document.getPdfDocument().getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            applyHeaderToPage(document, i);
        }
        addEmptyLines(91, document);
        document.flush();
    }

    private void applyHeaderToPage(Document document, int pageIndex) throws IOException {
        Rectangle pageSize = document.getPdfDocument().getPage(pageIndex).getPageSize();
        float width = pageSize.getWidth();
        float height = pageSize.getHeight();

        Paragraph title = new Paragraph()
                .setFontSize(inputBean.getFontSize())
                .setTextAlignment(inputBean.getTextAlignment())
                .setVerticalAlignment(inputBean.getVerticalAlignment())
                .setFont(PdfFontFactory.createFont(inputBean.getFont().getFontName()))
                .setTextAlignment(inputBean.getTextAlignment()
                ).add(inputBean.getTitle());

        document.showTextAligned(title, inputBean.getLeftMargin(), height - inputBean.getTopMargin(), inputBean.getTextAlignment());

        if (inputBean.isLogoNeeded()) {
            Image logo = loadImage(inputBean.getImagePath(), inputBean.getFitWidth(), inputBean.getFitHeight());
            if (logo != null) {
                document.showTextAligned(new Paragraph().add(logo),
                        width - inputBean.getFitWidth() - 17,
                        height - inputBean.getLogoHeight(),
                        inputBean.getLogoTextAlignment());
            }
        }
    }

    private Image loadImage(String imagePath, float fitWidth, float fitHeight) {
        try (ByteArrayInputStream inputStream =
                     new ByteArrayInputStream(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(imagePath)).readAllBytes())) {
            ImageData imageData = ImageDataFactory.create(ImageIO.read(inputStream), null);
            return new Image(imageData).scaleToFit(fitWidth, fitHeight);
        } catch (IOException e) {
            log.info("Error loading image: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to load Image : ", e);
        }
    }

}
