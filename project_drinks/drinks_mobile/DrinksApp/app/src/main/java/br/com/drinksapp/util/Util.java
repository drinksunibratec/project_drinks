package br.com.drinksapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Silvio Cedrim on 31/10/2017.
 */

public class Util {

    static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    static final SimpleDateFormat americanFormat = new SimpleDateFormat("yyyy/MM/dd");

    public static String[] REPLACES =
            { "a", "e", "i", "o", "u", "c" };

    public static Pattern[] PATTERNS = null;

    public static void compilePatterns() {
        PATTERNS = new Pattern[REPLACES.length];
        PATTERNS[0] = Pattern.compile(
                "[âãáàä]", Pattern.CASE_INSENSITIVE);
        PATTERNS[1] = Pattern.compile(
                "[éèêë]", Pattern.CASE_INSENSITIVE);
        PATTERNS[2] = Pattern.compile(
                "[íìîï]", Pattern.CASE_INSENSITIVE);
        PATTERNS[3] = Pattern.compile(
                "[óòôõö]", Pattern.CASE_INSENSITIVE);
        PATTERNS[4] = Pattern.compile(
                "[úùûü]", Pattern.CASE_INSENSITIVE);
        PATTERNS[5] = Pattern.compile(
                "[ç]", Pattern.CASE_INSENSITIVE);
    }

    public static String removeAcentos(String text) {
        if (PATTERNS == null) {
            compilePatterns();
        }

        String result = text;
        for (int i = 0; i < PATTERNS.length; i++) {
            Matcher matcher = PATTERNS[i].matcher(result);
            result = matcher.replaceAll(REPLACES[i]);
        }
        return result.toLowerCase();
    }

    public static String getDataAtual() {
        Date dataFormatada;
        String dataBanco = "";//variavel que vai receber a data para o banco
        try {

            Date minhaDate = new Date();
            String dataSistema = format.format(minhaDate);

            dataFormatada = format.parse(dataSistema);
            dataBanco = format.format(dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataBanco;
    }

    public static String getDataAtualAmericana() {
        Date dataFormatada;
        String dataBanco = "";//variavel que vai receber a data para o banco
        try {

            Date minhaDate = new Date();
            String dataSistema = americanFormat.format(minhaDate);

            dataFormatada = americanFormat.parse(dataSistema);
            dataBanco = americanFormat.format(dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataBanco;
    }


}
