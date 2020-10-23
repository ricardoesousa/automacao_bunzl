package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import driver.DriverManagerFactory;
import hooks.DefaultProperties;
import org.openqa.selenium.support.PageFactory;
import pages.AnaliseCredito;
import pages.DecisaoCredito;
import pages.Inicio;
import pages.ResultadoAnalise;
import report.Report;
import sql.SQLConnector;

import java.sql.SQLException;
import java.text.ParseException;

public class AnaliseSteps extends DriverManagerFactory implements DefaultProperties {

    private final Inicio inicio = PageFactory.initElements(getDriver(), Inicio.class);
    private final AnaliseCredito analiseDeCredito = PageFactory.initElements(getDriver(), AnaliseCredito.class);
    private final ResultadoAnalise resultadoDaAnalise = PageFactory.initElements(getDriver(), ResultadoAnalise.class);
    private final DecisaoCredito decisaoDeCredito = PageFactory.initElements(getDriver(), DecisaoCredito.class);
    private final SQLConnector sqlConnector = PageFactory.initElements(getDriver(), SQLConnector.class);

    @Dado("eu acesso a tela de análise de crédito")
    public void euAcessoATelaDeAnaliseDeCredito() {

        inicio.clicaAbaDecisao();
        Report.tiraFotoDaTela();
        inicio.clicaBotaoSairModal();
        inicio.clicaBotaoAnaliseDeEmpresa();
        Report.tiraFotoDaTela();
    }

    @E("configuro a massa com os dados CMA-PJ: {string}, {string}, {string}, {string}")
    public void alteroAMassaDeDados(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1)
            throws SQLException, ClassNotFoundException {

        sqlConnector.resetaCNPJ(sqlConnector.formataCNPJ(CNPJ), DATA_ALT);
        sqlConnector.alteraUmCampo(sqlConnector.formataCNPJ(CNPJ), DATA_ALT, CAMPO1, VALOR1);
    }

    @E("configuro a massa com os dados \\(Classificação Risco Zero): {string},{string},{string},{string},{string}," +
            "{string},{string},{string},{string},{string}")
    public void configuroAMassaComOsDadosClassificaçãoRiscoZero(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1, String CAMPO3, String VALOR3,
                                                                String CAMPO4, String VALOR4, String CAMPO5, String VALOR5)
            throws SQLException, ClassNotFoundException {
        sqlConnector.resetaCNPJ(sqlConnector.formataCNPJ(CNPJ), DATA_ALT);
        sqlConnector.alteraQuatroCampos(sqlConnector.formataCNPJ(CNPJ), DATA_ALT, CAMPO1, VALOR1, CAMPO3,
                VALOR3, CAMPO4, VALOR4, CAMPO5, VALOR5);
    }

    @E("configuro a massa com os dados \\(Regra de Comportamento): {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void configuroAMassaComOsDadosRegraDeComportamento(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                                              String CAMPO2, String VALOR2, String CAMPO3, String VALOR3,
                                                              String CAMPO4, String VALOR4, String CAMPO5, String VALOR5,
                                                              String CAMPO6, String VALOR6, String CAMPO7, String VALOR7)
            throws SQLException, ClassNotFoundException {
        sqlConnector.resetaCNPJ(sqlConnector.formataCNPJ(CNPJ), DATA_ALT);
        sqlConnector.alteraSeteCampos(sqlConnector.formataCNPJ(CNPJ), DATA_ALT, CAMPO1, VALOR1, CAMPO2, VALOR2, CAMPO3,
                sqlConnector.formataValor(VALOR3), CAMPO4, VALOR4, CAMPO5, sqlConnector.formataValor(VALOR5), CAMPO6, VALOR6, CAMPO7, VALOR7);
    }

    @E("configuro a massa com os dados \\(Pontos de Corte Score 6.0): {string},{string},{string},{string},{string},{string}")
    public void configuroAMassaComOsDadosPontosDeCorteScore(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                                            String CAMPO2, String VALOR2) throws SQLException, ClassNotFoundException {
        sqlConnector.resetaCNPJ(sqlConnector.formataCNPJ(CNPJ), DATA_ALT);
        sqlConnector.alteraDoisCampos(sqlConnector.formataCNPJ(CNPJ), DATA_ALT, CAMPO1, VALOR1, CAMPO2, VALOR2);
    }

