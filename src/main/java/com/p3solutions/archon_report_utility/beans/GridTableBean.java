package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.borders.Border;
import com.p3solutions.archon_report_utility.enums.TableTypeEnum;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GridTableBean implements ReportBean {

  @Builder.Default private float width = 100;
  @Builder.Default private boolean keepTogether = false;
  @Builder.Default private Border border = Border.NO_BORDER;
  @Builder.Default private int numberOfColumns = 1;
  @Builder.Default private float[] pointColumnWidth = new float[] {1L};
  @Builder.Default private TableTypeEnum type = TableTypeEnum.NUMBER_OF_COLUMNS;
  @Builder.Default private CellInputBean cellInputBean = new CellInputBean();
  @Builder.Default private Map<String, List<String>> parameterMap = new LinkedHashMap<>();

  @Builder.Default private MarginBean marginBean = new MarginBean();

  @Builder.Default private BorderBean borderBean = new BorderBean();
}
