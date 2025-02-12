package com.p3solutions.archon_report_utility.components.charts;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.p3solutions.archon_report_utility.beans.ChartCreationConfig;
import com.p3solutions.archon_report_utility.beans.charts.HtmlCreationInfoBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import com.p3solutions.archon_report_utility.utils.html.HtmlFileGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

import static com.p3solutions.archon_report_utility.utils.CommonUtils.addEmptyLines;
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


        for (HtmlCreationInfoBean htmlCreationInfoBean : inputBean.getHtmlCreationInfoBean()) {
            File htmlFile = HtmlFileGenerator.generateHtml(htmlCreationInfoBean);

            Image chart = takeScreenshot(
                    htmlFile.toURI().toString(),
                    "chrome",
                    htmlCreationInfoBean.getChartBasicInfo().getChartType());

            chart.scaleToFit(inputBean.getFitWeight(), inputBean.getFitHeight());

            Cell chartCell = new Cell().add(chart)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setHorizontalAlignment(HorizontalAlignment.LEFT)
                    .setBorder(Border.NO_BORDER)
                    ;


            chartCell.setPaddingLeft(-80);

            chartTable.addCell(chartCell);
            chartTable.setMarginTop(-12);

        }
        chartTable.setFixedLayout();
        document.add(chartTable);
        addEmptyLines(1, document);

    }
}