    @E("configuro a massa com os dados \\(Clientes Corporate Plus): {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void configuroAMassaComOsDadosClientesCorporate(String CNPJ, String DATA_ALT, String CAMPO_PORTE_EMPRESA, String VALOR_PORTE_EMPRESA, String CAMPO_PAGTOS_PONTUAIS,
                                                           String VALOR_PAGTOS_PONTUAIS, String CAMPO_QTD_PROTESTOS, String VALOR_QTD_PROTESTOS, String CAMPO_QTD_PEFIN,
                                                           String VALOR_QTD_PEFIN, String CAMPO_CAPITAL_SOCIAL, String VALOR_CAPITAL_SOCIAL) throws SQLException, ClassNotFoundException {
        sqlConnector.resetaCNPJ(sqlConnector.formataCNPJ(CNPJ), DATA_ALT);
        sqlConnector.alteraCincoCampos(sqlConnector.formataCNPJ(CNPJ), DATA_ALT, CAMPO_PORTE_EMPRESA, VALOR_PORTE_EMPRESA, CAMPO_PAGTOS_PONTUAIS,
                VALOR_PAGTOS_PONTUAIS, CAMPO_QTD_PROTESTOS, VALOR_QTD_PROTESTOS, CAMPO_QTD_PEFIN,
                VALOR_QTD_PEFIN, CAMPO_CAPITAL_SOCIAL, sqlConnector.formataValor(VALOR_CAPITAL_SOCIAL));
    }

    @E("configuro a massa com os dados \\(Classificação Final): {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}, {string}")
    public void configuroAMassaComOsDadosClassificaçãoFinal(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                                            String CAMPO2, String VALOR2, String CAMPO3, String VALOR3,
                                                            String CAMPO4, String VALOR4, String CAMPO5, String VALOR5,
                                                            String CAMPO6, String VALOR6, String CAMPO7, String VALOR7,
                                                            String CAMPO8, String VALOR8)
            throws SQLException, ClassNotFoundException {
        sqlConnector.resetaCNPJ(sqlConnector.formataCNPJ(CNPJ), DATA_ALT);
        sqlConnector.alteraOitoCampos(sqlConnector.formataCNPJ(CNPJ), DATA_ALT, CAMPO1, VALOR1, CAMPO2, VALOR2, CAMPO3,
                sqlConnector.formataValor(VALOR3), CAMPO4, VALOR4, CAMPO5, sqlConnector.formataValor(VALOR5), CAMPO6, VALOR6, CAMPO7, VALOR7, CAMPO8, VALOR8);
    }

    @E("configuro a massa com os dados \\(Risco Final Clientes Corporate Plus): {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}, {string}")
    public void configuroAMassaComOsDadosRiscoFinalClientesCorporatePlus(String CNPJ, String DATA_ALT, String CAMPO_PORTE_EMPRESA, String VALOR_PORTE_EMPRESA, String CAMPO_PAGTOS_PONTUAIS,
                                                                         String VALOR_PAGTOS_PONTUAIS, String CAMPO_QTD_PROTESTOS, String VALOR_QTD_PROTESTOS, String CAMPO_QTD_PEFIN,
                                                                         String VALOR_QTD_PEFIN, String CAMPO_CAPITAL_SOCIAL, String VALOR_CAPITAL_SOCIAL, String CAMPO_SCORE, String VALOR_SCORE)
            throws SQLException, ClassNotFoundException {
        sqlConnector.resetaCNPJ(sqlConnector.formataCNPJ(CNPJ), DATA_ALT);
        sqlConnector.alteraSeisCampos(sqlConnector.formataCNPJ(CNPJ), DATA_ALT, CAMPO_PORTE_EMPRESA, VALOR_PORTE_EMPRESA, CAMPO_PAGTOS_PONTUAIS,
                VALOR_PAGTOS_PONTUAIS, CAMPO_QTD_PROTESTOS, VALOR_QTD_PROTESTOS, CAMPO_QTD_PEFIN,
                VALOR_QTD_PEFIN, CAMPO_CAPITAL_SOCIAL, sqlConnector.formataValor(VALOR_CAPITAL_SOCIAL), CAMPO_SCORE, VALOR_SCORE);
    }

