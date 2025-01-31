package com.p3solutions.archon_report_utility.utils.webdriver;

import com.p3solutions.archon_report_utility.utils.browser.ChromeConfig;
import com.p3solutions.archon_report_utility.utils.browser.EdgeConfig;
import com.p3solutions.archon_report_utility.utils.browser.FirefoxConfig;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

@UtilityClass
public class WebDriverConfig {

    public static WebDriver getInstance(String browser) {
        browser = browser.toLowerCase(Locale.ROOT);

        return switch (browser) {
            case "chrome" -> ChromeConfig.createDriver();
            case "edge" -> EdgeConfig.createDriver();
            case "firefox" -> FirefoxConfig.createFirefoxDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

    }

}
