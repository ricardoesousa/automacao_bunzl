import java.text.DecimalFormat;
import java.text.ParseException;

public class Testes {
    public static void main(String argumentos[]) throws ParseException {

        String faturamento = "550000.00";
        DecimalFormat df = new DecimalFormat("###,###,###,###,###.00");

        int LIMITE_ANUAL = (int) (Double.parseDouble(faturamento) / 13 * 12 * 0.2);
        System.out.println(LIMITE_ANUAL);
        String LIMITE_DISPONIVEL = df.format(LIMITE_ANUAL);
        System.out.println(LIMITE_DISPONIVEL);

    }
}
