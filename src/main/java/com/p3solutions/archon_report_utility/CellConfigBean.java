package com.p3solutions.archon_report_utility;

import com.itextpdf.layout.element.Image;
import com.p3solutions.archon_report_utility.beans.*;
import com.p3solutions.archon_report_utility.enums.CellContentType;
import com.p3solutions.archon_report_utility.enums.CellType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CellConfigBean {

    @Builder.Default
    private String content = "";

    @Builder.Default
    private Image chart = null;

    @Builder.Default
    private float width = 0;

    @Builder.Default
    private float height = 0;

    @Builder.Default
    private float minHeight = 0;

    @Builder.Default
    private float minWidth = 0;

    @Builder.Default
    private int rowSpan = 1;

    @Builder.Default
    private int colSpan = 1;

    @Builder.Default
    private CellType cellType = CellType.DEFAULT;

    @Builder.Default
    private CellContentType cellContentType = CellContentType.PARAGRAPH;

    @Builder.Default
    private BorderBean paragraphBorder = new BorderBean();

    @Builder.Default
    private PaddingInputBean paragraphPadding = new PaddingInputBean();

    @Builder.Default
    private MarginBean paragraphMargin = new MarginBean();

    @Builder.Default
    private AlignmentBean paragraphAlignment = new AlignmentBean();

    @Builder.Default
    private FontConfigBean paragraphFont = new FontConfigBean();

    @Builder.Default
    private Boolean fixedLayout = false;

    @Builder.Default
    private String backgroundColor = "";
}
