package com.p3solutions.archon_report_utility.components;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.p3solutions.archon_report_utility.beans.GridTableBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.GREY_COLOR;
import static com.p3solutions.archon_report_utility.utils.ColorUtils.hexaDecimalToRGB;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GridTableComponent implements ReportComponent {

    private GridTableBean inputBean;

    @Override
    public void render(Document document) throws IOException {
        if (inputBean.getParameterMap() != null && !inputBean.getParameterMap().isEmpty()) {
            Table table = null;
            switch (inputBean.getType()) {
                case POINT_COLUMN_WIDTH:
                    table = new Table(UnitValue.createPercentArray(inputBean.getPointColumnWidth()));
                    break;
                case NUMBER_OF_COLUMNS:
                    table = new Table(inputBean.getNumberOfColumns());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported type: " + inputBean.getType());
            }

            table.setWidth(UnitValue.createPercentValue(inputBean.getWidth()));
            table.setKeepTogether(inputBean.isKeepTogether());
            table.setMargin(20); // Add margin around the table
            table.setBorder(new SolidBorder(1));

            Map<String, List<String>> parameterMap = inputBean.getParameterMap();
            for (String header : parameterMap.keySet()) {
                Cell headerCell = new Cell().add(new Paragraph(header)
                                .setFont(PdfFontFactory.createFont(HELVETICA_BOLD)))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(hexaDecimalToRGB(GREY_COLOR))
                        .setPadding(5);
                table.addCell(headerCell);
            }

            int rowCount = parameterMap.values().iterator().next().size();
            for (int i = 0; i < rowCount; i++) {
                for (List<String> values : parameterMap.values()) {
                    Cell cell = new Cell().add(new Paragraph(values.get(i)))
                            .setBorderTop(Border.NO_BORDER)
                            .setBorderLeft(Border.NO_BORDER)
                            .setBorderRight(Border.NO_BORDER)
                            .setBorderBottom(new SolidBorder(0.5f))
                            .setPadding(5);
                    table.addCell(cell);
                }
            }

            document.add(table);
        }
    }


}
