package com.p3solutions.archon_report_utility.helpers;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TableHelper {

    public void setTableWidth(Table table, int width) {
        table.setWidth(UnitValue.createPercentValue(width));
    }

    public void setKeepTogether(Table table, Boolean keepTogether) {
        table.setKeepTogether(keepTogether);
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

    public void addCell(Table table , Cell cell) {
        table.addCell(cell);
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




}
