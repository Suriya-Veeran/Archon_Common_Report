package com.p3solutions.archon_report_utility.components;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.p3solutions.archon_report_utility.beans.SummaryBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryComponent implements ReportComponent {
    private SummaryBean inputBean;
    
    public void render(Document document) {
        document.add(new Paragraph("Summary"));
        document.flush();
    }

}