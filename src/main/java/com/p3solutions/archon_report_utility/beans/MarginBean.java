package com.p3solutions.archon_report_utility.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarginBean {

    @Builder.Default
    private float leftMargin = -18f;

    @Builder.Default
    private float rightMargin = -18f;

    @Builder.Default
    private float bottomMargin = -18f;

    @Builder.Default
    private float topMargin = -10f;



}
