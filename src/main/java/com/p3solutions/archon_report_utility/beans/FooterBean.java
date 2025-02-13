package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FooterBean implements ReportBean {


    @Builder.Default
    private int rectangleWidth = 0;  // Default value for rectangle width
    @Builder.Default
    private int rectangleHeight = 0;  // Default value for rectangle height

    @Builder.Default
    private String url = "https://platform3solutions.com/";  // Default URL value
    @Builder.Default
    private String linkText = "Platform 3 Solutions ";  // Default link text

    @Builder.Default
    private int width = 100;  // Default width for footer
    @Builder.Default
    private TextAlignment textAlignment = TextAlignment.LEFT;  // Default text alignment
    @Builder.Default
    private VerticalAlignment verticalAlignment = VerticalAlignment.BOTTOM;  // Default vertical alignment
    @Builder.Default
    private int fontSize = 8;  // Default font size for footer text
    @Builder.Default
    private String fontProgram = "src/main/resources/fonts/Roboto-Regular.ttf";  // Default font for footer text
    @Builder.Default
    private String fontColor = "3F3F3F";  // Default font color for footer text
    @Builder.Default
    private Border border = Border.NO_BORDER;  // Default border style for footer text

    @Builder.Default
    private String copyRightText = "Copyright © 2024. ";  // Default copyright text
    @Builder.Default
    private String allRightsReservedText = " All rights reserved.";  // Default "all rights reserved" text

    @Builder.Default
    private float textAlignmentWidth = 18f;  // Default text alignment width
    @Builder.Default
    private float textAlignmentHeight = 10f;  // Default text alignment height

    @Builder.Default
    private float pageAlignmentWidth = 30f;  // Default page alignment width
    @Builder.Default
    private float pageAlignmentHeight = 10f;

    @Builder.Default
    private Boolean isDividerNeeded = true;

    @Builder.Default
    private DividerBean dividerBean = new DividerBean();
}
