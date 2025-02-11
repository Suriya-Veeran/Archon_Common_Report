package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum CellStructureType {

    SINGLE_CELL("Single Cell"),
    CELL_WITH_HEADER_AND_VALUES("Cell With Header and Values"),
    CELL_WITH_JOB_STATUS("Cell With Job Status");

    private String name;

    CellStructureType(String name) {
        this.name = name;
    }


}
