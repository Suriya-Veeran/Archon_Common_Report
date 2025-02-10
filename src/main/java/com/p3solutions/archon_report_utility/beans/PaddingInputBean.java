package com.p3solutions.archon_report_utility.beans;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaddingInputBean {

    private float commonPadding;

    private float paddingLeft;

    private float paddingRight;

    private float paddingTop;

    private float paddingBottom;

}
