package com.p3solutions.archon_report_utility.components;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.TableRenderer;

public class RoundedTableRenderer extends TableRenderer {

  private final float borderRadius;
  private final Color borderColor;
  private final float borderWidth;
  private final Color backgroundColor;
  private final Color headerBackgroundColor;
  private final float headerHeight = 30f;

  public RoundedTableRenderer(
      Table table,
      float borderRadius,
      Color borderColor,
      float borderWidth,
      Color backgroundColor,
      Color headerBackgroundColor) {
    super(table);
    this.borderRadius = borderRadius;
    this.borderColor = borderColor;
    this.borderWidth = borderWidth;
    this.backgroundColor = backgroundColor;
    this.headerBackgroundColor = headerBackgroundColor;
  }

  @Override
  public void draw(DrawContext drawContext) {

    PdfCanvas canvas = drawContext.getCanvas();
    Rectangle rect = getOccupiedAreaBBox();

    float adjustedRadius = Math.min(borderRadius, headerHeight / 2); // Ensures proper fit
    float x = rect.getLeft() - 18;
    float y = rect.getBottom();
    float width = rect.getWidth() + 18 + 18;
    float height = rect.getHeight();
    float top = rect.getTop();
    float headerBottom = top - headerHeight;

    // 1️⃣ Draw Table Background
    canvas.saveState();
    canvas.setFillColor(backgroundColor);
    canvas.roundRectangle(x, y, width, height, adjustedRadius).fill();
    canvas.restoreState();

    // 2️⃣ **Fix Header Background Overflow by Clipping to Border**
    canvas.saveState();
    canvas.setFillColor(headerBackgroundColor);
    canvas.rectangle(x, headerBottom, width, headerHeight).clip().fill(); // Clip header inside bounds
    canvas.restoreState();

    // 3️⃣ Draw Table Border (Ensures Header Background Doesn't Exceed)
    canvas.saveState();
    canvas.setStrokeColor(borderColor)
            .setLineWidth(borderWidth)
            .roundRectangle(x, y, width, height, adjustedRadius)
            .stroke();
    canvas.restoreState();

    super.draw(drawContext);
  }
}
