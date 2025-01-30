package com.p3solutions.archon_report_utility.constants;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColorConstants {
    public static final Color BLACK = new DeviceRgb(0, 0, 0);
    public static final Color BLUE = new DeviceRgb(0, 0, 255);
    public static final Color CYAN = new DeviceRgb(0, 255, 255);
    public static final Color DARK_GRAY = new DeviceRgb(64, 64, 64);
    public static final Color GRAY = new DeviceRgb(128, 128, 128);
    public static final Color GREEN = new DeviceRgb(0, 255, 0);
    public static final Color DARK_GREEN = new DeviceRgb(0, 100, 0);
    public static final Color LIGHT_GRAY = new DeviceRgb(192, 192, 192);
    public static final Color MAGENTA = new DeviceRgb(255, 0, 255);
    public static final Color ORANGE = new DeviceRgb(255, 200, 0);
    public static final Color PINK = new DeviceRgb(255, 175, 175);
    public static final Color RED = new DeviceRgb(255, 0, 0);
    public static final Color DARK_RED = new DeviceRgb(139, 0, 0);
    public static final Color YELLOW = new DeviceRgb(255, 255, 0);
    public static final Color FIELDS_GREY_COLOR = new DeviceRgb(238, 238, 238);



    public static final Color WHITE = new DeviceRgb(255, 255, 255);
}
