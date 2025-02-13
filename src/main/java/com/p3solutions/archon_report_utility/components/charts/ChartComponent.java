package com.p3solutions.archon_report_utility.components.charts;

import static com.p3solutions.archon_report_utility.utils.CommonUtils.addEmptyLines;
import static com.p3solutions.archon_report_utility.utils.screenshot_utils.HeadlessScreenshot.takeScreenshot;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.p3solutions.archon_report_utility.beans.ChartCreationConfig;
import com.p3solutions.archon_report_utility.beans.charts.HtmlCreationInfoBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import com.p3solutions.archon_report_utility.utils.html.HtmlFileGenerator;
import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartComponent implements ReportComponent {
    private ChartCreationConfig inputBean;
    
    @Override
    public void render(Document document) {
        Table chartTable = new Table(inputBean.getNumberOfRows());
        chartTable.setMarginLeft(inputBean.getMarginBean().getLeftMargin());
        for (HtmlCreationInfoBean htmlCreationInfoBean : inputBean.getHtmlCreationInfoBean()) {
            File htmlFile = HtmlFileGenerator.generateHtml(htmlCreationInfoBean);
            Image chart = takeScreenshot(
                    htmlFile.toURI().toString(),
                    inputBean.getBrowser(),
                    htmlCreationInfoBean.getChartBasicInfo().getChartType());
            chart.scaleToFit(inputBean.getFitWeight(), inputBean.getFitHeight());
            Cell chartCell = new Cell().add(chart)
                    .setTextAlignment(inputBean.getCellInputBean().getTextAlignment())
                    .setHorizontalAlignment(inputBean.getCellInputBean().getHorizontalAlignment())
                    .setBorder(inputBean.getCellInputBean().getBorder());
            chartCell.setPaddingLeft(inputBean.getPaddingLeft());
            chartTable.addCell(chartCell);
            chartTable.setMarginTop(inputBean.getMarginBean().getTopMargin());
        }
        chartTable.setFixedLayout();
        document.add(chartTable);
        addEmptyLines(inputBean.getEmptyLines(), document);
    }
}
