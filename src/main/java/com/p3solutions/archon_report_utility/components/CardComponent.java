package com.p3solutions.archon_report_utility.components;

import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;
import static com.p3solutions.archon_report_utility.utils.CommonUtils.addEmptyLines;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.beans.CardBean;
import com.p3solutions.archon_report_utility.enums.CardType;
import com.p3solutions.archon_report_utility.enums.FontType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.IOException;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardComponent implements ReportComponent {

  private CardBean inputBean;

  @Override
  public void render(Document document) throws IOException {
    if (inputBean.getCardType() == CardType.MULTIPLE_DETAILS_INFO) {
      renderMultipleDetailsCard(document);
    } else if (inputBean.getCardType() == CardType.SINGLE_DETAILS_INFO) {
      renderSingleDetailsCard(document);
    }
    addEmptyLines(1, document);
  }

  /** Renders a Multiple Details Info Card (Table-style card). */
  private void renderMultipleDetailsCard(Document document) throws IOException {
    Color cardBackground = hexaDecimalToRGB("E8EDF7"); // Light grey background
    Color borderColor = hexaDecimalToRGB("DCDCDC"); // Light border color
    Color successColor = hexaDecimalToRGB("007D2B");

    Color headerColor =
        hexaDecimalToRGB(inputBean.getCellInputBean().isValueHeader() ? "2C2C2C" : "000000");
    Color valueColor =
        hexaDecimalToRGB(inputBean.getCellInputBean().isValueHeader() ? "000000" : "2C2C2C");

    String valueFontPath = "src/main/resources/fonts/Roboto-Regular.ttf";
    String headerFontPath = "src/main/resources/fonts/Roboto-Medium.ttf";

    PdfFont headerFont = PdfFontFactory.createFont(headerFontPath, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
    PdfFont valueFont = PdfFontFactory.createFont(valueFontPath, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);


    if (inputBean.getParameters() != null && !inputBean.getParameters().isEmpty()) {
      Table parameterTable = new Table(3);
      parameterTable.setWidth(UnitValue.createPercentValue(100));
      parameterTable.setFixedLayout();
      parameterTable.setBorderBottom(new SolidBorder(borderColor, 1));
      parameterTable.setBorderLeft(new SolidBorder(borderColor, 1));
      parameterTable.setBorderRight(new SolidBorder(borderColor, 1));
      parameterTable.setMarginLeft(-18f);
      parameterTable.setMarginRight(-18f);
      parameterTable.setKeepTogether(true);

      Paragraph paragraph = new Paragraph();
      paragraph.setFont(headerFont).setTextAlignment(TextAlignment.LEFT);
      String[] parts = inputBean.getHeader().split(",", 2);
      paragraph.add(new Paragraph(parts[0]).setFontSize(10));

      if (parts.length > 1) {
        paragraph.add("\n").add(new Paragraph(parts[1].trim()).setFontSize(8));

      }

      Cell paragraphCell = new Cell(1,3).add(paragraph)
              .setBackgroundColor(cardBackground)
              .setBorder(new SolidBorder(borderColor, 1));
      paragraphCell.setKeepTogether(true);
      parameterTable.addCell(paragraphCell);
      for (Map.Entry<String, String> entry : inputBean.getParameters().entrySet()) {
        String header = entry.getKey();
        String value = entry.getValue();

        boolean isValueHeader = inputBean.getCellInputBean().isValueHeader();

        float headerFontSize =
            isValueHeader
                ? inputBean.getCellInputBean().getFontSize() - 1
                : inputBean.getCellInputBean().getFontSize();
        float valueFontSize =
            isValueHeader
                ? inputBean.getCellInputBean().getFontSize()
                : inputBean.getCellInputBean().getFontSize() - 1;

        PdfFont valueFinalFont = isValueHeader ? valueFont : headerFont;

        Color valueFinalColor = "Success".equalsIgnoreCase(value) ? successColor : valueColor;

        Cell cell =
            new Cell()
                .add(new Paragraph(new Text(header))
                        .setFont(headerFont)
                        .setFontColor(headerColor)
                        .setFontSize(headerFontSize))
                .add(
                    new Paragraph(new Text(value))
                        .setFont(valueFinalFont)
                        .setFontColor(valueFinalColor)
                        .setFontSize(valueFontSize))
                .setBackgroundColor(inputBean.getCellInputBean().getBackgroundColor())
                .setBorder(inputBean.getCellInputBean().getBorder())
                .setTextAlignment(inputBean.getCellInputBean().getTextAlignment())
                .setVerticalAlignment(inputBean.getCellInputBean().getVerticalAlignment());

        cell.setKeepTogether(true);
        parameterTable.addCell(cell);
        parameterTable.setBorder(new SolidBorder(borderColor, 1));
      }
      document.add(parameterTable);
    }
  }

  /** Renders a Single Details Info Card (Header + Date + Description). */
  private void renderSingleDetailsCard(Document document) throws IOException {
    addEmptyLines(2, document);
    Color cardBackground = hexaDecimalToRGB("DFEAFF"); // Light grey background

    String valueFontPath = "src/main/resources/fonts/Roboto-Regular.ttf";
    String headerFontPath = "src/main/resources/fonts/Roboto-Medium.ttf";

    PdfFont headerFont = PdfFontFactory.createFont(headerFontPath, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
    PdfFont valueFont = PdfFontFactory.createFont(valueFontPath, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);

    Table headerTable = new Table(2);
    headerTable.setWidth(UnitValue.createPercentValue(100));
    headerTable.setBackgroundColor(cardBackground);
    headerTable.setBorderTop(new SolidBorder(hexaDecimalToRGB("DCDCDC"), 1));
    headerTable.setBorderLeft(new SolidBorder(hexaDecimalToRGB("DCDCDC"), 1));
    headerTable.setBorderRight(new SolidBorder(hexaDecimalToRGB("DCDCDC"), 1));
    headerTable.addCell(
        new Cell()
            .add(
                new Paragraph(inputBean.getHeader())
                    .setFont(headerFont)
                    .setFontColor(hexaDecimalToRGB("030303"))
                    .setFontSize(10))
            .setBorder(Border.NO_BORDER)
            .setPadding(5)
            .setTextAlignment(TextAlignment.LEFT));

    headerTable.addCell(
        new Cell()
            .add(
                new Paragraph(inputBean.getGeneratedTime())
                    .setFont(headerFont)
                    .setFontColor(hexaDecimalToRGB("030303"))
                    .setFontSize(10))
            .setBorder(Border.NO_BORDER)
            .setPadding(5)
            .setTextAlignment(TextAlignment.RIGHT));
    headerTable.setMarginLeft(-18f);
    headerTable.setMarginRight(-18f);
    document.add(headerTable);

    Table contentTable = new Table(1);
    contentTable.setWidth(UnitValue.createPercentValue(100));
    contentTable.setBorderBottom(new SolidBorder(cardBackground, 1));
    contentTable.setBorderLeft(new SolidBorder(cardBackground, 1));
    contentTable.setBorderRight(new SolidBorder(cardBackground, 1));

    Cell contentCell =
        new Cell()
            .add(
                new Paragraph(inputBean.getContent())
                    .setFont(valueFont)
                    .setFontSize(12)
                    .setFontColor(hexaDecimalToRGB("000000")))
            .setBorder(Border.NO_BORDER)
            .setPadding(10);
    contentTable.setMarginLeft(-18f);
    contentTable.setMarginRight(-18f);
    contentTable.addCell(contentCell);
    document.add(contentTable);
  }
}
