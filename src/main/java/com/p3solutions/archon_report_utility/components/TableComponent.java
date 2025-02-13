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
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
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
      jobStatusTable.setMarginTop(inputBean.getMarginBean().getTopMargin());
      document.add(jobStatusTable);
      addEmptyLines(1, document);
    }

    if (inputBean.getParameters() != null && !inputBean.getParameters().isEmpty()) {
      Table table = configTable(inputBean);
      table.setMarginLeft(inputBean.getMarginLeft());
      table.setMarginTop(inputBean.getMarginTop());
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

    Cell jobStatusCell = new Cell(inputBean.getJobStatusInputBean().getRowSpan(),
            inputBean.getJobStatusInputBean().getColumnSpan());

    Color fontColor =
        inputBean.getJobStatusInputBean().getJobStatus().getStatus().equalsIgnoreCase("Success")
            ? hexaDecimalToRGB(inputBean.getSuccessFontColor())
            : hexaDecimalToRGB(inputBean.getErrorFontColor());

    jobStatusCell.add(
        new Paragraph(new Text(inputBean.getJobStatusInputBean().getHeader()
                + inputBean.getJobStatusInputBean().getJobStatus().getStatus()))
            .setFont(PdfFontFactory.createFont(inputBean.getJobStatusInputBean().getFontProgram(),
                    PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED))
            .setFontSize(inputBean.getJobStatusInputBean().getFontSize())
            .setFontColor(hexaDecimalToRGB(inputBean.getJobStatusInputBean().getJobStatusFontColor()))
            .setTextAlignment(inputBean.getJobStatusInputBean().getTextAlignment())
            .setMarginLeft(inputBean.getJobStatusInputBean().getMarginLeft()));
    jobStatusCell.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
    jobStatusCell.setBackgroundColor(fontColor);
    jobStatusCell.setBorder(inputBean.getJobStatusInputBean().getBorder());
    jobStatusTable.addCell(jobStatusCell);

    if(!inputBean.getJobStatusInputBean().getJobStatus().getStatus().equalsIgnoreCase("Success")) {
      Cell errorCell = new Cell(inputBean.getJobStatusInputBean().getRowSpan(),
              inputBean.getJobStatusInputBean().getColumnSpan());
      errorCell.add(new Paragraph(new Text(inputBean.getJobStatusInputBean().getErrorHeader()
              + inputBean.getJobStatusInputBean().getErrorMessage()))
              .setFont(PdfFontFactory.createFont(inputBean.getJobStatusInputBean().getErrorFontProgram(),
                      PdfEncodings.IDENTITY_H,
                      PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED))
              .setFontSize(inputBean.getJobStatusInputBean().getFontSize())
              .setFontColor(fontColor)
              .setTextAlignment(inputBean.getJobStatusInputBean().getTextAlignment())
              .setMarginLeft(inputBean.getJobStatusInputBean().getMarginLeft()));
      errorCell.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
      errorCell.setBackgroundColor(hexaDecimalToRGB(inputBean.getJobStatusInputBean().getErrorBackgroundColor()));
      errorCell.setBorder(inputBean.getJobStatusInputBean().getBorder());
      jobStatusTable.addCell(errorCell);
    }

    jobStatusTable.setFixedLayout();
  }

  private void setCellValues(TableBean inputBean, Table table) throws IOException {
    cellConfiguration(inputBean.getCellInputBean(), table, inputBean.getParameters());
  }

  private void cellConfiguration(
      CellInputBean cellInputBean, Table table, Map<String, String> parameters) throws IOException {

    Color headerColor = hexaDecimalToRGB(cellInputBean.getCellHeaderColor());
    Color valueColor = hexaDecimalToRGB(cellInputBean.getCellValueColor());

    String headerFontPath = cellInputBean.isValueHeader() ? cellInputBean.getRobotoRegularFont() : cellInputBean.getRobotMediumFont();
    String valueFontPath = cellInputBean.isValueHeader() ? cellInputBean.getRobotMediumFont() : cellInputBean.getRobotoRegularFont();

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
              : hexaDecimalToRGB(cellInputBean.getHeaderCellBackgroundColor());

      Cell cell =
          new Cell()
              .add(
                  new Paragraph(new Text(header))
                      .setFont(headerFont)
                      .setFontColor(headerColor)
                          .setPaddingLeft(cellInputBean.getCellPaddingLeft())
                      .setBorder(cellInputBean.getBorder())
                      .setFontSize(headerFontSize))
              .add(
                  new Paragraph(new Text(value))
                      .setFont(valueFinalFont)
                      .setFontColor(valueColor)
                          .setPaddingLeft(cellInputBean.getCellPaddingLeft())
                      .setBorder(cellInputBean.getBorder())
                      .setFontSize(valueFontSize))
              .setBackgroundColor(backgroundColor)
              .setBorder(cellInputBean.getBorder())
              .setTextAlignment(cellInputBean.getTextAlignment())
              .setVerticalAlignment(cellInputBean.getVerticalAlignment());

      table.addCell(cell);
    }
  }
}
