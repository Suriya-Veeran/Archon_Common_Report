package com.p3solutions.archon_report_utility.components.charts;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.p3solutions.archon_report_utility.beans.ChartCreationConfig;
import com.p3solutions.archon_report_utility.beans.charts.HtmlCreationInfoBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import com.p3solutions.archon_report_utility.utils.html.HtmlFileGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

import static com.p3solutions.archon_report_utility.utils.screenshot_utils.HeadlessScreenshot.takeScreenshot;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartComponent implements ReportComponent {
    private ChartCreationConfig inputBean;


    @Override
    public void render(Document document) {

        Table chartTable = new Table(inputBean.getNumberOfRows());
        chartTable.setMarginLeft(-18);
        int i = 0;


        for (HtmlCreationInfoBean htmlCreationInfoBean : inputBean.getHtmlCreationInfoBean()) {
            File htmlFile = HtmlFileGenerator.generateHtml(htmlCreationInfoBean);

            Image chart = takeScreenshot(
                    htmlFile.toURI().toString(),
                    "chrome",
                    htmlCreationInfoBean.getChartBasicInfo().getChartType());

            chart.scaleToFit(inputBean.getFitWeight(), inputBean.getFitHeight());

            Cell chartCell = new Cell().add(chart).setBorder(Border.NO_BORDER);

            if (i % inputBean.getNumberOfRows() == 0) {
                chartCell.setPaddingLeft(-30); // Apply padding for left-aligned chart
            }

            // Add the chart cell to the table
            chartTable.addCell(chartCell);

            i++;

        }

        document.add(chartTable);


    }
}