    @E("configuro a massa com os dados \\(Limite Disponível): {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}," +
            "{string},{string}, {string},{string},{string},{string},{string}")
    public void configuroAMassaComOsDadosLimiteDisponível(String CNPJ, String DATA_ALT, String CAMPO_PORTE_EMPRESA, String VALOR_PORTE_EMPRESA, String CAMPO_QTD_PROTESTOS, String VALOR_QTD_PROTESTOS,
                                                          String CAMPO_VR_PROTESTOS, String VALOR_VR_PROTESTOS, String CAMPO_QTD_PEFIN, String VALOR_QTD_PEFIN, String CAMPO_VR_PEFIN, String VALOR_VR_PEFIN,
                                                          String CAMPO_CONSULTAS_MES_ATUAL, String VALOR_CONSULTAS_MES_ATUAL, String CAMPO_CONSULTAS_MES_ANTERIOR, String VALOR_CONSULTAS_MES_ANTERIOR,
                                                          String CAMPO_SCORE, String VALOR_SCORE, String CAMPO_FATURAMENTO_PRESUMIDO, String VALOR_FATURAMENTO_PRESUMIDO, String CAMPO_CAPITAL_SOCIAL,
                                                          String VALOR_CAPITAL_SOCIAL)
            throws SQLException, ClassNotFoundException {
        sqlConnector.resetaCNPJ(sqlConnector.formataCNPJ(CNPJ), DATA_ALT);
        sqlConnector.alteraDezCampos(sqlConnector.formataCNPJ(CNPJ), DATA_ALT, CAMPO_PORTE_EMPRESA, VALOR_PORTE_EMPRESA, CAMPO_QTD_PROTESTOS, VALOR_QTD_PROTESTOS, CAMPO_VR_PROTESTOS,
                sqlConnector.formataValor(VALOR_VR_PROTESTOS), CAMPO_QTD_PEFIN, VALOR_QTD_PEFIN, CAMPO_VR_PEFIN, sqlConnector.formataValor(VALOR_VR_PEFIN), CAMPO_CONSULTAS_MES_ATUAL,
                VALOR_CONSULTAS_MES_ATUAL, CAMPO_CONSULTAS_MES_ANTERIOR, VALOR_CONSULTAS_MES_ANTERIOR, CAMPO_SCORE, VALOR_SCORE, CAMPO_FATURAMENTO_PRESUMIDO, sqlConnector.formataValor(VALOR_FATURAMENTO_PRESUMIDO),
                CAMPO_CAPITAL_SOCIAL, sqlConnector.formataValor(VALOR_CAPITAL_SOCIAL));


    }

    @E("no relatório de Decisão de Crédito ter os seguintes dados on-line validados: {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}," +
            "{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void noRelatórioDeDecisãoDeCréditoTerOsSeguintesDadosOnLineValidados(String CNPJ, String VALOR_NF, String AUTO_FALENCIA, String CONCORDATA, String CONCORDATA_DEFERIDA, String CONCORDATA_REQUERIDA,
                                                                                String CONCORDATA_SUSPENSIVA, String DATA_ENTRADA_SOCIO_MAIS_RECENTE, String DATA_DE_INICIO_DA_ATIVIDADE, String DATA_DO_SISTEMA,
                                                                                String FALENCIA_DECRETADA, String FALENCIA_REQUERIDA, String HISTORICO_ATRASOS, String MODALIDADE_DE_PAGAMENTO, String PARTICIPACAO_EM_FALENCIA,
                                                                                String QT_REFIN_BANCOS, String RECUPERACAO_EXTRA_JUDICIAL_HOMOLOGADA, String RECUPERACAO_EXTRA_JUDICIAL_REQUERIDA, String RECUPERACAO_JUDICIAL_CONCEDIDA,
                                                                                String RECUPERACAO_JUDICIAL_REQUERIDA, String STATUS_CNPJ, String TEMPO_RELACIONAMENTO, String VALORES_EM_ABERTO, String VR_REFIN_BANCOS) throws ParseException {
        resultadoDaAnalise.clicaBotaoDados();
        decisaoDeCredito.alternaParaAbaDecisaoDeCredito();
        Report.tiraFotoDaTela();
        decisaoDeCredito.rolaPaginaParaBaixo();
        Report.tiraFotoDaTela();
        decisaoDeCredito.validaCNPJ(CNPJ);
        decisaoDeCredito.validaRelatorioDadosOnline(VALOR_NF, AUTO_FALENCIA, CONCORDATA, CONCORDATA_DEFERIDA, CONCORDATA_REQUERIDA,
                CONCORDATA_SUSPENSIVA, DATA_ENTRADA_SOCIO_MAIS_RECENTE, DATA_DE_INICIO_DA_ATIVIDADE, DATA_DO_SISTEMA,
                FALENCIA_DECRETADA, FALENCIA_REQUERIDA, HISTORICO_ATRASOS, MODALIDADE_DE_PAGAMENTO, PARTICIPACAO_EM_FALENCIA,
                QT_REFIN_BANCOS, RECUPERACAO_EXTRA_JUDICIAL_HOMOLOGADA, RECUPERACAO_EXTRA_JUDICIAL_REQUERIDA, RECUPERACAO_JUDICIAL_CONCEDIDA,
                RECUPERACAO_JUDICIAL_REQUERIDA, STATUS_CNPJ, TEMPO_RELACIONAMENTO, VALORES_EM_ABERTO, VR_REFIN_BANCOS);
    }

