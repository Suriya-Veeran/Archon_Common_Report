package com.p3solutions.archon_report_utility.components;

import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;
import static com.p3solutions.archon_report_utility.utils.CommonUtils.addEmptyLines;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.beans.CellInputBean;
import com.p3solutions.archon_report_utility.beans.TableBean;
import com.p3solutions.archon_report_utility.enums.FontType;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.IOException;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TableComponent implements ReportComponent {
  private TableBean inputBean;

  public void render(Document document) throws IOException {

    if (inputBean.getTableType().equals(TableType.HEADER)) {
      addEmptyLines(1, document);
    }

    if (inputBean.getParameters() != null && !inputBean.getParameters().isEmpty()) {
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
      setCellValues(inputBean, table);
      table.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
      table.setKeepTogether(inputBean.isKeepTogether());
      table.setBorder(inputBean.getBorder());
      table.setMarginLeft(-18);
      table.setMarginRight(-18);
      document.add(table);
      if (inputBean.getTableType().equals(TableType.SUMMARY)) {
        addEmptyLines(1, document);
      }
    }
  }

  private void setCellValues(TableBean inputBean, Table table) throws IOException {
    cellConfiguration(inputBean.getCellInputBean(), table, inputBean.getParameters());
  }

  private void cellConfiguration(
      CellInputBean cellInputBean, Table table, Map<String, String> parameters) throws IOException {

    Color headerColor = hexaDecimalToRGB("2C2C2C");
    Color valueColor = hexaDecimalToRGB("000000");

    PdfFont headerFont =
        cellInputBean.isValueHeader()
            ? PdfFontFactory.createFont(FontType.HELVETICA.getFontName())
            : PdfFontFactory.createFont(FontType.HELVETICA_BOLD.getFontName());

    PdfFont valueFont =
        cellInputBean.isValueHeader()
            ? PdfFontFactory.createFont(FontType.HELVETICA_BOLD.getFontName())
            : PdfFontFactory.createFont(FontType.HELVETICA.getFontName());

    for (Map.Entry<String, String> entry : parameters.entrySet()) {
      String header = entry.getKey();
      String value = entry.getValue();

      boolean isValueHeader = cellInputBean.isValueHeader();

      float headerFontSize =
          isValueHeader ? cellInputBean.getFontSize() - 1 : cellInputBean.getFontSize();
      float valueFontSize =
          isValueHeader ? cellInputBean.getFontSize() : cellInputBean.getFontSize() - 1;

      PdfFont valueFinalFont = isValueHeader ? valueFont : headerFont;

      Color backgroundColor =
          (inputBean.getTableType() == TableType.SUMMARY)
              ? cellInputBean.getBackgroundColor()
              : hexaDecimalToRGB("F9F9F9");

      Cell cell =
          new Cell()
              .add(
                  new Paragraph(new Text(header))
                      .setFont(headerFont)
                      .setFontColor(headerColor)
                      .setBorder(Border.NO_BORDER)
                      .setFontSize(headerFontSize))
              .add(
                  new Paragraph(new Text(value))
                      .setFont(valueFinalFont)
                      .setFontColor(valueColor)
                      .setBorder(Border.NO_BORDER)
                      .setFontSize(valueFontSize))
              .setBackgroundColor(backgroundColor)
              .setBorder(cellInputBean.getBorder())
              .setTextAlignment(cellInputBean.getTextAlignment())
              .setPadding(0)
              .setVerticalAlignment(cellInputBean.getVerticalAlignment());

      table.addCell(cell);
    }
  }
}
