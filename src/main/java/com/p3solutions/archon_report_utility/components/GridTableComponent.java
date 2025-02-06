package com.p3solutions.archon_report_utility.components;

import static com.itextpdf.io.font.constants.StandardFonts.*;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.BLUE_BG_COLOR;
import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
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
      Table table = null;
      switch (inputBean.getType()) {
        case POINT_COLUMN_WIDTH:
          table = new Table(UnitValue.createPercentArray(inputBean.getPointColumnWidth()));
          break;
        case NUMBER_OF_COLUMNS:
          table = new Table(inputBean.getNumberOfColumns());
          break;
        default:
          throw new IllegalArgumentException("Unsupported type: " + inputBean.getType());
      }

      table.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
      table.setKeepTogether(inputBean.isKeepTogether());
      table.setMarginLeft(-18f);
      table.setMarginRight(-18f);
      table.setBorder(new SolidBorder(hexaDecimalToRGB("DCDCDC"), 1));

      Map<String, List<String>> parameterMap = inputBean.getParameterMap();
      for (String header : parameterMap.keySet()) {
        Cell headerCell =
            new Cell()
                .add(new Paragraph(header).setFont(PdfFontFactory.createFont(HELVETICA_BOLD)))
                .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(10)
                .setBackgroundColor(hexaDecimalToRGB(BLUE_BG_COLOR))
                .setPadding(5);
        table.addCell(headerCell);
      }

      int rowCount = parameterMap.values().iterator().next().size();
      for (int i = 0; i < rowCount; i++) {
        for (List<String> values : parameterMap.values()) {

          String value = values.get(i);
          Color fontColor;
          if (value.equalsIgnoreCase("Disposed Success") || value.equalsIgnoreCase("Success")) {
            fontColor = hexaDecimalToRGB("007D2B");
          } else if (value.equalsIgnoreCase("Disposed Failure") || value.equalsIgnoreCase("Failed")) {
            fontColor = hexaDecimalToRGB("D60000");
          } else {
            fontColor = hexaDecimalToRGB("000000");
          }

          Cell cell =
              new Cell()
                  .add(new Paragraph(values.get(i)))
                  .setTextAlignment(TextAlignment.LEFT)
                  .setBorderTop(Border.NO_BORDER)
                  .setBorderLeft(Border.NO_BORDER)
                  .setBorderRight(Border.NO_BORDER)
                      .setFontColor(fontColor)
                  .setBorderBottom(new SolidBorder(hexaDecimalToRGB("DCDCDC"), 0.5f))
                  .setPadding(5);
          table.addCell(cell);
        }
      }
      document.add(table);
    }
  }
}
