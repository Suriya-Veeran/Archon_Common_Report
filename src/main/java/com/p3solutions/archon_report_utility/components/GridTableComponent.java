package com.p3solutions.archon_report_utility.components;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.beans.GridTableBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GridTableComponent implements ReportComponent {

  private GridTableBean inputBean;

  @Override
  public void render(Document document) throws IOException {

    if (inputBean.getParameterMap() != null && !inputBean.getParameterMap().isEmpty()) {

      Table table = null;
      switch (inputBean.getType()) {
        case POINT_COLUMN_WIDTH:
          table = new Table(UnitValue.createPercentArray(inputBean.getPointColumnWidth()));
          break;
        case NUMBER_OF_COLUMNS:
          table = new Table(inputBean.getNumberOfColumns());
          break;
        default:
          throw new IllegalArgumentException("Unsupported type: " + inputBean.getType());
      }
      setCellValues(inputBean, table);
      table.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
      table.setKeepTogether(inputBean.isKeepTogether());
      table.setBorder(inputBean.getBorder());
      table.setMarginLeft(-18);
      document.add(table);
    }
  }

  private void setCellValues(GridTableBean inputBean, Table table) {
    Map<String, List<String>> parameterMap = inputBean.getParameterMap();

    for (String header : parameterMap.keySet()) {
      table.addCell(new Cell().add(new Paragraph(new Text(header))));
    }

    int rowCount = parameterMap.values().iterator().next().size();
    for (int i = 0; i < rowCount; i++) {
      for (List<String> values : parameterMap.values()) {
        table.addCell(new Cell().add(new Paragraph(new Text(values.get(i)))));
      }
    }
  }
}
