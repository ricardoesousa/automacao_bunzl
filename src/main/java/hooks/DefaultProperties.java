package hooks;

import com.github.javafaker.Faker;

import java.util.Locale;

public interface DefaultProperties {

    Faker faker = new Faker(new Locale("pt-BR"));

    int TIME_OUT = 20;
    String URL_BASE = "https://gw-homologa.serasa.com.br/GestorPJ/";
    String NOME = "HOMOLOG2 | 81632833";
    String LOGON_GESTOR = "HOMOLOG2";
    String SENHA_GESTOR = "serasa";
    String LOGON_SERASA = "81632833";
    String SENHA_SERASA = "Gestor@1";
    String CNPJ = "00.000.001/0001-36";
    String VALOR = "2.500,00";
    String POLITICA = "05 - CLIENTES DO MERCADO DE ENERGIA";
    String CHAVE_DO_ANALISTA = faker.dragonBall().character();
    String RAZAO_SOCIAL = faker.harryPotter().character();
    String AREA_SOLICITANTE = faker.job().field();
    String RENOVACAO = "SIM";
    String IMPLEMENTACAO_AUTOMATICA = "SIM";
    String UTILIZA_RATING = "NAO";
    String DEMONSTRACAO_PERIODO = "1t";
    String GRAU_INVESTIMENTO = "SIM";
    String TEMPO_RELACIONAMENTO = "3";
    String ATRASO_MEDIO = "4";
    String BLACK_LIST  = "NAO";
    String PROBLEMAS_DE_IMAGEM = "2";
    String QUALIDADE_DO_BALANCO = "4";
    String RATING_SERASA = "6";
    String RECEITA_PETROBRAS = "90,00";
    String TIPOS_CASOS_ENERGIA = "COMUM";
    String DIAS_DE_ATRASO = "30";
    String FATURAMENTO_MENSAL = "2.900,00";
    String HISTORICO_DE_COMPRAS = "SIM";
    String LIMITE_CREDITO_MAIS_DE_3_MESES = "SIM";
    String SEGMENTO = "2 RODAS";
    String CRITERIO_CAPACIDADE = "DIVERSIFICACAO";
    String CREDITO_VIGENTE = "SIM";
    String FITCH = "A+";
    String MOODS = "A2";
    String POORS = "A+";
    String QUALIDADE_DEMONSTRACAO_FINANCEIRA = "SEM RESSALVA";
    String RESTRICOES_RELEVANTES = "NAO";
    String POSICAO_SEGMENTO = "2 QUARTIL";
    String DOMICILIO_FISCAL = "EXTERNO";
    String ATIVO_CIRCULANTE = "2.600,00";
    String ATIVO_TOTAL = "2.700,00";
    String EBTIDA = "2.800,00";
    String PASSIVO_CIRCULANTE = "2.900,00";
    String PASSIVO_TERCEIROS = "3.000,00";
    String PATRIMONIO_LIQUIDO = "3.100,00";
    String RESULTADOS_ACUMULADOS = "3.200,00";
    String AREA_CONTROLE_CREDITO = "CCBK";
    String TIPO_CREDITO = "YB";
    String VALOR_PROPOSTA = "3.300,00";
    String VIGENCIA = "23";
    String MSG_RECOMENDACAO_DA_POLITICA = "RECOMENDAÇÃO DA POLÍTICA: CAPACIDADE CREDITICIA CALCULADA COM SUCESSO";
    String MSG_APROVADO = "APROVADO - CRÉDITO CALCULADO CONFORME REGRAS VIGENTES";
    String MSG_RESULTADO_ANALISE = "    RECOMENDAÇÃO DA POLÍTICA: REPROVADO - CNPJ COM VALOR DE RESTRITIVOS ACIMA DO ESTABELECIDO.          ";
}
