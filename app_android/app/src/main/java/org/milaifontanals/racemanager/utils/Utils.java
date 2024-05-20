package org.milaifontanals.racemanager.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public String formatarData(String s_data_incorrecte) {
        String s_data = s_data_incorrecte;

        DateTimeFormatter dtf;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

            try {
                LocalDateTime dataAmbHora = LocalDateTime.parse(s_data_incorrecte, dtf);
                DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                s_data = dataAmbHora.format(dtfHora);

                return s_data;
            } catch (DateTimeParseException ex) {
                return s_data_incorrecte;
            }
        }

        return s_data_incorrecte;
    }

    public String formatarData(Date date) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Convierte Date a LocalDateTime
            LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return dtfOutput.format(localDateTime);
        } else {
            // Para versiones de Android anteriores a Oreo
            SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return sdfOutput.format(date);
        }
    }
}
