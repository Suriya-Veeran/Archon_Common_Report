package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum TableTypeEnum {
  NUMBER_OF_COLUMNS("Number of columns"),
  POINT_COLUMN_WIDTH("Point Column Width");

  private final String name;
  TableTypeEnum(String name) {
    this.name = name;
  }

}
