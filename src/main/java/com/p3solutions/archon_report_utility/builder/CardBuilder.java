package com.p3solutions.archon_report_utility.builder;

import com.p3solutions.archon_report_utility.beans.CardBean;
import com.p3solutions.archon_report_utility.enums.CardType;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CardBuilder {

    public static CardBean buildCard(String header, CardType cardType, Map<String,String> parameters,
                                     String generatedTime,
                                     String value) {
        return CardBean.
                builder()
                .cardType(cardType)
                .header(header)
                .generatedTime(generatedTime)
                .content(value)
                .parameters(parameters)
                .build();
    }

}
