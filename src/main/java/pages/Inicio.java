package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import report.Report;

import static driver.DriverManagerFactory.driver;

public class Inicio {

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_menuPrincipal\"]/ul[2]/li[3]/a/i")
    private WebElement btnMostrarPerfil;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_menuPrincipal\"]/ul[2]/li[3]/ul/li[1]/text()")
    private WebElement lblNomeDoUsuario;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_itemModuloPolitica\"]/a")
    private WebElement btnPolitica;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_itemModuloDecisao\"]/a")
    private WebElement btnDecisao;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_MainContent_cphBody_analisePJ\"]")
    private WebElement btnAnaliseDeEmpresa;

    @FindBy(xpath="//*[@id=\"driver-popover-item\"]/div[4]/button")
    private WebElement btnSairModal;

    @FindBy(xpath="//*[@id=\"driver-popover-item\"]/div[4]/button")
    private WebElement btnSairModal2;

    @FindBy(xpath="//*[@id=\"TS-menuLateral_lista\"]/li[8]/a/i")
    private WebElement btnFerramentas;

    @FindBy(xpath="//*[@id=\"TS-menuLateral_lista\"]/li[8]/div/a[1]/p")
    private WebElement btnHomologarPolitica;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_MainContent_cphBody_bt_Homologar\"]")
    private WebElement btnHomologar;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_MainContent_cphBody_Datagrid\"]/tbody/tr[3]/td")
    private WebElement lblCNPJConsultado;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_MainContent_cphBody_Datagrid\"]/tbody/tr[5]/td")
    private WebElement lblPoliticaAplicada;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_MainContent_cphBody_Datagrid\"]/tbody/tr[9]/td")
    private WebElement lblValorDaCompra;

    @FindBy(css="#ctl00_ctl00_MainContent_cphBody_Datagrid > tbody > tr:nth-child(11) > td")
    private WebElement lblMsgRecomendacao;

    @FindBy(id="ctl00_ctl00_MainContent_cphBody_btDados")
    private WebElement btnDados;

    @FindBy(xpath="//*[@id=\"gdvDados\"]/tbody/tr[2]/td[2]")
    private WebElement txtChaveDoAnalista2;

    @FindBy(id="ctl00_ctl00_MainContent_cphBody_btnAvancar")
    private WebElement btnProsseguir;

    public void clicaBotaoSairModal() {
        try {
            jsClick(btnSairModal);
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            jsClick(btnSairModal);
        }
    }


    public boolean validaEntrada(String valEntrada) {
        Report.tiraFotoDaTela();
        try {
            jsClick(btnSairModal);
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            jsClick(btnSairModal);
        }
        jsClick(btnSairModal);
        jsClick(btnMostrarPerfil);
        Report.tiraFotoDaTela();
        return true;
    }

    public static void jsClick(WebElement elem) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", elem
        );
    }

    public void acessaAnaliseDeCredito () {
        jsClick(btnDecisao);
        Report.tiraFotoDaTela();
        jsClick(btnSairModal);
        jsClick(btnAnaliseDeEmpresa);
        Report.tiraFotoDaTela();
    }

    public void clicaAbaDecisao () {
        jsClick(btnDecisao);
    }

    public void clicaBotaoAnaliseDeEmpresa () {
        jsClick(btnAnaliseDeEmpresa);
    }



}