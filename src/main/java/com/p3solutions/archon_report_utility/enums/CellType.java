package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum CellType {

    DEFAULT("Default"),
    ROW_AND_COLUMN("Row and Column");


    private String value;
    CellType(String value) {
        this.value = value;
    }

}
