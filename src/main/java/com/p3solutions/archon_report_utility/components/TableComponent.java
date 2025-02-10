package com.p3solutions.archon_report_utility.components;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.p3solutions.archon_report_utility.beans.TableBean;
import com.p3solutions.archon_report_utility.exception.EnumNotFound;
import com.p3solutions.archon_report_utility.helpers.TableHelper;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.p3solutions.archon_report_utility.utils.CommonUtils.configTable;
import static com.p3solutions.archon_report_utility.utils.CommonUtils.paddingConfiguration;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TableComponent implements ReportComponent {

  private TableBean inputBean;

  public void render(Document document) throws IOException {

    Table table = null;

    switch (inputBean.getTableConfigBean().getTableType()) {
      case HEADER:
        table = createHeaderTable(inputBean);
        break;
      case SUMMARY:
        table = createSummaryTable(inputBean);
        break;
      case JOB_STATUS:
       table =  createJobStatusTable(inputBean);
        break;
      default:
        throw new EnumNotFound(
            "Illegal table type : " + inputBean.getTableConfigBean().getTableType());
    }

    document.add(table);

  }

  private Table createJobStatusTable(TableBean inputBean) {
    Table table = configTable(inputBean);
    return table;
  }

  private Table createSummaryTable(TableBean inputBean) {
    Table table = configTable(inputBean);
    return table;
  }

  private Table createHeaderTable(TableBean inputBean) {
    Table table = configTable(inputBean);
    TableHelper.setTableWidth(table, inputBean.getTableConfigBean().getWidth());
    TableHelper.setFixedLayout(table, inputBean.getTableConfigBean().getSetFixedLayout());
    TableHelper.setKeepTogether(table, inputBean.getTableConfigBean().isKeepTogether());
    TableHelper.setFontFamily(table, inputBean.getFontConfigBean().getFontName());
    TableHelper.setFontSize(table, inputBean.getFontConfigBean().getFontSize());
    TableHelper.setFont(table, inputBean.getFontConfigBean().getFont());
    TableHelper.setMargin(
        table,
        inputBean.getMarginBean().getLeftMargin(),
        inputBean.getMarginBean().getTopMargin(),
        inputBean.getMarginBean().getRightMargin(),
        inputBean.getMarginBean().getBottomMargin());
    TableHelper.setAlignment(table, inputBean.getAlignmentBean().getHorizontalAlignment(), inputBean.getAlignmentBean().getVerticalAlignment());
    TableHelper.setTextAlignment(table, inputBean.getAlignmentBean().getTextAlignment());
    paddingConfiguration(table, inputBean);
    TableHelper.setCellConfiguration(table, inputBean);
    return table;
  }
}
