package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

import static driver.DriverManagerFactory.driver;
import static org.junit.Assert.*;

public class ResultadoAnalise {

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_MainContent_cphBody_Datagrid\"]/tbody/tr[3]/td")
    private WebElement lblCNPJConsultado;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_MainContent_cphBody_Datagrid\"]/tbody/tr[5]/td")
    private WebElement lblPoliticaAplicada;

    @FindBy(xpath="//*[@id=\"ctl00_ctl00_MainContent_cphBody_Datagrid\"]/tbody/tr[9]/td")
    private WebElement lblValorNF;

    @FindBy(css="#ctl00_ctl00_MainContent_cphBody_Datagrid > tbody > tr:nth-child(11) > td")
    private WebElement lblMsgRecomendacao;

    @FindBy(id="ctl00_ctl00_MainContent_cphBody_btDados")
    private WebElement btnDados;

    @FindBy(xpath="//*[@id=\"gdvDados\"]/tbody/tr[2]/td[2]")
    private WebElement lblChaveDoAnalista;

    @FindBy(id="ctl00_ctl00_MainContent_cphBody_btnAvancar")
    private WebElement btnProsseguir;

    @FindBy(css="#ctl00_ctl00_MainContent_cphBody_Datagrid > tbody > tr:nth-child(7) > td")
    private WebElement lblLimiteSugerido;

    public void validaMsgResultadoDaAnalise(String MSG_RESULTADO_ANALISE) {
        String txtMsgRecomendacao = lblMsgRecomendacao.getText();
        System.out.println(">> lblMsgTXT: " + txtMsgRecomendacao);
        assertTrue(txtMsgRecomendacao.contains(MSG_RESULTADO_ANALISE));
    }

    public void validaValorNF(String VALOR_NF) {
        String txtValorNF = lblValorNF.getText();
        assertTrue(txtValorNF.contains("VALOR DA COMPRA: R$ "+VALOR_NF));
    }

    public void validaLimiteSugerido(String LIMITE_SUGERIDO) {
        assertTrue(lblLimiteSugerido.getText().contains("LIMITE SUGERIDO: R$ "+LIMITE_SUGERIDO));
    }

    public void validaCNPJConsultado(String CNPJ) {
        String txtCNPJConsultado = lblCNPJConsultado.getText();
        assertTrue(txtCNPJConsultado.contains(CNPJ));
    }

    public void clicaBotaoDados(){
        btnDados.click();
    }

    public void finalizaAnalise() {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

}