package com.p3solutions.archon_report_utility.core;

import com.p3solutions.archon_report_utility.beans.*;
import com.p3solutions.archon_report_utility.components.*;
import com.p3solutions.archon_report_utility.components.charts.ChartComponent;
import com.p3solutions.archon_report_utility.enums.ComponentType;
import com.p3solutions.archon_report_utility.factory.ReportComponentFactory;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class ReportBuilder extends AbstractReport {

    private final Report report;
    public ReportBuilder(String outputPath) throws IOException {
        super(outputPath);
        report = new Report(document);
    }

    public ReportBuilder(String outputPath,String filename) throws IOException {
        super(outputPath,filename);
        report = new Report(document);
    }

    public ReportBuilder appendComponent(ComponentType type, ReportBean bean) {
        report.addComponent(ReportComponentFactory.createComponent(type, bean));
        return this;
    }

    public static ReportComponent addComponent(HeaderBean bean){
        return HeaderComponent.builder()
                .inputBean(bean).build();
    }

    public static ReportComponent addComponent(CardBean bean){
        return CardComponent.builder()
                .inputBean(bean).build();
    }

    public static ReportComponent addComponent(GridTableBean bean){
        return GridTableComponent.builder()
                .inputBean(bean).build();
    }

    public static ReportComponent addComponent(DividerBean bean){
        return DividerComponent.builder()
                .inputBean(bean).build();
    }
    public static ReportComponent addComponent(ChartCreationConfig bean){
        return ChartComponent.builder()
                .inputBean(bean).build();
    }
    public static ReportComponent addComponent(SummaryBean bean){
        return SummaryComponent.builder()
                .inputBean(bean).build();
    }

    public static ReportComponent addComponent(FooterBean bean){
        return FooterComponent.builder()
                .inputBean(bean).build();
    }

    public static ReportComponent addComponent(TableBean bean){
        return TableComponent.builder()
                .inputBean(bean).build();
    }

    public Report build() {
        return report;
    }
}
