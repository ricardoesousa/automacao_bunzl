package sql;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLConnector {

    public String formataCNPJ(String CNPJ) {
        String CNPJ_BD = "0" + CNPJ.replace(".", "").replace("/", "").replace("-", "");

        return CNPJ_BD;
    }

    public String formataValor(String VALOR) {
        String VALOR_BD = VALOR.replace(".", "");

        return VALOR_BD;
    }

    public String formataData(String DATA) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse(DATA);
        formato.applyPattern("yyyyMMdd");
        String dataFormatada = formato.format(data);
        System.out.println("Data: " + dataFormatada);
        return dataFormatada;
    }

    public void resetaCNPJ(String CNPJ, String DATA_ALT)
            throws ClassNotFoundException, SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";

        //Database Username
        String username = "GESTOR43854777000126";

        //Database Password
        String password = "43854777000126";

        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('% PAGTOS PONTUAIS') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('AUTO-FALENCIA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('CAPITAL SOCIAL') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('CONCORDATA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('CONCORDATA DEFERIDA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('CONCORDATA REQUERIDA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('CONCORDATA SUSPENSIVA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('DATA DA ENTRADA DO SOCIO MAIS RECENTE') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('DATA DE INICIO DA ATIVIDADE') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('DATA DO SISTEMA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('FALENCIA DECRETADA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('FALENCIA REQUERIDA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('FATURAMENTO PRESUMIDO 1.0') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('PARTICIPACAO EM FALENCIA (PIE)') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('PONTUACAO DO SCORE (06 MESES) 22F') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('PORTE DA EMPRESA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('QT PEFIN-EMPRESAS ATE A DATA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('QT PROTESTOS ATE A DATA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('QT REFIN-BANCOS ATE A DATA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\t\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('QTDE CONSULTAS MES (M-1)') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('QTDE CONSULTAS MES ATUAL (M)') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('RAZAO SOCIAL') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('RECUPERACAO EXTRA-JUDICIAL HOMOLOGADA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('RECUPERACAO EXTRA-JUDICIAL REQUERIDA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('RECUPERACAO JUDICIAL CONCEDIDA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('RECUPERACAO JUDICIAL REQUERIDA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('STATUS DO CNPJ') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('VR PEFIN-EMPRESAS ATE A DATA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('VR PROTESTOS ATE A DATA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='#'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\n" +
                        "WHERE P.PO_PARA_Desc IN ('VR REFIN-BANCOS ATE A DATA') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)";

        //Load mysql jdbc driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Create Connection to DB
        Connection con = DriverManager.getConnection(dbUrl, username, password);

        //Create Statement Object
        Statement stmt = con.createStatement();

        // Execute the SQL Query. Store results in ResultSet
        stmt.executeUpdate(query);

//        // While Loop to iterate through all data and print results
//        while (rs.next()){
//            String coluna1 = rs.getString(1);
//            String coluna2 = rs.getString(2);
//            String coluna3 = rs.getString(3);
//            String coluna4 = rs.getString(4);
//            System. out.println(coluna1+"  "+coluna2+"  "+coluna3+"  "+coluna4);
//        }
        // closing DB Connection
        con.close();
    }

    public void alteraUmCampo(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1)
            throws ClassNotFoundException, SQLException {
        //Sintaxe da URL de conexão: "jdbc:mysql://ipaddress(or server):portnumber/db_name"
        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";

        //Nome do banco de dados
        String username = "GESTOR43854777000126";

        //Senha do banco de dados
        String password = "43854777000126";

        //Código de consulta SQL
        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR1 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO1 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "SELECT pp.PO_PARA_Desc,pcp.CO_PESSOA_PCP, pcp.DT_PARAM_PCP, pcp.VL_PARAM_PCP FROM PO_CACHE_PARAMETRO pcp\n" +
                        "INNER JOIN PO_Parametro pp\n" +
                        "ON pcp.PO_PARA_COD = pp.PO_PARA_Cod\n" +
                        "WHERE pcp.CO_PESSOA_PCP =@cnpj AND pcp.DT_PARAM_PCP =@data";

        //Carregar o driver jdbc do Sqlserver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Criar conexão com o banco de dados
        Connection con = DriverManager.getConnection(dbUrl, username, password);

        //Create Statement Object Criar objeto
        Statement stmt = con.createStatement();

        //Executar a consulta SQL. Neste caso atualização
        stmt.executeUpdate(query);

        //Fechar a conexão com o banco de dados
        con.close();
    }

    public void alteraCincoCampos(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                  String CAMPO2, String VALOR2, String CAMPO3, String VALOR3,
                                  String CAMPO4, String VALOR4, String CAMPO5, String VALOR5)
            throws ClassNotFoundException, SQLException {
        //Sintaxe da URL de conexão: "jdbc:mysql://ipaddress(or server):portnumber/db_name"
        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";

        //Nome do banco de dados
        String username = "GESTOR43854777000126";

        //Senha do banco de dados
        String password = "43854777000126";

        //Código de consulta SQL
        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR1 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO1 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR2 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO2 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR3 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO3 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR4 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO4 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR5 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO5 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n";

        //Carregar o driver jdbc do Sqlserver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Criar conexão com o banco de dados
        Connection con = DriverManager.getConnection(dbUrl, username, password);

        //Create Statement Object Criar objeto
        Statement stmt = con.createStatement();

        //Executar a atualização dos campos solicitados
        stmt.executeUpdate(query);

        //Fechar a conexão com o banco de dados
        con.close();
    }

    public void alteraDoisCampos(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                 String CAMPO2, String VALOR2)
            throws ClassNotFoundException, SQLException {

        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";
        String username = "GESTOR43854777000126";
        String password = "43854777000126";

        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR1 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO1 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR2 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO2 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
        con.close();

    }

    public void alteraQuatroCampos(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1, String CAMPO3, String VALOR3,
                                   String CAMPO4, String VALOR4, String CAMPO5, String VALOR5)
            throws ClassNotFoundException, SQLException {

        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";
        String username = "GESTOR43854777000126";
        String password = "43854777000126";

        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR1 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO1 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR3 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO3 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR4 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO4 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR5 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO5 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
        con.close();

    }


    public void alteraSeteCampos(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                 String CAMPO2, String VALOR2, String CAMPO3, String VALOR3,
                                 String CAMPO4, String VALOR4, String CAMPO5, String VALOR5,
                                 String CAMPO6, String VALOR6, String CAMPO7, String VALOR7)
            throws ClassNotFoundException, SQLException {

        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";
        String username = "GESTOR43854777000126";
        String password = "43854777000126";

        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR1 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO1 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR2 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO2 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR3 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO3 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR4 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO4 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR5 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO5 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR6 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO6 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR7 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO7 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
        con.close();
    }

    public void alteraSeisCampos(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                 String CAMPO2, String VALOR2, String CAMPO3, String VALOR3,
                                 String CAMPO4, String VALOR4, String CAMPO5, String VALOR5,
                                 String CAMPO6, String VALOR6)
            throws ClassNotFoundException, SQLException {

        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";
        String username = "GESTOR43854777000126";
        String password = "43854777000126";

        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR1 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO1 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR2 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO2 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR3 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO3 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR4 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO4 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR5 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO5 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR6 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO6 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
        con.close();
    }

    public void alteraOitoCampos(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                 String CAMPO2, String VALOR2, String CAMPO3, String VALOR3,
                                 String CAMPO4, String VALOR4, String CAMPO5, String VALOR5,
                                 String CAMPO6, String VALOR6,String CAMPO7, String VALOR7,
                                 String CAMPO8, String VALOR8)
            throws ClassNotFoundException, SQLException {

        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";
        String username = "GESTOR43854777000126";
        String password = "43854777000126";

        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR1 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO1 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR2 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO2 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR3 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO3 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR4 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO4 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR5 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO5 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR6 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO6 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR7 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO7 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR8 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO8 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
        con.close();
    }

    public void alteraDezCampos(String CNPJ, String DATA_ALT, String CAMPO1, String VALOR1,
                                 String CAMPO2, String VALOR2, String CAMPO3, String VALOR3,
                                 String CAMPO4, String VALOR4, String CAMPO5, String VALOR5,
                                 String CAMPO6, String VALOR6,String CAMPO7, String VALOR7,
                                 String CAMPO8, String VALOR8,String CAMPO9, String VALOR9,
                                String CAMPO10, String VALOR10)
            throws ClassNotFoundException, SQLException {

        String dbUrl = "jdbc:sqlserver://dbhomolog:1433;databaseName=GESTOR43854777000126";
        String username = "GESTOR43854777000126";
        String password = "43854777000126";

        String query =
                "DECLARE @cnpj VARCHAR(15) ='" + CNPJ + "'\n" +
                        "DECLARE @data DATETIME = '" + DATA_ALT + "'\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR1 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO1 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR2 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO2 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR3 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO3 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR4 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO4 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR5 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO5 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR6 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO6 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR7 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO7 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR8 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO8 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR9 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO9 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n" +
                        "\n" +
                        "UPDATE PO_CACHE_PARAMETRO \n" +
                        "SET VL_PARAM_PCP ='" + VALOR10 + "'\n" +
                        "FROM PO_CACHE_PARAMETRO C INNER JOIN PO_Parametro P\n" +
                        "ON C.PO_PARA_COD = P.PO_PARA_Cod\t\n" +
                        "WHERE P.PO_PARA_Desc IN ('" + CAMPO10 + "') AND\n" +
                        "C.CO_PESSOA_PCP IN (@cnpj) AND\n" +
                        "C.DT_PARAM_PCP = (@data)\n";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
        con.close();
    }


}