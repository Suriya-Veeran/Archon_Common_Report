package com.p3solutions.archon_report_utility.core;

import com.itextpdf.layout.Document;
import com.p3solutions.archon_report_utility.enums.ComponentType;
import com.p3solutions.archon_report_utility.factory.ReportComponentFactory;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

// Composite
public class Report {
    private final Document document;
    private List<ReportComponent> components = new LinkedList<>();

    public Report(Document document) {
        this.document = document;
    }


    public void addComponent(ComponentType type, ReportBean bean) {
        components.add(ReportComponentFactory.createComponent(type, bean));
    }

    public void addComponent(ReportComponent reportComponent) {
        components.add(reportComponent);
    }

    public void render() throws IOException {
        for (ReportComponent component : components) {
            component.render(this.document);
        }
        components.clear();
    }

    public void close() {
        if (Objects.nonNull(this.document)) {
            this.document.flush();
            this.document.close();
        }
    }
}
