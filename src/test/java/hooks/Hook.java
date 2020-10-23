package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import driver.DriverManager;
import driver.DriverManagerFactory;
import driver.DriverType;
import report.Report;

import java.util.concurrent.TimeUnit;

public class Hook extends DriverManagerFactory implements DefaultProperties{

    DriverManager driverManager;

    @Before
    public void init(Scenario scenario){
        Report.scenario = scenario;
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(DefaultProperties.TIME_OUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        Report.tiraFotoDaTela();
        driver.quit();
    }
}
