package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum PaddingType {

    COMMON_PADDING("Common Padding"),
    LEFT_PADDING("Left Padding"),
    RIGHT_PADDING("Right Padding"),
    TOP_PADDING("Top Padding"),
    BOTTOM_PADDING("Bottom Padding");

    private String value;
    PaddingType(String value) {
        this.value = value;
    }

}
