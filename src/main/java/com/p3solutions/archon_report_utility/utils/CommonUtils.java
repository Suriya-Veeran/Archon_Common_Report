package com.p3solutions.archon_report_utility.utils;

import com.itextpdf.layout.element.Paragraph;
import lombok.experimental.UtilityClass;
import com.itextpdf.layout.Document;

@UtilityClass
public class CommonUtils {

    public static void addEmptyLines(int numberOfPages, Document document) {
        for (int i = 0; i < numberOfPages; i++) {
            document.add(new Paragraph(""));
        }
    }
}
