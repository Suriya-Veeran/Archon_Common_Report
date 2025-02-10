package com.p3solutions.archon_report_utility.helpers;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.*;
import com.p3solutions.archon_report_utility.beans.TableBean;
import lombok.experimental.UtilityClass;

import static com.p3solutions.archon_report_utility.utils.CommonUtils.addCellContent;
import static com.p3solutions.archon_report_utility.utils.CommonUtils.configCell;

@UtilityClass
public class TableHelper {

  public void setTableWidth(Table table, float width) {
    table.setWidth(UnitValue.createPercentValue(width));
  }

  public void setKeepTogether(Table table, Boolean keepTogether) {
    table.setKeepTogether(keepTogether);
  }

  public void setMargin(
      Table table, float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
    table.setMarginLeft(leftMargin);
    table.setMarginTop(topMargin);
    table.setMarginRight(rightMargin);
    table.setMarginBottom(bottomMargin);
  }

  public void setMarginLeftToRight(Table table, float leftMargin, float rightMargin) {
    table.setMarginLeft(leftMargin);
    table.setMarginRight(rightMargin);
  }

  public void setMarginTopToBottom(Table table, float topMargin, float bottomMargin) {
    table.setMarginTop(topMargin);
    table.setMarginBottom(bottomMargin);
  }

  public void setMarginLeft(Table table, int marginLeft) {
    table.setMarginLeft(marginLeft);
  }

  public void setMarginRight(Table table, int marginRight) {
    table.setMarginRight(marginRight);
  }

  public void setMarginTop(Table table, int marginTop) {
    table.setMarginTop(marginTop);
  }

  public void setMarginBottom(Table table, int marginBottom) {
    table.setMarginBottom(marginBottom);
  }

  public void setFixedLayout(Table table, boolean fixedLayout) {
    if (fixedLayout) {
      table.setFixedLayout();
    }
  }

  public void addCell(Table table, Cell cell) {
    table.addCell(cell);
  }

  public void addCell(Table table, Image image) {
    table.addCell(image);
  }

  public void addCell(Table table, String content) {
    table.addCell(content);
  }

  public void setBackgroundColor(Table table, Color backgroundColor) {
    table.setBackgroundColor(backgroundColor);
  }

  public void setBorder(Table table, Border border) {
    table.setBorder(border);
  }

  public void setBorderTop(Table table, Border borderTop) {
    table.setBorderTop(borderTop);
  }

  public void setBorderBottom(Table table, Border borderBottom) {
    table.setBorderBottom(borderBottom);
  }

  public void setBorderLeft(Table table, Border borderLeft) {
    table.setBorderLeft(borderLeft);
  }

  public void setBorderRight(Table table, Border borderRight) {
    table.setBorderRight(borderRight);
  }

  public void setBorderBottomLeftRadius(Table table, BorderRadius borderBottomLeftRadius) {
    table.setBorderBottomLeftRadius(borderBottomLeftRadius);
  }

  public void setBorderBottomRightRadius(Table table, BorderRadius borderBottomRightRadius) {
    table.setBorderBottomRightRadius(borderBottomRightRadius);
  }

  public void setBorderTopLeftRadius(Table table, BorderRadius borderBottomTopRadius) {
    table.setBorderTopLeftRadius(borderBottomTopRadius);
  }

  public void setBorderTopRightRadius(Table table, BorderRadius borderTopRightRadius) {
    table.setBorderTopRightRadius(borderTopRightRadius);
  }

  public void setFontSize(Table table, float fontSize) {
    table.setFontSize(fontSize);
  }

  public void setFontFamily(Table table, String fontName) {
    table.setFontFamily(fontName);
  }

  public void setVerticalAlignment(Table table, VerticalAlignment verticalAlignment) {
    table.setVerticalAlignment(verticalAlignment);
  }

  public void setHorizontalAlignment(Table table, HorizontalAlignment horizontalAlignment) {
    table.setHorizontalAlignment(horizontalAlignment);
  }

  public void setTextAlignment(Table table, TextAlignment textAlignment) {
    table.setTextAlignment(textAlignment);
  }

  public void setAlignment(
      Table table, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
    table.setHorizontalAlignment(horizontalAlignment);
    table.setVerticalAlignment(verticalAlignment);
  }

  public void setAlignment(Table table, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, TextAlignment textAlignment) {
    table.setHorizontalAlignment(horizontalAlignment);
    table.setVerticalAlignment(verticalAlignment);
    table.setTextAlignment(textAlignment);
  }

  public void setFont(Table table, PdfFont font) {
    table.setFont(font);
  }

  public void setPadding(Table table, float padding) {
    table.setPadding(padding);
  }

  public void setPaddingLeft(Table table, float paddingLeft) {
    table.setPaddingLeft(paddingLeft);
  }

  public void setPaddingRight(Table table, float paddingRight) {
    table.setPaddingRight(paddingRight);
  }

  public void setPaddingTop(Table table, float paddingTop) {
    table.setPaddingTop(paddingTop);
  }

  public void setPaddingBottom(Table table, float paddingBottom) {
    table.setPaddingBottom(paddingBottom);
  }

  public void setPadding(Table table, float left, float top, float right, float bottom) {
    table.setPaddingLeft(left);
    table.setPaddingTop(top);
    table.setPaddingRight(right);
    table.setPaddingBottom(bottom);
  }

  public void setCellConfiguration(Table table, TableBean tableBean) {
    Cell cell = configCell(tableBean.getCellInputBean().getCellConfigBean());
    cell = addCellContent(cell, tableBean.getCellInputBean().getCellConfigBean());
    CellHelper.setCellAlignment(cell, tableBean.getCellInputBean().getAlignmentBean().getHorizontalAlignment(),
            tableBean.getCellInputBean().getAlignmentBean().getVerticalAlignment(),
            tableBean.getCellInputBean().getAlignmentBean().getTextAlignment());
    CellHelper.setCellBorderRadius(cell,
            tableBean.getCellInputBean().getBorderBean().getBorderBottomLeftRadius(),
            tableBean.getCellInputBean().getBorderBean().getBorderBottomRightRadius(),
            tableBean.getCellInputBean().getBorderBean().getBorderTopLeftRadius(),
            tableBean.getCellInputBean().getBorderBean().getBorderTopRightRadius());
    CellHelper.setCellPadding(
        cell,
        tableBean.getCellInputBean().getPaddingInputBean().getPaddingLeft(),
            tableBean.getCellInputBean().getPaddingInputBean().getPaddingTop(),
            tableBean.getCellInputBean().getPaddingInputBean().getPaddingRight(),
            tableBean.getCellInputBean().getPaddingInputBean().getPaddingBottom()
    );
    CellHelper.setCellMargin(cell,
            tableBean.getCellInputBean().getMarginBean().getLeftMargin(),
            tableBean.getCellInputBean().getMarginBean().getTopMargin(),
            tableBean.getCellInputBean().getMarginBean().getBottomMargin(),
            tableBean.getCellInputBean().getMarginBean().getRightMargin());
    CellHelper.setCellFont(cell, tableBean.getCellInputBean().getFontConfigBean().getFont());
    CellHelper.setCellFontSize(cell, tableBean.getCellInputBean().getFontConfigBean().getFontSize());
    TableHelper.addCell(table, cell);
  }
}
