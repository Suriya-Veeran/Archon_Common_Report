package com.p3solutions.archon_report_utility.interfaces;

import com.itextpdf.layout.Document;

import java.io.IOException;

public interface ReportComponent {
    void render(Document document) throws IOException;


}