    @E("no relatório de Decisão de Crédito ter os seguintes dados \\(Classificação Risco Zero) validados: {string},{string},{string},{string},{string}")
    public void noRelatórioDeDecisãoDeCréditoTerOsSeguintesDadosClassificaçãoRiscoZeroValidados(String VALOR_SCORE, String VALOR_PAGTOS_PONTUAIS, String VALOR_CONSULTAS_MES_ATUAL,
                                                                                                String VALOR_CONSULTAS_MES_ANTERIOR, String PONTUACAO) {
        decisaoDeCredito.validaRelatorioRiscoZero(VALOR_SCORE, VALOR_PAGTOS_PONTUAIS, VALOR_CONSULTAS_MES_ATUAL, VALOR_CONSULTAS_MES_ANTERIOR, PONTUACAO);
    }

    @E("no relatório de Decisão de Crédito ter os seguintes dados \\(Regra de Comportamento) validados: {string},{string},{string},{string},{string},{string},{string},{string}")
    public void noRelatórioDeDecisãoDeCréditoTerOsSeguintesDadosRegraDeComportamentoValidados(String VALOR_PORTE_EMPRESA, String VALOR_QTD_PROTESTOS, String VALOR_VR_PROTESTOS,
                                                                                              String VALOR_QTD_PEFIN, String VALOR_VR_PEFIN, String VALOR_CONSULTAS_MES_ATUAL,
                                                                                              String VALOR_CONSULTAS_MES_ANTERIOR, String PONTUACAO) {
        decisaoDeCredito.validaRelatorioRegraDeComportamento(VALOR_PORTE_EMPRESA, VALOR_QTD_PROTESTOS, VALOR_VR_PROTESTOS, VALOR_QTD_PEFIN, VALOR_VR_PEFIN, VALOR_CONSULTAS_MES_ATUAL,
                VALOR_CONSULTAS_MES_ANTERIOR, PONTUACAO);
    }

    @E("no relatório de Decisão de Crédito ter os seguintes dados \\(Pontos de Corte Score 6.0) validados: {string},{string},{string},{string}")
    public void noRelatórioDeDecisãoDeCréditoTerOsSeguintesDadosPontosDeCorteScoreValidados(String VALOR_PORTE_EMPRESA, String VALOR_SCORE, String PONTUACAO, String RISCO) {
        decisaoDeCredito.validaRelatorioPontosScore(VALOR_PORTE_EMPRESA, VALOR_SCORE, PONTUACAO, RISCO);
    }

