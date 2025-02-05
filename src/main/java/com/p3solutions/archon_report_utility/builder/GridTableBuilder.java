package com.p3solutions.archon_report_utility.builder;

import com.itextpdf.layout.borders.Border;
import com.p3solutions.archon_report_utility.beans.GridTableBean;
import com.p3solutions.archon_report_utility.enums.TableTypeEnum;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GridTableBuilder {

  public static GridTableBean buildGridTableComponent(
      Map<String, List<String>> gridParameters, int size) {
    return GridTableBean.builder()
        .numberOfColumns(size)
        .pointColumnWidth(new float[] {450L, 450L, 450L, 450L, 450L})
        .width(100)
        .border(Border.NO_BORDER)
        .keepTogether(true)
        .parameterMap(gridParameters)
        .type(TableTypeEnum.NUMBER_OF_COLUMNS)
        .build();
  }
}
