package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import driver.DriverManagerFactory;
import hooks.DefaultProperties;
import org.openqa.selenium.support.PageFactory;
import pages.Inicio;
import pages.Login;

import static org.junit.Assert.assertTrue;

public class LoginSteps extends DriverManagerFactory implements DefaultProperties {

    private Inicio inicio = PageFactory.initElements(getDriver(), Inicio.class);
    private Login login = PageFactory.initElements(getDriver(), Login.class);

    @Dado("Eu acesso a home do site")
    public void euAcessoAHomeDoSite() {
        login.acessaAplicacao();
    }

    @Quando("Eu informo dados de login válidos")
    public void EuinformoDadosDeLoginVálidos() {
        login.entra(LOGON_GESTOR,SENHA_GESTOR,LOGON_SERASA,SENHA_SERASA);
    }

    @Entao("eu devo acessar a home e ver uma mensagem com o nome de usuário")
    public void euDevoAcessarAHomeEVerUmaMensagemComONomeDeUsuário() {
        assertTrue(inicio.validaEntrada(NOME));
    }

}
