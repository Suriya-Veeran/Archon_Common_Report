package com.p3solutions.archon_report_utility.builder;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.CellConfigBean;
import com.p3solutions.archon_report_utility.beans.*;
import com.p3solutions.archon_report_utility.enums.*;
import lombok.experimental.UtilityClass;

import java.util.Map;

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

  public static TableConfigBean buildTableConfigBean(
      float width,
      boolean keepTogether,
      int numberOfColumns,
      float[] pointColumnWidth,
      Map<String, String> parameters,
      TableTypeEnum tableTypeEnum,
      Boolean setFixedLayout) {

    return TableConfigBean.builder()
        .width(width)
        .numberOfColumns(numberOfColumns)
            .pointColumnWidth(pointColumnWidth)
        .keepTogether(keepTogether)
        .type(tableTypeEnum)
        .parameters(parameters)
        .setFixedLayout(setFixedLayout)
        .build();
  }

  public static FontConfigBean buildFontConfigBean(
      String fontName, PdfFont font, String fontColor, float fontSize) {

    return FontConfigBean.builder()
        .fontName(fontName)
        .fontSize(fontSize)
        .font(font)
        .fontColor(fontColor)
        .build();
  }

  public static AlignmentBean buildAlignmentBean(
      TextAlignment textAlignment,
      VerticalAlignment verticalAlignment,
      HorizontalAlignment horizontalAlignment) {

    return AlignmentBean.builder()
        .textAlignment(textAlignment)
        .horizontalAlignment(horizontalAlignment)
        .verticalAlignment(verticalAlignment)
        .build();
  }

  public static MarginBean buildMarginBean(
      float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
    return MarginBean.builder()
        .leftMargin(leftMargin)
        .bottomMargin(bottomMargin)
        .rightMargin(rightMargin)
        .topMargin(topMargin)
        .build();
  }

  public static PaddingInputBean buildPaddingInputBean(
          float leftMargin,
          float topMargin,
          float rightMargin,
          float bottomMargin,
          float commonPadding,
          PaddingType paddingType){

    return PaddingInputBean.builder()
            .commonPadding(commonPadding)
            .paddingBottom(bottomMargin)
            .paddingLeft(leftMargin)
            .paddingRight(rightMargin)
            .paddingTop(topMargin)
            .commonPadding(commonPadding)
            .paddingType(paddingType).build();

  }

  public static BorderBean buildBorderBean(Border border){
    return BorderBean.builder()
            .border(border)
            .build();
  }


  public static TableBean buildTableBean(AlignmentBean alignmentBean,
                                  TableConfigBean tableConfigBean,
                                  JobStatusInputBean jobStatusInputBean,
                                  FontConfigBean fontConfigBean,
                                  CellInputBean cellInputBean,
                                  MarginBean marginBean,
                                  BorderBean borderBean,
                                  PaddingInputBean paddingInputBean,
                                  Boolean isDividerNeeded
                                  ){
    return
            TableBean.builder()
                    .alignmentBean(alignmentBean)
                    .tableConfigBean(tableConfigBean)
                    .jobStatusInputBean(jobStatusInputBean)
                    .cellInputBean(cellInputBean)
                    .fontConfigBean(fontConfigBean)
                    .borderBean(borderBean)
                    .marginBean(marginBean)
                    .paddingInputBean(paddingInputBean)
                    .isDividerNeeded(isDividerNeeded)
                    .build();
  }

  public static CellConfigBean buildCellConfigBean(String content,
                                                   float width,
                                                   float height,
                                                   CellType cellType,
                                                   CellContentType cellContentType,
                                                   BorderBean paragraphBorder,
                                                   PaddingInputBean paragraphPadding,
                                                   MarginBean paragraphMargin,
                                                   FontConfigBean paragraphFont,
                                                   AlignmentBean paragraphAlignment,
                                                   String backgroundColor,
                                                   Boolean fixedLayout,
                                                   Map<String,String> headerParameters,
                                                   CellStructureType cellStructureType){

    return CellConfigBean.builder()
            .content(content)
            .width(width)
            .height(height)
            .cellType(cellType)
            .cellContentType(cellContentType)
            .paragraphAlignment(paragraphAlignment)
            .paragraphBorder(paragraphBorder)
            .paragraphPadding(paragraphPadding)
            .paragraphFont(paragraphFont)
            .paragraphMargin(paragraphMargin)
            .backgroundColor(backgroundColor)
            .fixedLayout(fixedLayout)
            .cellStructureType(cellStructureType)
            .headerAndValueParameters(headerParameters)
            .build();

  }

  public static CellInputBean buildCellInputBean(AlignmentBean alignmentBean,
                                                 BorderBean borderBean,
                                                 PaddingInputBean paddingInputBean,
                                                 FontConfigBean fontConfigBean,
                                                 CellConfigBean cellConfigBean,
                                                 MarginBean marginBean){
    return CellInputBean.builder()
            .alignmentBean(alignmentBean)
            .borderBean(borderBean)
            .paddingInputBean(paddingInputBean)
            .fontConfigBean(fontConfigBean)
            .cellConfigBean(cellConfigBean)
            .marginBean(marginBean)
            .build();
  }





}
