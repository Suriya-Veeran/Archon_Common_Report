package com.p3solutions.archon_report_utility.components;

import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;
import static com.p3solutions.archon_report_utility.utils.CommonUtils.addEmptyLines;
import static com.p3solutions.archon_report_utility.utils.CommonUtils.configTable;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.beans.CellInputBean;
import com.p3solutions.archon_report_utility.beans.TableBean;

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

  public static final String LIGHT_GREEN_HEXA_DECIMAL = "007D2B";

  public static final String RED_HEXA_DECIMAL = "D60000";

  public void render(Document document) throws IOException {

    if (inputBean.getTableType().equals(TableType.HEADER)) {
      addEmptyLines(2, document);
    }

    if (Boolean.TRUE.equals(inputBean.getTableType().equals(TableType.JOB_STATUS))
        && Boolean.TRUE.equals(inputBean.getIsJobStatusTableNeeded())) {
      Table jobStatusTable = configTable(inputBean);
      setJobStatusCellValue(inputBean, jobStatusTable);
      jobStatusTable.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
      jobStatusTable.setKeepTogether(inputBean.isKeepTogether());
      jobStatusTable.setMarginLeft(inputBean.getMarginBean().getLeftMargin());
      jobStatusTable.setMarginRight(inputBean.getMarginBean().getRightMargin());
      jobStatusTable.setMarginTop(-10f);
      document.add(jobStatusTable);
      addEmptyLines(1, document);
    }

    if (inputBean.getParameters() != null && !inputBean.getParameters().isEmpty()) {
      Table table = configTable(inputBean);
      table.setMarginLeft(-36);
      table.setMarginTop(-5);
      table.setWidth(PageSize.A4.getWidth());
      setCellValues(inputBean, table);
      table.setBorder(inputBean.getBorder());
      document.add(table);
      if (inputBean.getTableType().equals(TableType.SUMMARY)) {
        addEmptyLines(1, document);
      }
    }
  }

  private void setJobStatusCellValue(TableBean inputBean, Table jobStatusTable) throws IOException {

    Cell jobStatusCell = new Cell(1, 3);

    Color fontColor =
        inputBean.getJobStatusInputBean().getJobStatus().getStatus().equalsIgnoreCase("Success")
            ? hexaDecimalToRGB(LIGHT_GREEN_HEXA_DECIMAL)
            : hexaDecimalToRGB(RED_HEXA_DECIMAL);

    jobStatusCell.add(
        new Paragraph(new Text("Job Status: " + inputBean.getJobStatusInputBean().getJobStatus().getStatus()))
            .setFont(PdfFontFactory.createFont("src/main/resources/fonts/Roboto-BlackItalic.ttf",
                    PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED))
            .setFontSize(7)
            .setFontColor(hexaDecimalToRGB("FFFFFF"))
            .setTextAlignment(TextAlignment.LEFT)
            .setMarginLeft(4f));
    jobStatusCell.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
    jobStatusCell.setBackgroundColor(fontColor);
    jobStatusCell.setBorder(Border.NO_BORDER);
    jobStatusTable.addCell(jobStatusCell);

    if(!inputBean.getJobStatusInputBean().getJobStatus().getStatus().equalsIgnoreCase("Success")) {
      Cell errorCell = new Cell(1, 3);
      errorCell.add(new Paragraph(new Text("Error Message: " + inputBean.getJobStatusInputBean().getErrorMessage()))
              .setFont(PdfFontFactory.createFont("src/main/resources/fonts/Roboto-BlackItalic.ttf",
                      PdfEncodings.IDENTITY_H,
                      PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED))
              .setFontSize(7)
              .setFontColor(fontColor)
              .setTextAlignment(TextAlignment.LEFT)
              .setMarginLeft(4f));
      errorCell.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
      errorCell.setBackgroundColor(hexaDecimalToRGB("FFEAEA"));
      errorCell.setBorder(Border.NO_BORDER);
      jobStatusTable.addCell(errorCell);
    }

    jobStatusTable.setFixedLayout();
  }

  private void setCellValues(TableBean inputBean, Table table) throws IOException {
    cellConfiguration(inputBean.getCellInputBean(), table, inputBean.getParameters());
  }

  private void cellConfiguration(
      CellInputBean cellInputBean, Table table, Map<String, String> parameters) throws IOException {

    Color headerColor = hexaDecimalToRGB("2C2C2C");
    Color valueColor = hexaDecimalToRGB("000000");

    String regularFontPath = "src/main/resources/fonts/Roboto-Regular.ttf";
    String mediumFontPath = "src/main/resources/fonts/Roboto-Medium.ttf";

    String headerFontPath = cellInputBean.isValueHeader() ? regularFontPath : mediumFontPath;
    String valueFontPath = cellInputBean.isValueHeader() ? mediumFontPath : regularFontPath;

    PdfFont headerFont = PdfFontFactory.createFont(headerFontPath, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
    PdfFont valueFont = PdfFontFactory.createFont(valueFontPath, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);


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
                          .setPaddingLeft(17)
                      .setBorder(Border.NO_BORDER)
                      .setFontSize(headerFontSize))
              .add(
                  new Paragraph(new Text(value))
                      .setFont(valueFinalFont)
                      .setFontColor(valueColor)
                          .setPaddingLeft(17)
                      .setBorder(Border.NO_BORDER)
                      .setFontSize(valueFontSize))
              .setBackgroundColor(backgroundColor)
              .setBorder(cellInputBean.getBorder())
              .setTextAlignment(cellInputBean.getTextAlignment())
              .setVerticalAlignment(cellInputBean.getVerticalAlignment());

      table.addCell(cell);
    }
  }
}
