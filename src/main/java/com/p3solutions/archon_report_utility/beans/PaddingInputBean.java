package com.p3solutions.archon_report_utility.beans;

import com.p3solutions.archon_report_utility.enums.PaddingType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaddingInputBean {

    @Builder.Default private PaddingType paddingType = PaddingType.COMMON_PADDING;

    @Builder.Default
    private float commonPadding = 0.0f;

    @Builder.Default
    private float paddingLeft = 0.0f;

    @Builder.Default
    private float paddingRight = 0.0f;

    @Builder.Default
    private float paddingTop = 0.0f;

    @Builder.Default
    private float paddingBottom = 0.0f;

}
