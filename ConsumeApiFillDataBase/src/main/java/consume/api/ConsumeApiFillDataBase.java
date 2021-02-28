package consume.api;

import consume.api.sqlConn.SqlConnection;
import consume.api.util.ReadPropertyFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase de entrada desde aqui se comsume el servicio de la pagina de marvel
 * y se configura para cada x tiempo consultar de nuevo el servicio
 * @author Brahian VT
 * */

public class ConsumeApiFillDataBase {
    private static String URL_BASE_API;
    private static Integer LIMIT = 50;
    private static Integer OFFSET = 0;
    ParseApiInformation parse = new ParseApiInformation();
    ConnectConsumeApi consumeApi = new ConnectConsumeApi();
    SqlConnection sql = new SqlConnection();
    ReadPropertyFile file = new ReadPropertyFile();
    Properties config = null;
    String idManHero;
    public void consumeApi() throws IOException {
        config = file.getProperties();
        URL_BASE_API = config.getProperty("urlBase");
        idManHero = config.getProperty("idIronMan");

        String url = URL_BASE_API + idManHero + "/";
        sql.openConnection();
        LocalDate from = sql.getLastDateSync();

        if(from == null){
            url+="comics?format=comic&formatType=comic&orderBy=onsaleDate&offset="+ OFFSET+"&limit=" + LIMIT +
                    "&ts=" + config.getProperty("ts") + "&apikey="+config.getProperty("apikey") + "&hash="+config.getProperty("hash");
        } else{
            String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String prev = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String rangeDate = prev + "%2C" + now;
            url+="comics?format=comic&dateRange="+rangeDate+ "&formatType=comic&orderBy=onsaleDate&offset="+ OFFSET+"&limit=" + LIMIT +
                    "&ts=" + config.getProperty("ts") + "&apikey="+config.getProperty("apikey") + "&hash="+config.getProperty("hash");
        }

        consumeApi(url);
        idManHero = config.getProperty("idCap");
        url = url.replaceFirst("1009368", idManHero);
        consumeApi(url);
    }

    public void consumeApi(String url) throws  IOException {
        System.out.println(url);
        while(true) {
            BufferedReader br = consumeApi.getBufferedReader(url);
            String outputApi = br.readLine();
            parse.readJson(outputApi);
            if(!parse.hasData())break;
            parse.parseInformation(Integer.parseInt(idManHero));
            url = url.replaceFirst("offset=" + OFFSET ,"offset=" + (OFFSET + LIMIT));
            OFFSET += LIMIT;
        }
    }

    // por default la aplicacion consultara el servicio cada 86400s,  un dia
    // se puede indicar otro intervalo que puede ser definido como variable de entrada
    public static void main(String[] args) throws IOException {
        int minuteAtSeconds = 86400;
        if(args.length == 1) minuteAtSeconds = Integer.parseInt(args[0]);

        System.out.println("Every " + minuteAtSeconds + " seconds");
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ConsumeApiFillDataBase consumeApiFillDataBase = new ConsumeApiFillDataBase();
                try {
                    consumeApiFillDataBase.consumeApi();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1 * minuteAtSeconds * 1000);
    /**/

    }

    private File parse(){
        File jsonFile = new File("test.json").getAbsoluteFile();
        if(jsonFile == null) System.out.println("error esta vacio");
        return jsonFile;

    }

}
