package br.com.drinksapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Silvio Cedrim on 31/10/2017.
 */

public class Util {

    static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

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
}
