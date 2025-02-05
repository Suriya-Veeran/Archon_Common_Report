package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum TableType {
    HEADER("Header"),
    SUMMARY("Summary");

    private String value;

    TableType(String value) {
        this.value = value;
    }

}
