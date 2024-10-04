package br.com.FuriniSolutions.util;

import java.util.Calendar;
import java.util.Date;

public class DataUtil {

    public static Date dataAtual() {
        // Obtém a data atual
        Date dataAtual = Calendar.getInstance().getTime();
        // Converte java.util.Date para java.sql.Date, pois se não da erro
        return new java.sql.Date(dataAtual.getTime());
    }

}
