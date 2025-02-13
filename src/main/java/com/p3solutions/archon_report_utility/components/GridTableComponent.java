package com.p3solutions.archon_report_utility.components;


import static com.p3solutions.archon_report_utility.constants.ColorConstants.BLUE_BG_COLOR;
import static com.p3solutions.archon_report_utility.constants.SpecialCharacterConstants.COMMA;
import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;
import static com.p3solutions.archon_report_utility.utils.CommonUtils.configTable;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.beans.GridTableBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GridTableComponent implements ReportComponent {

  private GridTableBean inputBean;

  @Override
  public void render(Document document) throws IOException {
    if (inputBean.getParameterMap() != null && !inputBean.getParameterMap().isEmpty()) {

      Table table = configTable(inputBean);
      table.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
      table.setKeepTogether(inputBean.isKeepTogether());
      table.setMarginLeft(inputBean.getMarginBean().getLeftMargin());
      table.setMarginRight(inputBean.getMarginBean().getRightMargin());
      table.setBorder(new SolidBorder(hexaDecimalToRGB(inputBean.getBorderBean().getSolidBorderColor()),
              inputBean.getBorderBean().getSolidBorderWidth()));

      Map<String, List<String>> parameterMap = inputBean.getParameterMap();
      for (String header : parameterMap.keySet()) {

        Paragraph paragraph = new Paragraph();
        paragraph.setFont(
                PdfFontFactory.createFont(
                        inputBean.getFontProgram(),
                        PdfEncodings.IDENTITY_H,
                        PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED));

        String[] parts = header.split(COMMA, 2);
        paragraph.add(new Paragraph(parts[0])
                .setBackgroundColor(hexaDecimalToRGB("953553"))
                .setFontSize(inputBean.getCellInputBean().getFontSize()));

        if (parts.length > 1) {
          paragraph
              .add("\n")
              .add(
                  new Paragraph(parts[1].trim())
                          .setMarginTop(-1000f)
                          .setPaddingTop(20f)
                          .setBackgroundColor(hexaDecimalToRGB("EE4B2B"))
                      .setFontSize(inputBean.getCellInputBean().getFontSize()));
          }

        Cell headerCell =
            new Cell()
                .add(paragraph)
                .setBorder(inputBean.getCellInputBean().getBorder())
                .setTextAlignment(inputBean.getCellInputBean().getTextAlignment())
                .setFontSize(inputBean.getCellInputBean().getFontSize())
                .setBackgroundColor(hexaDecimalToRGB(BLUE_BG_COLOR))
                .setPadding(inputBean.getPadding());
        table.addCell(headerCell);
      }

      int rowCount = parameterMap.values().iterator().next().size();
      for (int i = 0; i < rowCount; i++) {
        for (List<String> values : parameterMap.values()) {
          String originalValue = values.get(i);
          Color fontColor = retrieveCellFontColor(originalValue, inputBean);

          Paragraph paragraph = new Paragraph();
          String[] parts = originalValue.split(COMMA, 2);
          paragraph.add(new Paragraph(parts[0])
                  .setFontSize(inputBean.getCellInputBean().getFontSize()));

          if (parts.length > 1) {
            paragraph.add("\n")
                    .add(new Paragraph(parts[1].trim())
                            .setPaddingLeft(-10)
                    .setFontSize(inputBean.getCellInputBean().getFontSize() - 2f));

          }

          Cell cell =
              new Cell()
                  .add(paragraph)
                  .setBorderTop(inputBean.getBorderBean().getBorderTop())
                  .setBorderLeft(inputBean.getBorderBean().getBorderLeft())
                  .setBorderRight(inputBean.getBorderBean().getBorderRight())
                  .setFontColor(fontColor)
                  .setBorderBottom(new SolidBorder(hexaDecimalToRGB(inputBean.getBorderBean().getSolidBorderBottomColor()),
                          inputBean.getBorderBean().getSolidBorderWidth()))
                  .setPadding(inputBean.getPadding());

          table.addCell(cell);
        }
      }
      document.add(table);
    }
  }

  private static Color retrieveCellFontColor(String value,
                                             GridTableBean inputBean) {
    Color fontColor;
    if (value.equalsIgnoreCase("Disposed Success") || value.equalsIgnoreCase("Success")) {
      fontColor = hexaDecimalToRGB(inputBean.getSuccessColor());
    } else if (value.equalsIgnoreCase("Disposed Failure") || value.equalsIgnoreCase("Failed")) {
      fontColor = hexaDecimalToRGB(inputBean.getFailureColor());
    } else {
      fontColor = hexaDecimalToRGB(inputBean.getFontColor());
    }

    return fontColor;
  }
}
