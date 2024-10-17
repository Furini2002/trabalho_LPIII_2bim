package br.com.FuriniSolutions.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataUtils {

    // Método utilitário estático para formatar uma data do tipo java.util.Date
    public static String formatarData(Date data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converter Date para LocalDate
        LocalDate localDate = data.toInstant()
                                  .atZone(ZoneId.systemDefault())
                                  .toLocalDate();

        // Formatar a data como string
        return localDate.format(formatter);
    }
}


