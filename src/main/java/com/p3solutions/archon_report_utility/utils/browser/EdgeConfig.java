package com.p3solutions.archon_report_utility.utils.browser;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static com.p3solutions.archon_report_utility.constants.WebDriverConfigConstants.*;

@UtilityClass
public class EdgeConfig {
    public EdgeDriver createDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(HEADLESS_MODE);
        options.addArguments(DISABLE_GPU);
        options.addArguments(WINDOW_SIZE);
        return new EdgeDriver(options);
    }
}
