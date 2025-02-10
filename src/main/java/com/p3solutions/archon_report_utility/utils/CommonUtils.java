package com.p3solutions.archon_report_utility.utils;

import static com.p3solutions.archon_report_utility.enums.TableTypeEnum.NUMBER_OF_COLUMNS;
import static com.p3solutions.archon_report_utility.enums.TableTypeEnum.POINT_COLUMN_WIDTH;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.CellConfigBean;
import com.p3solutions.archon_report_utility.beans.GridTableBean;
import com.p3solutions.archon_report_utility.beans.TableBean;
import com.p3solutions.archon_report_utility.exception.EnumNotFound;
import com.p3solutions.archon_report_utility.helpers.TableHelper;
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
    switch (tableBean.getTableConfigBean().getType()) {
      case POINT_COLUMN_WIDTH:
        table = new Table(UnitValue.createPercentArray(tableBean.getTableConfigBean().getPointColumnWidth()));
        break;
      case NUMBER_OF_COLUMNS:
        table = new Table(tableBean.getTableConfigBean().getNumberOfColumns());
        break;
      default:
        throw new IllegalArgumentException("Unsupported type: " + tableBean.getTableConfigBean().getType());
    }
    return table;
  }

  public static Table configTable(GridTableBean gridTableBean) {
    Table table;
    switch (gridTableBean.getType()) {
      case POINT_COLUMN_WIDTH:
        table = new Table(UnitValue.createPercentArray(gridTableBean.getPointColumnWidth()));
        break;
      case NUMBER_OF_COLUMNS:
        table = new Table(gridTableBean.getNumberOfColumns());
        break;
      default:
        throw new IllegalArgumentException("Unsupported type: " + gridTableBean.getType());
    }
    return table;
  }

  public static void paddingConfiguration(Table table, TableBean inputBean) {
    switch (inputBean.getPaddingType()) {
      case COMMON_PADDING:
        TableHelper.setPadding(table, inputBean.getPaddingInputBean().getCommonPadding());
        break;
      case TOP_PADDING:
        TableHelper.setPaddingTop(table, inputBean.getPaddingInputBean().getPaddingTop());
        break;
      case BOTTOM_PADDING:
        TableHelper.setPaddingBottom(table, inputBean.getPaddingInputBean().getPaddingBottom());
        break;
      case LEFT_PADDING:
        TableHelper.setPaddingLeft(table, inputBean.getPaddingInputBean().getPaddingLeft());
        break;
      case RIGHT_PADDING:
        TableHelper.setPaddingRight(table, inputBean.getPaddingInputBean().getPaddingRight());
        break;
      default:
        throw new EnumNotFound("Illegal padding type : " + inputBean.getPaddingType());
    }
  }

  public static Cell configCell(CellConfigBean cellConfigBean){
    Cell cell;
    switch (cellConfigBean.getCellType()){
      case DEFAULT:
        cell = new Cell();
        break;
      case ROW_AND_COLUMN:
        cell = new Cell(cellConfigBean.getRowSpan(), cellConfigBean.getColSpan());
        break;
      default:
        throw new EnumNotFound("Illegal cell type : " + cellConfigBean.getCellType());
    }
    return cell;
  }

  public static Cell addCellContent(Cell cell , CellConfigBean configBean){
    switch (configBean.getCellContentType()){
      case PARAGRAPH:
        cell.add(new Paragraph(configBean.getContent())
                .setFont(configBean.getParagraphFont().getFont())
                .setFontSize(configBean.getParagraphFont().getFontSize())
                .setFontColor(configBean.getParagraphFont().getFontColor())
                .setVerticalAlignment(configBean.getParagraphAlignment().getVerticalAlignment())
                .setHorizontalAlignment(configBean.getParagraphAlignment().getHorizontalAlignment())
                .setTextAlignment(configBean.getParagraphAlignment().getTextAlignment())
                .setPaddings(configBean.getParagraphPadding().getPaddingTop(),
                        configBean.getParagraphPadding().getPaddingRight(),
                        configBean.getParagraphPadding().getPaddingBottom(),
                        configBean.getParagraphPadding().getPaddingLeft())
        );
        break;
      case IMAGE:
          cell.add(configBean.getChart());
          break;
      case PARAGRAPH_WITH_TEXT:
        cell.add(new Paragraph(new Text(configBean.getContent())));
        break;
      case PARAGRAPH_WITH_LINK:
//        cell.add(new Paragraph(new Link(configBean.getContent())));
        break;
      default:
        throw new EnumNotFound("Illegal cell type : " + configBean.getCellContentType());
    }
    return cell;
  }

}
