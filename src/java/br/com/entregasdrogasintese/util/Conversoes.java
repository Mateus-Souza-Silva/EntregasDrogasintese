package br.com.entregasdrogasintese.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversoes {

    public static Date converterData(String data) {
        if (data != null) {
            try {
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                //System.out.println("ddddddddddd "+data);
                if ((data != null) || (!data.trim().equals(""))) {
                    //System.out.println("aaaaaaaaaaaa "+fmt.parse(data));
                    return fmt.parse(data);

                } else {
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Problemas ao converter data!"
                        + "\n Erro: " + ex.getMessage());
                ex.printStackTrace();
                return null;
            }
        }else{
            System.out.println("Campo nao era para cadastrar");
        }
            return null;
    }

    public static Time converterHora(String hora) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Date data = formatador.parse(hora);
        Time time = new Time(data.getTime());
        return time;
    }

    public static Date converterDataHora(String data) {
        try {
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            if ((data != null) || (!data.trim().equals(""))) {
                return fmt.parse(data);

            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao converter hora!"
                    + "\n Erro: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
