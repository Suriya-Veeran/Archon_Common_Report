package com.p3solutions.archon_report_utility.helpers;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CellHelper {

  public void setCellHeight(Cell cell, int height) {
    cell.setHeight(height);
  }

  public void setCellWidth(Cell cell, int width) {
    cell.setWidth(width);
  }

  public void setCell(Cell cell, int height, int width) {
    cell.setHeight(height);
    cell.setWidth(width);
  }

  public void setCellKeepTogether(Cell cell, Boolean keepTogether) {
    cell.setKeepTogether(keepTogether);
  }

  public void setCellMargin(Cell cell, float left, float top, float right, float bottom) {
    cell.setMargins(top, right, bottom, left);
  }

  public void setCellMarginTop(Cell cell, float top) {
    cell.setMarginTop(top);
  }

  public void setCellMarginBottom(Cell cell, float bottom) {
    cell.setMarginBottom(bottom);
  }

  public void setCellMarginLeft(Cell cell, float left) {
    cell.setMarginLeft(left);
  }

  public void setCellMarginRight(Cell cell, float right) {
    cell.setMarginRight(right);
  }

  public void setCellBorder(Cell cell, Border border) {
    cell.setBorder(border);
  }

  public void setCellBorderTop(Cell cell, Border border) {
    cell.setBorderTop(border);
  }

  public void setCellBorderBottom(Cell cell, Border border) {
    cell.setBorderBottom(border);
  }

  public void setCellBorderLeft(Cell cell, Border border) {
    cell.setBorderLeft(border);
  }

  public void setCellBorderRight(Cell cell, Border border) {
    cell.setBorderRight(border);
  }

  public void setCellBorderRadius(Cell cell, BorderRadius borderRadius) {
    cell.setBorderRadius(borderRadius);
  }

  public void setCellBorderBottomLeftRadius(Cell cell, BorderRadius borderRadius) {
    cell.setBorderBottomLeftRadius(borderRadius);
  }

  public void setCellBorderBottomRightRadius(Cell cell, BorderRadius borderRadius) {
    cell.setBorderBottomRightRadius(borderRadius);
  }

  public void setCellBorderTopLeftRadius(Cell cell, BorderRadius borderRadius) {
    cell.setBorderTopLeftRadius(borderRadius);
  }

  public void setCellBorderTopRightRadius(Cell cell, BorderRadius borderRadius) {
    cell.setBorderTopRightRadius(borderRadius);
  }

  public void setCellPadding(Cell cell, float left, float top, float right, float bottom) {
    cell.setPaddingLeft(left);
    cell.setPaddingTop(top);
    cell.setPaddingRight(right);
    cell.setPaddingBottom(bottom);
  }

  public void setCellLeftPadding(Cell cell, float left) {
    cell.setPaddingLeft(left);
  }

  public void setCellRightPadding(Cell cell, float right) {
    cell.setPaddingRight(right);
  }

  public void setCellTopPadding(Cell cell, float top) {
    cell.setPaddingTop(top);
  }

  public void setCellBottomPadding(Cell cell, float top) {
    cell.setPaddingBottom(top);
  }

  public void setCellPadding(Cell cell, float padding) {
    cell.setPadding(padding);
  }

  public void setCellVerticalAlignment(Cell cell, VerticalAlignment verticalAlignment) {
    cell.setVerticalAlignment(verticalAlignment);
  }

  public void setCellHorizontalAlignment(Cell cell, HorizontalAlignment horizontalAlignment) {
    cell.setHorizontalAlignment(horizontalAlignment);
  }

  public void setCellTextAlignment(Cell cell, TextAlignment textAlignment) {
    cell.setTextAlignment(textAlignment);
  }

  public void setCellAlignment(
      Cell cell, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
    cell.setHorizontalAlignment(horizontalAlignment);
    cell.setVerticalAlignment(verticalAlignment);
  }

  public void setCellAlignment(
      Cell cell,
      HorizontalAlignment horizontalAlignment,
      VerticalAlignment verticalAlignment,
      TextAlignment textAlignment) {
    cell.setHorizontalAlignment(horizontalAlignment);
    cell.setVerticalAlignment(verticalAlignment);
    cell.setTextAlignment(textAlignment);
  }

  public void setCellFontSize(Cell cell, float cellFontSize) {
    cell.setFontSize(cellFontSize);
  }

  public void setCellFontFamily(Cell cell, String fontFamily) {
    cell.setFontFamily(fontFamily);
  }

  public void setCellFont(Cell cell, PdfFont font) {
    cell.setFont(font);
  }

  public void setCellBackgroundColor(Cell cell, Color backgroundColor) {
    cell.setBackgroundColor(backgroundColor);
  }
  
  public void setCellBorder(Cell cell, Border topBorder, Border bottomBorder, Border leftBorder, Border rightBorder) {
    cell.setBorderTop(topBorder);
    cell.setBorderBottom(bottomBorder);
    cell.setBorderLeft(leftBorder);
    cell.setBorderRight(rightBorder);
  }
  
  public void setCellBorderRadius(Cell cell,
                                  BorderRadius bottomLeftRadius,
                                  BorderRadius bottomRightRadius, 
                                  BorderRadius topBorderLeftRadius,
                                  BorderRadius topBorderRightRadius) {
    
    cell.setBorderBottomLeftRadius(bottomLeftRadius);
    cell.setBorderBottomRightRadius(bottomRightRadius);
    cell.setBorderTopLeftRadius(topBorderLeftRadius);
    cell.setBorderTopRightRadius(topBorderRightRadius);
    
  }
  
  public void setCellBorderBottomRadius(Cell cell, BorderRadius bottomLeftRadius, BorderRadius bottomRightRadius){
    cell.setBorderBottomLeftRadius(bottomLeftRadius);
    cell.setBorderBottomRightRadius(bottomRightRadius);
  }
  
  public void setCellBorderTopRadius(Cell cell, BorderRadius topLeftRadius, BorderRadius topRightRadius){
    cell.setBorderTopLeftRadius(topLeftRadius);
    cell.setBorderTopRightRadius(topRightRadius);
  }
  
}