    @E("no relatório de Decisão de Crédito ter os seguintes dados \\(Clientes Corporate Plus) validados: {string},{string},{string},{string},{string},{string}")
    public void noRelatórioDeDecisãoDeCréditoTerOsSeguintesDadosClientesCorporatePlusValidados(String VALOR_PORTE_EMPRESA, String VALOR_PAGTOS_PONTUAIS, String VALOR_QTD_PROTESTOS,
                                                                                               String VALOR_QTD_PEFIN, String VALOR_CAPITAL_SOCIAL, String PONTUACAO) {
        decisaoDeCredito.validaRelatorioClientesCorporatePlus(VALOR_PORTE_EMPRESA, VALOR_PAGTOS_PONTUAIS, VALOR_QTD_PROTESTOS, VALOR_QTD_PEFIN, VALOR_CAPITAL_SOCIAL, PONTUACAO);
    }

    @E("no relatório de Decisão de Crédito ter os seguintes dados \\(Classificação Final) validados: {string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void noRelatórioDeDecisãoDeCréditoTerOsSeguintesDadosClassificaçãoFinalValidados(String VALOR_PORTE_EMPRESA, String VALOR_QTD_PROTESTOS, String VALOR_VR_PROTESTOS,
                                                                                            String VALOR_QTD_PEFIN, String VALOR_VR_PEFIN, String VALOR_CONSULTAS_MES_ATUAL,
                                                                                            String VALOR_CONSULTAS_MES_ANTERIOR, String PONTUACAO, String VALOR_SCORE) {
        decisaoDeCredito.validaRelatorioRiscoFInal(VALOR_PORTE_EMPRESA, VALOR_QTD_PROTESTOS, VALOR_VR_PROTESTOS, VALOR_QTD_PEFIN, VALOR_VR_PEFIN, VALOR_CONSULTAS_MES_ATUAL,
                VALOR_CONSULTAS_MES_ANTERIOR, PONTUACAO, VALOR_SCORE);
    }

    @E("no relatório de Decisão de Crédito ter os seguintes dados \\(Risco Final Clientes Corporate Plus) validados: {string},{string},{string},{string},{string},{string},{string}")
    public void noRelatórioDeDecisãoDeCréditoTerOsSeguintesDadosRiscoFinalClientesCorporatePlusValidados(String VALOR_PORTE_EMPRESA, String VALOR_PAGTOS_PONTUAIS, String VALOR_QTD_PROTESTOS,
                                                                                                         String VALOR_QTD_PEFIN, String VALOR_CAPITAL_SOCIAL, String PONTUACAO, String VALOR_SCORE) {
        decisaoDeCredito.validaRelatorioRiscoFinalClientesCorporatePlus(VALOR_PORTE_EMPRESA, VALOR_PAGTOS_PONTUAIS, VALOR_QTD_PROTESTOS, VALOR_QTD_PEFIN, VALOR_CAPITAL_SOCIAL, PONTUACAO, VALOR_SCORE);
    }

    @E("no relatório de Decisão de Crédito ter os seguintes dados \\(Limite Disponível) validados: {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void noRelatórioDeDecisãoDeCréditoTerOsSeguintesDadosLimiteDisponívelValidados(String VALOR_PORTE_EMPRESA, String VALOR_QTD_PROTESTOS, String VALOR_VR_PROTESTOS,
                                                                                          String VALOR_QTD_PEFIN, String VALOR_VR_PEFIN, String VALOR_CONSULTAS_MES_ATUAL,
                                                                                          String VALOR_CONSULTAS_MES_ANTERIOR, String PONTUACAO, String VALOR_SCORE,
                                                                                          String VALOR_FATURAMENTO_PRESUMIDO, String VALOR_CAPITAL_SOCIAL) {
        decisaoDeCredito.validaRelatorioLimiteDisponivel(VALOR_PORTE_EMPRESA, VALOR_QTD_PROTESTOS, VALOR_VR_PROTESTOS, VALOR_QTD_PEFIN, VALOR_VR_PEFIN, VALOR_CONSULTAS_MES_ATUAL,
                VALOR_CONSULTAS_MES_ANTERIOR, PONTUACAO, VALOR_SCORE, VALOR_FATURAMENTO_PRESUMIDO, VALOR_CAPITAL_SOCIAL);
    }


    @Entao("eu devo ver uma página com o resultado da análise: {string}, {string}")
    public void euDevoVerUmaMensagemDeSucesso(String MSG_RESULTADO_ANALISE, String VALOR_NF) {

        Report.tiraFotoDaTela();
        resultadoDaAnalise.validaMsgResultadoDaAnalise(MSG_RESULTADO_ANALISE);
        resultadoDaAnalise.validaValorNF(VALOR_NF);
    }


