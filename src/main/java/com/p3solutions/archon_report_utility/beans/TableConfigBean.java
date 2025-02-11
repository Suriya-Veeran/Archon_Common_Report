package com.p3solutions.archon_report_utility.beans;

import com.p3solutions.archon_report_utility.enums.TableTypeEnum;
import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableConfigBean {

  @Builder.Default private float width = 100;
  @Builder.Default private boolean keepTogether = false;
  @Builder.Default private int numberOfColumns = 1;
  @Builder.Default private float[] pointColumnWidth = new float[] {1L};
  @Builder.Default private Map<String, String> parameters = new LinkedHashMap<>();
  @Builder.Default private TableTypeEnum type = TableTypeEnum.NUMBER_OF_COLUMNS;
  @Builder.Default private Boolean setFixedLayout = false;
}
