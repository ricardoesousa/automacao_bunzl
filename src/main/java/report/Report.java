package report;

import cucumber.api.Scenario;
import driver.DriverManagerFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class Report {

    public static Scenario scenario;

    public static void tiraFotoDaTela() {
        try {
            final byte[] screenshot = ((TakesScreenshot) DriverManagerFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }
//
//    @Attachment(value = "Foto da Tela", type = "image/png")
//    public static byte[] tiraFotoDaTela() {
//        return ((TakesScreenshot) DriverManagerFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//    }
}
