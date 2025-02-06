package com.p3solutions.archon_report_utility.utils;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.beans.GridTableBean;
import com.p3solutions.archon_report_utility.beans.TableBean;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {

  public static void addEmptyLines(int numberOfPages, Document document) {
    for (int i = 0; i < numberOfPages; i++) {
      document.add(new Paragraph(""));
    }
  }

  public static Table configTable(TableBean tableBean) {
    Table table;
    switch (tableBean.getType()) {
      case POINT_COLUMN_WIDTH:
        table = new Table(UnitValue.createPercentArray(tableBean.getPointColumnWidth()));
        break;
      case NUMBER_OF_COLUMNS:
        table = new Table(tableBean.getNumberOfColumns());
        break;
      default:
        throw new IllegalArgumentException("Unsupported type: " + tableBean.getType());
    }
    return table;
  }

  public static Table configTable(GridTableBean tableBean) {
    Table table;
    switch (tableBean.getType()) {
      case POINT_COLUMN_WIDTH:
        table = new Table(UnitValue.createPercentArray(tableBean.getPointColumnWidth()));
        break;
      case NUMBER_OF_COLUMNS:
        table = new Table(tableBean.getNumberOfColumns());
        break;
      default:
        throw new IllegalArgumentException("Unsupported type: " + tableBean.getType());
    }
    return table;
  }

}
