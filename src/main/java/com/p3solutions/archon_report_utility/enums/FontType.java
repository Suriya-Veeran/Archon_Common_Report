package com.p3solutions.archon_report_utility.enums;

import lombok.Getter;

@Getter
public enum FontType {
  HELVETICA("Helvetica"),
  TIMES("Times-Roman"),
  COURIER("Courier"),
  ARIAL("Arial"),
  SYMBOL("Symbol"),
  ZAPFDINGBATS("ZapfDingbats"),
  FREESERIF("FreeSerif"),
  FREESANS("FreeSans"),
  TIMES_BOLD("Times-Bold"),
  TIMES_ITALIC("Times-Italic"),
  COURIER_BOLD("Courier-Bold"),
  COURIER_OBLIQUE("Courier-Oblique"),
  HELVETICA_BOLD("Helvetica-Bold"),
  HELVETICA_OBLIQUE("Helvetica-Oblique"),
  HELVETICA_BOLD_OBLIQUE("Helvetica-BoldOblique"),
  TIMES_BOLD_ITALIC("Times-BoldItalic"),
  COURIER_BOLD_OBLIQUE("Courier-BoldOblique"),
  COURIER_ITALIC("Courier-Italic"),
  ARIAL_BOLD("Arial-Bold"),
  ARIAL_ITALIC("Arial-Italic"),
  ARIAL_BOLD_ITALIC("Arial-BoldItalic"),
  TIMES_BOLD_UNDERLINE("Times-BoldUnderline"),
  ARIAL_UNDERLINE("Arial-Underline"),
  FRANKRUEHL("FrankRuhlLibre"),
  GARAMOND("Garamond"),
  TAHOMA("Tahoma"),
  VERDANA("Verdana"),
  CALIBRI("Calibri"),
  CONSOLAS("Consolas"),
  LUCIDA("Lucida"),
  ROBOTO("Roboto"),
  LOBSTER("Lobster"),
  COMIC_SANS("ComicSansMS"),
  IMPACT("Impact");

  private final String fontName;

  FontType(String fontName) {
    this.fontName = fontName;
  }


}