    @Quando("informo os seguintes dados on-line e clico em Analisar: {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}," +
            "{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void informoOsSeguintesDadosOnLineEClicoEmAnalisar(String CNPJ, String VALOR_NF, String AUTO_FALENCIA, String CONCORDATA, String CONCORDATA_DEFERIDA, String CONCORDATA_REQUERIDA,
                                                              String CONCORDATA_SUSPENSIVA, String DATA_ENTRADA_SOCIO_MAIS_RECENTE, String DATA_DE_INICIO_DA_ATIVIDADE, String DATA_DO_SISTEMA,
                                                              String FALENCIA_DECRETADA, String FALENCIA_REQUERIDA, String HISTORICO_ATRASOS, String MODALIDADE_DE_PAGAMENTO, String PARTICIPACAO_EM_FALENCIA,
                                                              String QT_REFIN_BANCOS, String RECUPERACAO_EXTRA_JUDICIAL_HOMOLOGADA, String RECUPERACAO_EXTRA_JUDICIAL_REQUERIDA, String RECUPERACAO_JUDICIAL_CONCEDIDA,
                                                              String RECUPERACAO_JUDICIAL_REQUERIDA, String STATUS_CNPJ, String TEMPO_RELACIONAMENTO, String VALORES_EM_ABERTO, String VR_REFIN_BANCOS) throws ParseException {

        analiseDeCredito.preencheCNPJ(CNPJ);
        analiseDeCredito.preencheValorNF(VALOR_NF);
        analiseDeCredito.selecionaAutoFalencia(AUTO_FALENCIA);
        analiseDeCredito.selecionaConcordata(CONCORDATA);
        analiseDeCredito.selecionaConcordataDeferida(CONCORDATA_DEFERIDA);
        analiseDeCredito.selecionaConcordataRequerida(CONCORDATA_REQUERIDA);
        analiseDeCredito.selecionaConcordataSuspensiva(CONCORDATA_SUSPENSIVA);
        analiseDeCredito.preencheDataDeEntradaDoSocioMaisRecente(DATA_ENTRADA_SOCIO_MAIS_RECENTE);
        analiseDeCredito.preencheDataDeInicioDaAtividade(DATA_DE_INICIO_DA_ATIVIDADE);
        analiseDeCredito.preencheDataDoSistema(DATA_DO_SISTEMA);
        analiseDeCredito.selecionaFalenciaDecretada(FALENCIA_DECRETADA);
        analiseDeCredito.selecionaFalenciaRequerida(FALENCIA_REQUERIDA);
        analiseDeCredito.preencheHistoricoDeAtrasos(HISTORICO_ATRASOS);
        analiseDeCredito.preencheModalidadeDePagamento(MODALIDADE_DE_PAGAMENTO);
        analiseDeCredito.selecionaParticipacaoEmFalencia(PARTICIPACAO_EM_FALENCIA);
        analiseDeCredito.preencheQtdRefinBancos(QT_REFIN_BANCOS);
        analiseDeCredito.selecionaRecuperacaoExtraJudicialHomologada(RECUPERACAO_EXTRA_JUDICIAL_HOMOLOGADA);
        analiseDeCredito.selecionaRecuperacaoExtraJudicialRequerida(RECUPERACAO_EXTRA_JUDICIAL_REQUERIDA);
        analiseDeCredito.selecionaRecuperacaoJudicialConcedida(RECUPERACAO_JUDICIAL_CONCEDIDA);
        analiseDeCredito.selecionaRecuperacaoJudicialRequerida(RECUPERACAO_JUDICIAL_REQUERIDA);
        analiseDeCredito.preencheStatusDoCNPJ(STATUS_CNPJ);
        analiseDeCredito.preencheTempoDeRelacionamento(TEMPO_RELACIONAMENTO);
        analiseDeCredito.preencheValoresEmAberto(VALORES_EM_ABERTO);
        analiseDeCredito.preencheVrRefinBancos(VR_REFIN_BANCOS);
        analiseDeCredito.clicaBotaoAvaliar();
    }
}