package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum DividerType {
    PAGE_TO_PAGE("Page To Page"),
    CONTENT("Content");

    private final String value;

    DividerType(String value) {
        this.value = value;
    }

}
