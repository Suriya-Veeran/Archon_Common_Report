package com.p3solutions.archon_report_utility.beans;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FontConfigBean {

    private String fontName;

    private PdfFont font;

    private Color fontColor;

    private float fontSize;

}
