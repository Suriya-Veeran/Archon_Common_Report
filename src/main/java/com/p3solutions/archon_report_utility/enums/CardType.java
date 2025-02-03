package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum CardType {

    SINGLE_DETAILS_INFO("Single Details"),
    MULTIPLE_DETAILS_INFO("Multiple Details");

    private final String value;


    CardType(String value) {
        this.value = value;
    }
}
