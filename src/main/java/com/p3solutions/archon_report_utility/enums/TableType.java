package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum TableType {
    HEADER("Header"),
    JOB_STATUS("Job Status"),
    SUMMARY("Summary");

    private String value;

    TableType(String value) {
        this.value = value;
    }

}
