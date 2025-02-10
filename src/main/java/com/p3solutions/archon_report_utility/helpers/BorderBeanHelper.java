package com.p3solutions.archon_report_utility.helpers;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.BorderRadius;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BorderBeanHelper {

  public void setBorder(Border border, Cell cell) {
    cell.setBorder(border);
  }

  public void setBorderTop(Border border, Cell cell) {
    cell.setBorderTop(border);
  }

  public void setBorderLeft(Border border, Cell cell) {
    cell.setBorderLeft(border);
  }

  public void setBorderBottom(Border border, Cell cell) {
    cell.setBorderBottom(border);
  }

  public void setBorderRight(Border border, Cell cell) {
    cell.setBorderRight(border);
  }

  public void setBorderRadius(float radius, Cell cell) {
    cell.setBorderRadius(new BorderRadius(radius));
  }

  public void setBorderTopRightRadius(float radius, Cell cell) {
    cell.setBorderTopRightRadius(new BorderRadius(radius));
  }

  public void setBorderBottomRightRadius(float radius, Cell cell) {
    cell.setBorderBottomRightRadius(new BorderRadius(radius));
  }

  public void setBorderTopLeftRadius(float radius, Cell cell) {
    cell.setBorderTopLeftRadius(new BorderRadius(radius));
  }

  public void setBorderBottomLeftRadius(float radius, Cell cell) {
    cell.setBorderBottomLeftRadius(new BorderRadius(radius));
  }



}
