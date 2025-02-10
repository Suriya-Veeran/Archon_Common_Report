package com.p3solutions.archon_report_utility.builder;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.*;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.enums.TableTypeEnum;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TableBuilder {
  public static TableBean getTableBean(Map<String, String> parameters, TableType tableType) {

    return TableBean.builder().build();

    //        return TableBean.builder()
    //                .numberOfColumns(3)
    //                .pointColumnWidth(new float[]{450L, 450L, 450L})
    //                .width(100)
    //                .border(Border.NO_BORDER)
    //                .keepTogether(true)
    //                .parameters(parameters)
    //                .tableType(tableType)
    //                .type(TableTypeEnum.POINT_COLUMN_WIDTH)
    //                .build();
  }

  private TableConfigBean buildTableConfigBean(
      float width,
      boolean keepTogether,
      int numberOfColumns,
      float pointColumnWidth,
      Map<String, String> parameters,
      TableType tableType,
      TableTypeEnum tableTypeEnum,
      Boolean setFixedLayout) {

    return TableConfigBean.builder()
        .width(100)
        .numberOfColumns(1)
        .keepTogether(false)
        .type(TableTypeEnum.NUMBER_OF_COLUMNS)
        .parameters(parameters)
        .tableType(TableType.HEADER)
        .setFixedLayout(false)
        .build();
  }

  private FontConfigBean buildFontConfigBean(
      String fontName, PdfFont font, Color fontColor, float fontSize) {

    return FontConfigBean.builder()
        .fontName(fontName)
        .fontSize(fontSize)
        .font(font)
        .fontColor(fontColor)
        .build();
  }

  private AlignmentBean buildAlignmentBean(
      TextAlignment textAlignment,
      VerticalAlignment verticalAlignment,
      HorizontalAlignment horizontalAlignment) {

    return AlignmentBean.builder()
        .textAlignment(textAlignment)
        .horizontalAlignment(horizontalAlignment)
        .verticalAlignment(verticalAlignment)
        .build();
  }

  private MarginBean buildMarginBean(
      float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
    return MarginBean.builder()
        .leftMargin(leftMargin)
        .bottomMargin(bottomMargin)
        .rightMargin(rightMargin)
        .topMargin(topMargin)
        .build();
  }
}
