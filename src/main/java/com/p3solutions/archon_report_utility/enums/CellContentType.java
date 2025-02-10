package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum CellContentType {

    IMAGE("Image"),
    PARAGRAPH("Paragraph"),
    PARAGRAPH_WITH_TEXT("Paragraph With Text"),
    PARAGRAPH_WITH_LINK("Paragraph With Link"),
    ;

    private String value;

    CellContentType(String value) {
        this.value = value;
    }


}
