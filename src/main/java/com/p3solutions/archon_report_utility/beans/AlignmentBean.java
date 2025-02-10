package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlignmentBean {

    private TextAlignment textAlignment;

    private HorizontalAlignment horizontalAlignment;

    private VerticalAlignment verticalAlignment;

}
