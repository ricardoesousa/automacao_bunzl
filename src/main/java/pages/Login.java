package pages;

import driver.DriverManagerFactory;
import hooks.DefaultProperties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import report.Report;

public class    Login extends DriverManagerFactory implements DefaultProperties {

    @FindBy(name="txtLogonGestor")
    private WebElement txtLogonGestor;
    @FindBy(name="txtSenhaGestor")
    private WebElement txtSenhaGestor;
    @FindBy(name="txtLogonSerasa")
    private WebElement txtLogonSerasa;
    @FindBy(name="txtSenhaSerasa")
    private WebElement txtSenhaSerasa;
    @FindBy(id="imBtEntrar")
    private WebElement btnEntrar;

    public void acessaAplicacao() {
        getDriver().get(URL_BASE);
        Report.tiraFotoDaTela();
    }

    public void entra(String logonGestor, String senhaGestor, String logonSerasa, String senhaSerasa) {
        txtLogonGestor.sendKeys(logonGestor);
        txtSenhaGestor.sendKeys(senhaGestor);
        txtLogonSerasa.sendKeys(logonSerasa);
        txtSenhaSerasa.sendKeys(senhaSerasa);
        Report.tiraFotoDaTela();
        btnEntrar.click();
    }

}
