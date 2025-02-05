package com.p3solutions.archon_report_utility.builder;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.HeaderBean;

public class HeaderBuilder {

    public HeaderBean getHeaderBean() {
        return new HeaderBean();
    }

    public HeaderBean getHeaderBean(String title) {
        return HeaderBean.builder().title(title).build();
    }

    public HeaderBean getHeaderBean(String title, TextAlignment textAlignment) {
        return HeaderBean.builder().title(title).textAlignment(textAlignment).build();
    }

    public HeaderBean getHeaderBean(
            VerticalAlignment verticalAlignment, TextAlignment textAlignment) {
        return HeaderBean.builder()
                .verticalAlignment(verticalAlignment)
                .textAlignment(textAlignment)
                .build();
    }

    public HeaderBean getHeaderBean(
            String title, VerticalAlignment verticalAlignment, TextAlignment textAlignment) {
        return HeaderBean.builder()
                .title(title)
                .verticalAlignment(verticalAlignment)
                .textAlignment(textAlignment)
                .build();
    }

}
