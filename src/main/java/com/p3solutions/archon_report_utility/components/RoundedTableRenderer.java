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
  private final float leftMargin;
  private final float rightMargin;

  public RoundedTableRenderer(
      Table modelElement,
      float borderRadius,
      Color borderColor,
      float borderWidth,
      float leftMargin,
      float rightMargin) {
    super(modelElement);
    this.borderRadius = borderRadius;
    this.borderColor = borderColor;
    this.borderWidth = borderWidth;
    this.leftMargin = leftMargin;
    this.rightMargin = rightMargin;
  }

  @Override
  public void draw(DrawContext drawContext) {
    super.draw(drawContext);

    Rectangle rect = getOccupiedAreaBBox();
    PdfCanvas canvas = drawContext.getCanvas();

    float adjustedX = rect.getX() + leftMargin - (borderWidth / 2);
    float adjustedY = rect.getY() ;
    float adjustedWidth = rect.getWidth() - leftMargin - rightMargin + borderWidth;
    float adjustedHeight = rect.getHeight();

    canvas
        .saveState()
        .setStrokeColor(borderColor)
        .setLineWidth(borderWidth)
            .roundRectangle(adjustedX, adjustedY, adjustedWidth, adjustedHeight, borderRadius)
        .stroke()
        .restoreState();
  }

  @Override
  public TableRenderer getNextRenderer() {
    return new RoundedTableRenderer(
        (Table) modelElement, borderRadius, borderColor, borderWidth, leftMargin, rightMargin);
  }
}
