package pages;

import driver.DriverManagerFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnaliseCredito extends DriverManagerFactory {

    @FindBy(id = "txtCNPJ")
    private WebElement txtCNPJ;

    @FindBy(id = "txtValor")
    private WebElement txtValor;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10083")
    private WebElement selectAutoFalencia;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10091")
    private WebElement selectConcordata;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10092")
    private WebElement selectConcordataDeferida;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10093")
    private WebElement selectConcordataRequerida;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10094")
    private WebElement selectConcordataSuspensiva;

    @FindBy(id = "nome10095")
    private WebElement inputDataDeEntradaDoSocioMaisRecente;

    @FindBy(id = "nome10101")
    private WebElement inputDataDeInicioDaAtividade;

    @FindBy(id = "nome10100")
    private WebElement inputDataDoSistema;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10084")
    private WebElement selectFalenciaDecretada;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10085")
    private WebElement selectFalenciaRequerida;

    @FindBy(id = "nome10078")
    private WebElement inputHistoricoDeAtrasos;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10081")
    private WebElement selectModalidadeDePagamento;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10086")
    private WebElement selectParticipacaoEmFalencia;

    @FindBy(id = "nome10096")
    private WebElement inputQtdRefinBancos;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10087")
    private WebElement selectRecuperacaoExtraJudicialHomologada;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10088")
    private WebElement selectRecuperacaoExtraJudicialRequerida;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10089")
    private WebElement selectRecuperacaoJudicialConcedida;

    @FindBy(id = "ctl00_ctl00_MainContent_cphBody_nome10090")
    private WebElement selectRecuperacaoJudicialRequerida;

    @FindBy(id = "nome10082")
    private WebElement inputStatusDoCNPJ;

    @FindBy(id = "nome10079")
    private WebElement inputTempoDeRelacionamento;

    @FindBy(id = "nome10077")
    private WebElement inputValoresEmAberto;

    @FindBy(id = "nome10097")
    private WebElement inputVrRefinBancos;

    @FindBy(id = "ImBtAvaliar")
    private WebElement botaoAvaliar;

    public String formataDataFormulario(String DATA) throws ParseException {
        if (DATA.equals("#")) {
            return DATA;
        } else {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formato.parse(DATA);
            formato.applyPattern("ddMMyyyy");
            String dataFormatada = formato.format(data);
            System.out.println("Data: " + dataFormatada);
            return dataFormatada;
        }
    }

    public void preencheCNPJ(String CNPJ) {
        txtCNPJ.sendKeys(CNPJ);
        txtCNPJ.sendKeys(CNPJ);
    }

    public void preencheValorNF(String VALOR_NF) {
        txtValor.sendKeys(VALOR_NF);
    }

    public void selecionaAutoFalencia(String AUTO_FALENCIA) {
            new Select(selectAutoFalencia).selectByVisibleText(AUTO_FALENCIA);
    }

    public void selecionaConcordataRequerida(String CONCORDATA_REQUERIDA) {
        new Select(selectConcordataRequerida).selectByVisibleText(CONCORDATA_REQUERIDA);
    }

    public void selecionaConcordataSuspensiva(String CONCORDATA_SUSPENSIVA) {
        new Select(selectConcordataSuspensiva).selectByVisibleText(CONCORDATA_SUSPENSIVA);
    }

    public void preencheDataDeEntradaDoSocioMaisRecente(String DATA_ENTRADA_SOCIO_MAIS_RECENTE) throws ParseException {
        inputDataDeEntradaDoSocioMaisRecente.sendKeys(formataDataFormulario(DATA_ENTRADA_SOCIO_MAIS_RECENTE));
    }

    public void selecionaFalenciaDecretada(String FALENCIA_DECRETADA) {
        new Select(selectFalenciaDecretada).selectByVisibleText(FALENCIA_DECRETADA);
    }

    public void selecionaFalenciaRequerida(String FALENCIA_REQUERIDA) {
        new Select(selectFalenciaRequerida).selectByVisibleText(FALENCIA_REQUERIDA);
    }

    public void preencheHistoricoDeAtrasos(String HISTORICO_ATRASOS) {
        inputHistoricoDeAtrasos.sendKeys(HISTORICO_ATRASOS);
    }

    public void selecionaParticipacaoEmFalencia(String PARTICIPACAO_EM_FALENCIA) {
        new Select(selectParticipacaoEmFalencia).selectByVisibleText(PARTICIPACAO_EM_FALENCIA);
    }

    public void preencheQtdRefinBancos(String QT_REFIN_BANCOS) {
        inputQtdRefinBancos.sendKeys(QT_REFIN_BANCOS);
    }

    public void selecionaRecuperacaoJudicialConcedida(String RECUPERACAO_JUDICIAL_CONCEDIDA) {
        new Select(selectRecuperacaoJudicialConcedida).selectByVisibleText(RECUPERACAO_JUDICIAL_CONCEDIDA);
    }

    public void selecionaRecuperacaoJudicialRequerida(String RECUPERACAO_JUDICIAL_REQUERIDA) {
        new Select(selectRecuperacaoJudicialRequerida).selectByVisibleText(RECUPERACAO_JUDICIAL_REQUERIDA);
    }

    public void preencheStatusDoCNPJ(String STATUS_CNPJ) {
        inputStatusDoCNPJ.sendKeys(STATUS_CNPJ);
    }

    public void preencheTempoDeRelacionamento(String TEMPO_RELACIONAMENTO) {
        inputTempoDeRelacionamento.sendKeys(TEMPO_RELACIONAMENTO);
    }

    public void preencheValoresEmAberto(String VALORES_EM_ABERTO) {
        inputValoresEmAberto.sendKeys(VALORES_EM_ABERTO);
    }

    public void clicaBotaoAvaliar() {
        botaoAvaliar.click();
    }


    public void preencheModalidadeDePagamento(String MODALIDADE_DE_PAGAMENTO) {
        new Select(selectModalidadeDePagamento).selectByVisibleText(MODALIDADE_DE_PAGAMENTO);
    }

    public void preencheDataDoSistema(String DATA_DO_SISTEMA) throws ParseException {
        inputDataDoSistema.sendKeys(formataDataFormulario(DATA_DO_SISTEMA));
    }

    public void preencheDataDeInicioDaAtividade(String DATA_DE_INICIO_DA_ATIVIDADE) throws ParseException {
        inputDataDeInicioDaAtividade.sendKeys(formataDataFormulario(DATA_DE_INICIO_DA_ATIVIDADE));

    }

    public void selecionaConcordata(String concordata) {
        new Select(selectConcordata).selectByVisibleText(concordata);

    }


    public void selecionaConcordataDeferida(String concordata_deferida) {
        new Select(selectConcordataDeferida).selectByVisibleText(concordata_deferida);

    }

    public void selecionaRecuperacaoExtraJudicialHomologada(String recuperacao_extra_judicial_homologada) {
        new Select(selectRecuperacaoExtraJudicialHomologada).selectByVisibleText(recuperacao_extra_judicial_homologada);

    }

    public void selecionaRecuperacaoExtraJudicialRequerida(String recuperacao_extra_judicial_requerida) {
        new Select(selectRecuperacaoExtraJudicialRequerida).selectByVisibleText(recuperacao_extra_judicial_requerida);

    }

    public void preencheVrRefinBancos(String vr_refin_bancos) {
        inputVrRefinBancos.sendKeys(vr_refin_bancos);

    }
}

