package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static driver.DriverManagerFactory.driver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DecisaoCredito {

    @FindBy(id = "dadosClienteExterno")
    private WebElement fieldCNPJ;

    @FindBy(id = "gdvDados")
    private WebElement tabelaGDVDados;

    public String formataPontuacao(String PONTUACAO) {
        String PONTUACAO_FORMAT = PONTUACAO + "000000 Pontos";

        return PONTUACAO_FORMAT;
    }

    public int transformaPontuacaoEmInt(String PONTUACAO) {
        int PONTUACAO_INT = (int) Double.parseDouble(PONTUACAO.replace(",", "."));

        return PONTUACAO_INT;
    }

    public void alternaParaAbaDecisaoDeCredito() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.manage().window().maximize();
    }

    public void rolaPaginaParaBaixo() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void validaCNPJ(String CNPJ) {
        assertTrue(fieldCNPJ.getText().contains(CNPJ));
    }

    public void validaRelatorioRiscoZero(String VALOR_SCORE, String VALOR_PAGTOS_PONTUAIS, String VALOR_CONSULTAS_MES_ATUAL, String VALOR_CONSULTAS_MES_ANTERIOR, String PONTUACAO) {

        HashMap<String, String> newHash = getDadosDaTabela();

        assertEquals(VALOR_SCORE, newHash.get("PONTUACAO DO SCORE (06 MESES) 22F"));
        if (newHash.get("% PAGTOS PONTUAIS").equals("#")) {
            System.out.println("pagto == #:" + newHash.get("% PAGTOS PONTUAIS"));
            assertEquals(VALOR_PAGTOS_PONTUAIS, newHash.get("% PAGTOS PONTUAIS"));

        } else {
            System.out.println("pagto != #:" + newHash.get("% PAGTOS PONTUAIS"));
            String VALOR_ORIGIN = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS));
            String VALOR_PLUS = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS) + 1);
            String VALOR_MINUS = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS) - 1);
            assertTrue(newHash.get("% PAGTOS PONTUAIS").contains(VALOR_ORIGIN) || newHash.get("% PAGTOS PONTUAIS").contains(VALOR_MINUS)
                    || newHash.get("% PAGTOS PONTUAIS").contains(VALOR_PLUS));
        }
        assertEquals(VALOR_CONSULTAS_MES_ATUAL, newHash.get("QTDE CONSULTAS MES ATUAL (M)"));
        assertEquals(VALOR_CONSULTAS_MES_ANTERIOR, newHash.get("QTDE CONSULTAS MES (M-1)"));
        Assert.assertEquals(PONTUACAO, newHash.get("** FF - CLASSIFICACAO 1 - RISCO ZERO - RK **"));
        Assert.assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP -CLASSIFICACAO 1 - RISCO ZERO #"));
    }

    public void validaRelatorioRegraDeComportamento(String VALOR_PORTE_EMPRESA, String VALOR_QTD_PROTESTOS, String VALOR_VR_PROTESTOS,
                                                    String VALOR_QTD_PEFIN, String VALOR_VR_PEFIN, String VALOR_CONSULTAS_MES_ATUAL,
                                                    String VALOR_CONSULTAS_MES_ANTERIOR, String PONTUACAO) {

        HashMap<String, String> newHash = getDadosDaTabela();
        assertEquals(VALOR_PORTE_EMPRESA, newHash.get("PORTE DA EMPRESA"));
        assertEquals(VALOR_QTD_PROTESTOS, newHash.get("QT PROTESTOS ATE A DATA"));
        assertEquals(VALOR_VR_PROTESTOS, newHash.get("VR PROTESTOS ATE A DATA"));
        assertEquals(VALOR_QTD_PEFIN, newHash.get("QT PEFIN-EMPRESAS ATE A DATA"));
        assertEquals(VALOR_VR_PEFIN, newHash.get("VR PEFIN-EMPRESAS ATE A DATA"));
        assertEquals(VALOR_CONSULTAS_MES_ATUAL, newHash.get("QTDE CONSULTAS MES ATUAL (M)"));
        assertEquals(VALOR_CONSULTAS_MES_ANTERIOR, newHash.get("QTDE CONSULTAS MES (M-1)"));

        if (transformaPontuacaoEmInt(PONTUACAO) >= 18) {
            if (VALOR_PORTE_EMPRESA.equals("SMALL") || VALOR_PORTE_EMPRESA.equals("SMALL+")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO PEQUENO PORTE #"));
                assertEquals("BAIXO", newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("MIDDLE") || VALOR_PORTE_EMPRESA.equals("MIDDLE+")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO MÉDIO PORTE #"));
                assertEquals("BAIXO RISCO", newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("CORPORATE")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO GRANDE PORTE #"));
                assertEquals("BAIXO RISCO", newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("#")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO SEM PORTE #"));
                assertEquals("BAIXO RISCO", newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **"));
            }
        } else if (transformaPontuacaoEmInt(PONTUACAO) < 18 && transformaPontuacaoEmInt(PONTUACAO) >= 10) {
            if (VALOR_PORTE_EMPRESA.equals("SMALL") || VALOR_PORTE_EMPRESA.equals("SMALL+")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO PEQUENO PORTE #"));
                assertEquals("MÉDIO", newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("MIDDLE") || VALOR_PORTE_EMPRESA.equals("MIDDLE+")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO MÉDIO PORTE #"));
                assertEquals("MÉDIO RISCO", newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("CORPORATE")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO GRANDE PORTE #"));
                assertEquals("MÉDIO RISCO", newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("#")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO SEM PORTE #"));
                assertEquals("MÉDIO RISCO", newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **"));
            }
        } else {
            if (VALOR_PORTE_EMPRESA.equals("SMALL") || VALOR_PORTE_EMPRESA.equals("SMALL+")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO PEQUENO PORTE #"));
                assertEquals("ALTO", newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("MIDDLE") || VALOR_PORTE_EMPRESA.equals("MIDDLE+")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO MÉDIO PORTE #"));
                assertEquals("ALTO RISCO", newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("CORPORATE")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO GRANDE PORTE #"));
                assertEquals("ALTO RISCO", newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **"));
            }
            if (VALOR_PORTE_EMPRESA.equals("#")) {
                assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO SEM PORTE #"));
                assertEquals("ALTO RISCO", newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **"));
            }
        }
    }

    public void validaRelatorioPontosScore(String VALOR_PORTE_EMPRESA, String VALOR_SCORE, String PONTUACAO, String RISCO) {

        HashMap<String, String> newHash = getDadosDaTabela();
        assertEquals(VALOR_PORTE_EMPRESA, newHash.get("PORTE DA EMPRESA"));
        assertEquals(VALOR_SCORE, newHash.get("PONTUACAO DO SCORE (06 MESES) 22F"));
        assertEquals(RISCO, newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **"));
    }

    public void validaRelatorioClientesCorporatePlus(String VALOR_PORTE_EMPRESA, String VALOR_PAGTOS_PONTUAIS, String VALOR_QTD_PROTESTOS,
                                                     String VALOR_QTD_PEFIN, String VALOR_CAPITAL_SOCIAL, String PONTUACAO) {

        String VALOR_ORIGIN = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS));
        String VALOR_PLUS = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS) + 1);
        String VALOR_MINUS = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS) - 1);

        HashMap<String, String> newHash = getDadosDaTabela();
        assertEquals(VALOR_PORTE_EMPRESA, newHash.get("PORTE DA EMPRESA"));
        assertTrue(newHash.get("% PAGTOS PONTUAIS").contains(VALOR_ORIGIN) || newHash.get("% PAGTOS PONTUAIS").contains(VALOR_MINUS)
                || newHash.get("% PAGTOS PONTUAIS").contains(VALOR_PLUS));
        assertEquals(VALOR_QTD_PROTESTOS, newHash.get("QT PROTESTOS ATE A DATA"));
        assertEquals(VALOR_QTD_PEFIN, newHash.get("QT PEFIN-EMPRESAS ATE A DATA"));
        assertEquals(VALOR_CAPITAL_SOCIAL, newHash.get("CAPITAL SOCIAL"));
        assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO CORPORATE PLUS #"));

        if (transformaPontuacaoEmInt(PONTUACAO) >= 12) {
            assertEquals("BAIXO", newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **"));
        } else if (transformaPontuacaoEmInt(PONTUACAO) < 12 && transformaPontuacaoEmInt(PONTUACAO) >= 9) {
            assertEquals("MEDIO", newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **"));
        } else {
            assertEquals("ALTO", newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **"));
        }

    }

    public void validaRelatorioRiscoFInal(String VALOR_PORTE_EMPRESA, String VALOR_QTD_PROTESTOS, String VALOR_VR_PROTESTOS,
                                          String VALOR_QTD_PEFIN, String VALOR_VR_PEFIN, String VALOR_CONSULTAS_MES_ATUAL,
                                          String VALOR_CONSULTAS_MES_ANTERIOR, String PONTUACAO, String VALOR_SCORE) {
        HashMap<String, String> newHash = getDadosDaTabela();
        assertEquals(VALOR_SCORE, newHash.get("PONTUACAO DO SCORE (06 MESES) 22F"));
        assertEquals(VALOR_PORTE_EMPRESA, newHash.get("PORTE DA EMPRESA"));
        assertEquals(VALOR_QTD_PROTESTOS, newHash.get("QT PROTESTOS ATE A DATA"));
        assertEquals(VALOR_VR_PROTESTOS, newHash.get("VR PROTESTOS ATE A DATA"));
        assertEquals(VALOR_QTD_PEFIN, newHash.get("QT PEFIN-EMPRESAS ATE A DATA"));
        assertEquals(VALOR_VR_PEFIN, newHash.get("VR PEFIN-EMPRESAS ATE A DATA"));
        assertEquals(VALOR_CONSULTAS_MES_ATUAL, newHash.get("QTDE CONSULTAS MES ATUAL (M)"));
        assertEquals(VALOR_CONSULTAS_MES_ANTERIOR, newHash.get("QTDE CONSULTAS MES (M-1)"));

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("BAIXO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("BAIXO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("BAIXO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("BAIXO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("BAIXO")) {
            assertEquals("BAIXISSIMO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("BAIXO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("BAIXO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("BAIXO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("BAIXO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("MEDIO")) {
            assertEquals("BAIXO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("BAIXO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("BAIXO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("BAIXO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("BAIXO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("ALTO")) {
            assertEquals("MEDIO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("MEDIO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("MEDIO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("MEDIO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("MEDIO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("BAIXO")) {
            assertEquals("BAIXO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("MEDIO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("MEDIO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("MEDIO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("MEDIO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("MEDIO")) {
            assertEquals("MEDIO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("MEDIO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("MEDIO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("MEDIO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("MEDIO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("ALTO")) {
            assertEquals("ALTO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("ALTO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("ALTO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("ALTO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("ALTO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("BAIXO")) {
            assertEquals("MEDIO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("ALTO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("ALTO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("ALTO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("ALTO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("MEDIO")) {
            assertEquals("MEDIO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if ((newHash.get("** FF - COMPORTAMENTO PEQUENO PORTE - RK **").equals("ALTO") || newHash.get("** FF - COMPORTAMENTO MEDIO PORTE - RK **").equals("ALTO")
                || newHash.get("** FF - COMPORTAMENTO GRANDE PORTE - RK **").equals("ALTO") || newHash.get("** FF - COMPORTAMENTO SEM PORTE - RK **").equals("ALTO"))
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("ALTO")) {
            assertEquals("MEDIO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }
    }

    public void validaRelatorioRiscoFinalClientesCorporatePlus(String VALOR_PORTE_EMPRESA, String VALOR_PAGTOS_PONTUAIS, String VALOR_QTD_PROTESTOS,
                                                               String VALOR_QTD_PEFIN, String VALOR_CAPITAL_SOCIAL, String PONTUACAO, String VALOR_SCORE) {

        String VALOR_ORIGIN = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS));
        String VALOR_PLUS = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS) + 1);
        String VALOR_MINUS = String.valueOf((int) Double.parseDouble(VALOR_PAGTOS_PONTUAIS) - 1);

        HashMap<String, String> newHash = getDadosDaTabela();
        assertEquals(VALOR_SCORE, newHash.get("PONTUACAO DO SCORE (06 MESES) 22F"));
        assertEquals(VALOR_PORTE_EMPRESA, newHash.get("PORTE DA EMPRESA"));
        assertTrue(newHash.get("% PAGTOS PONTUAIS").contains(VALOR_ORIGIN) || newHash.get("% PAGTOS PONTUAIS").contains(VALOR_MINUS)
                || newHash.get("% PAGTOS PONTUAIS").contains(VALOR_PLUS));
        assertEquals(VALOR_QTD_PROTESTOS, newHash.get("QT PROTESTOS ATE A DATA"));
        assertEquals(VALOR_QTD_PEFIN, newHash.get("QT PEFIN-EMPRESAS ATE A DATA"));
        assertEquals(VALOR_CAPITAL_SOCIAL, newHash.get("CAPITAL SOCIAL"));
        assertEquals(formataPontuacao(PONTUACAO), newHash.get("# RP - PONTOS DE CORTE COMPORTAMENTO CORPORATE PLUS #"));

        if (transformaPontuacaoEmInt(PONTUACAO) >= 12) {
            assertEquals("BAIXO", newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **"));
        } else if (transformaPontuacaoEmInt(PONTUACAO) < 12 && transformaPontuacaoEmInt(PONTUACAO) >= 9) {
            assertEquals("MEDIO", newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **"));
        } else {
            assertEquals("ALTO", newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **"));
        }

        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("BAIXO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("BAIXO")) {
            assertEquals("BAIXISSIMO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }
        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("BAIXO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("MEDIO")) {
            assertEquals("BAIXO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }
        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("BAIXO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("ALTO")) {
            assertEquals("MEDIO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }
        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("MEDIO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("BAIXO")) {
            assertEquals("BAIXO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }
        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("MEDIO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("MEDIO")) {
            assertEquals("MEDIO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }
        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("MEDIO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("ALTO")) {
            assertEquals("ALTO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("ALTO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("BAIXO")) {
            assertEquals("MEDIO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("ALTO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("MEDIO")) {
            assertEquals("ALTO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }

        if (newHash.get("** FF - COMPORTAMENTO CORPORATE PLUS - RK **").equals("ALTO")
                && newHash.get("** FF - PONTOS DE CORTE - SCORE 6.0 - RK **").equals("ALTO")) {
            assertEquals("ALTISSIMO RISCO", newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **"));
        }
    }

    private HashMap<String, String> getDadosDaTabela() {
        List<WebElement> elements = tabelaGDVDados.findElements(By.xpath("//tr/td[2]"));
        List<WebElement> elementsName = tabelaGDVDados.findElements(By.xpath("//tr/td[1]"));
        List<String> dados = new ArrayList<>();
        List<String> campo = new ArrayList<>();

        HashMap<String, String> hash = new HashMap<String, String>();

        for (int i = 1; i < elementsName.size(); i++) {
            //System.out.println(">> elementsName.get(i).getText(): " + elementsName.get(i).getText());
            campo.add(elementsName.get(i).getText());
        }

        for (int i = 1; i < elements.size(); i++) {
            //System.out.println(">> elements.get(i).getText(): " + elements.get(i).getText());
            dados.add(elements.get(i).getText());
        }

        //System.out.println(">> dados.size(): " + dados.size());
        for (int i = 0; i < dados.size(); i++) {
            hash.put(campo.get(i), dados.get(i));
        }

        return hash;
    }

    public void validaRelatorioDadosOnline(String valor_nf, String auto_falencia, String concordata, String concordata_deferida, String concordata_requerida, String concordata_suspensiva,
                                           String data_entrada_socio_mais_recente, String data_de_inicio_da_atividade, String data_do_sistema, String falencia_decretada, String falencia_requerida,
                                           String historico_atrasos, String modalidade_de_pagamento, String participacao_em_falencia, String qt_refin_bancos, String recuperacao_extra_judicial_homologada,
                                           String recuperacao_extra_judicial_requerida, String recuperacao_judicial_concedida, String recuperacao_judicial_requerida, String status_cnpj,
                                           String tempo_relacionamento, String valores_em_aberto, String vr_refin_bancos) {

        HashMap<String, String> newHash = getDadosDaTabela();
        assertEquals(valor_nf, newHash.get("*DO_VALOR_DA_NF"));
        assertEquals(auto_falencia, newHash.get("*DO_AUTO_FALENCIA"));
        assertEquals(concordata, newHash.get("*DO_CONCORDATA"));
        assertEquals(concordata_deferida, newHash.get("*DO_CONCORDATA_DEFERIDA"));
        assertEquals(concordata_requerida, newHash.get("*DO_CONCORDATA_REQUERIDA"));
        assertEquals(concordata_suspensiva, newHash.get("*DO_CONCORDATA_SUSPENSIVA"));
        assertEquals(data_entrada_socio_mais_recente, newHash.get("*DO_DATA_DA_ENTRADA_DO_SOCIO_MAIS_RECENTE"));
        assertEquals(data_de_inicio_da_atividade, newHash.get("*DO_DATA_DE_INICIO_DA_ATIVIDADE"));
        assertEquals(data_do_sistema, newHash.get("*DO_DATA_DO_SISTEMA"));
        assertEquals(falencia_decretada, newHash.get("*DO_FALENCIA_DECRETADA"));
        assertEquals(falencia_requerida, newHash.get("*DO_FALENCIA_REQUERIDA"));
        assertEquals(historico_atrasos, newHash.get("*DO_HISTORICO_DE_ATRASOS"));
        assertEquals(modalidade_de_pagamento, newHash.get("*DO_MODALIDADE_DE_PAGAMENTO"));
        assertEquals(participacao_em_falencia, newHash.get("*DO_PARTICIPACAO_EM_FALENCIA_PIE"));
        assertEquals(qt_refin_bancos, newHash.get("*DO_QT_REFIN_BANCOS_ATE_A_DATA"));
        assertEquals(recuperacao_extra_judicial_homologada, newHash.get("*DO_RECUPERACAO_EXTRA_JUDICIAL_HOMOLOGADA"));
        assertEquals(recuperacao_extra_judicial_requerida, newHash.get("*DO_RECUPERACAO_EXTRA_JUDICIAL_REQUERIDA"));
        assertEquals(recuperacao_judicial_concedida, newHash.get("*DO_RECUPERACAO_JUDICIAL_CONCEDIDA"));
        assertEquals(recuperacao_judicial_requerida, newHash.get("*DO_RECUPERACAO_JUDICIAL_REQUERIDA"));
        assertEquals(status_cnpj, newHash.get("*DO_STATUS_DO_CNPJ"));
        assertEquals(tempo_relacionamento, newHash.get("*DO_TEMPO_DE_RELACIONAMENTO"));
        assertEquals(valores_em_aberto, newHash.get("*DO_VALORES_EM_ABERTO"));
        assertEquals(vr_refin_bancos, newHash.get("*DO_VR_REFIN_BANCOS_ATE_A_DATA"));
    }


    public void validaRelatorioLimiteDisponivel(String VALOR_PORTE_EMPRESA, String VALOR_QTD_PROTESTOS, String VALOR_VR_PROTESTOS,
                                                String VALOR_QTD_PEFIN, String VALOR_VR_PEFIN, String VALOR_CONSULTAS_MES_ATUAL,
                                                String VALOR_CONSULTAS_MES_ANTERIOR, String PONTUACAO, String VALOR_SCORE,
                                                String VALOR_FATURAMENTO_PRESUMIDO, String VALOR_CAPITAL_SOCIAL) {
        HashMap<String, String> newHash = getDadosDaTabela();
        assertEquals(VALOR_CAPITAL_SOCIAL, newHash.get("CAPITAL SOCIAL"));
        assertEquals(VALOR_FATURAMENTO_PRESUMIDO, newHash.get("FATURAMENTO PRESUMIDO 1.0"));
        assertEquals(VALOR_SCORE, newHash.get("PONTUACAO DO SCORE (06 MESES) 22F"));
        assertEquals(VALOR_PORTE_EMPRESA, newHash.get("PORTE DA EMPRESA"));
        assertEquals(VALOR_QTD_PROTESTOS, newHash.get("QT PROTESTOS ATE A DATA"));
        assertEquals(VALOR_VR_PROTESTOS, newHash.get("VR PROTESTOS ATE A DATA"));
        assertEquals(VALOR_QTD_PEFIN, newHash.get("QT PEFIN-EMPRESAS ATE A DATA"));
        assertEquals(VALOR_VR_PEFIN, newHash.get("VR PEFIN-EMPRESAS ATE A DATA"));
        assertEquals(VALOR_CONSULTAS_MES_ATUAL, newHash.get("QTDE CONSULTAS MES ATUAL (M)"));
        assertEquals(VALOR_CONSULTAS_MES_ANTERIOR, newHash.get("QTDE CONSULTAS MES (M-1)"));

        DecimalFormat df = new DecimalFormat("###,###,###,###,###.00");

        if (newHash.get("PORTE DA EMPRESA").equals("SMALL")) {
            if (!newHash.get("FATURAMENTO PRESUMIDO 1.0").equals("#")) {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("FATURAMENTO PRESUMIDO 1.0").replace(",", ".")) / 13 * 12 * 0.2);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 1.0);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.8);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.5);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO"));
                }
            } else {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("CAPITAL SOCIAL").replace(",", ".")) / 13 * 12 * 0.2);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.4);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.3);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.15);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO - CAPITAL SOCIAL"));
                }
            }
        }

        if (newHash.get("PORTE DA EMPRESA").equals("SMALL+")) {
            if (!newHash.get("FATURAMENTO PRESUMIDO 1.0").equals("#")) {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("FATURAMENTO PRESUMIDO 1.0").replace(",", ".")) / 13 * 12 * 0.1);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 1.0);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.8);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.5);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO"));
                }
            } else {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("CAPITAL SOCIAL").replace(",", ".")) / 13 * 12 * 0.1);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.4);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.3);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (LIMITE_ANUAL * 0.15);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO - CAPITAL SOCIAL"));
                }
            }
        }

        if (newHash.get("PORTE DA EMPRESA").equals("MIDDLE")) {
            if (!newHash.get("FATURAMENTO PRESUMIDO 1.0").equals("#")) {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("FATURAMENTO PRESUMIDO 1.0").replace(",", ".")) / 13 * 12 * 0.05);
                int OVERLIMIT = 1500000;
                int MENOR_VALOR = Math.min(LIMITE_ANUAL, OVERLIMIT);
                System.out.println(LIMITE_ANUAL);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 1.0);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.8);
                    System.out.println(LIMITE);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    System.out.println(LIMITE_DISPONIVEL);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.5);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO"));
                }
            } else {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("CAPITAL SOCIAL").replace(",", ".")) / 13 * 12 * 0.05);
                int OVERLIMIT = 1500000;
                int MENOR_VALOR = Math.min(LIMITE_ANUAL, OVERLIMIT);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.4);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.3);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.15);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO - CAPITAL SOCIAL"));
                }
            }
        }

        if (newHash.get("PORTE DA EMPRESA").equals("MIDDLE+")) {
            if (!newHash.get("FATURAMENTO PRESUMIDO 1.0").equals("#")) {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("FATURAMENTO PRESUMIDO 1.0").replace(",", ".")) / 13 * 12 * 0.05);
                int OVERLIMIT = 3000000;
                int MENOR_VALOR = Math.min(LIMITE_ANUAL, OVERLIMIT);
                System.out.println(LIMITE_ANUAL);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 1.0);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.8);
                    System.out.println(LIMITE);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    System.out.println(LIMITE_DISPONIVEL);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.5);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO"));
                }
            } else {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("CAPITAL SOCIAL").replace(",", ".")) / 13 * 12 * 0.05);
                int OVERLIMIT = 1500000;
                int MENOR_VALOR = Math.min(LIMITE_ANUAL, OVERLIMIT);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.4);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.3);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.15);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO - CAPITAL SOCIAL"));
                }
            }
        }

        if (newHash.get("PORTE DA EMPRESA").equals("CORPORATE")) {
            if (!newHash.get("FATURAMENTO PRESUMIDO 1.0").equals("#")) {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("FATURAMENTO PRESUMIDO 1.0").replace(",", ".")) / 13 * 12 * 0.02);
                int OVERLIMIT = 4000000;
                int MENOR_VALOR = Math.min(LIMITE_ANUAL, OVERLIMIT);
                System.out.println(LIMITE_ANUAL);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 1.0);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.8);
                    System.out.println(LIMITE);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    System.out.println(LIMITE_DISPONIVEL);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.5);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO"));
                }
            } else {
                int LIMITE_ANUAL = (int) (Double.parseDouble(newHash.get("CAPITAL SOCIAL").replace(",", ".")) / 13 * 12 * 0.02);
                int OVERLIMIT = 1500000;
                int MENOR_VALOR = Math.min(LIMITE_ANUAL, OVERLIMIT);
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXISSIMO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.4);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - RISCO ZERO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("BAIXO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.3);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - BAIXO RISCO - CAPITAL SOCIAL"));
                }
                if (newHash.get("** FF - CLASSIFICACAO FINAL DE RISCO - RK **").equals("MEDIO RISCO")) {
                    int LIMITE = (int) (MENOR_VALOR * 0.15);
                    String LIMITE_DISPONIVEL = df.format(LIMITE);
                    assertEquals(LIMITE_DISPONIVEL, newHash.get("RL - MEDIO RISCO - CAPITAL SOCIAL"));
                }
            }
        }
    }
}
