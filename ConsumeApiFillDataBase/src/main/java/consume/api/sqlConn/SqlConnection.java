package consume.api.sqlConn;

import consume.api.util.ReadPropertyFile;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Clase que gestiona conexiones a la base de datos
 * @author BrahianVT
 * */
public class SqlConnection {
    Properties config;
    ReadPropertyFile read = new ReadPropertyFile();
    Connection conn = null;
    // Crea un conexion a la base de datoe
    public void openConnection(){
        Properties auxConfig = getSqlProperties();

        try {
            Class.forName(auxConfig.getProperty("datasource.driver-class-name"));
            System.out.println("Driver Loaded");

            conn = DriverManager.getConnection(auxConfig.getProperty("datasource.url"),
                    auxConfig.getProperty("datasource.username"),auxConfig.getProperty("datasource.password"));

            System.out.println("Connected to MySql DataBase ...");
        } catch (SQLException  | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    // Metodo que cierra una conexion con base de datos
    public void disconnect(){
        System.out.println("Disconnect from database..");
        if(conn != null){
            try{
                conn.close();
                conn = null;
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public boolean checkHero(int idHero){
        String query = "select id_hero from hero  where id_hero = " + idHero;
        ResultSet resultSet = executeStatements(query);
        try {
            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); return false;
        }
        return false;
    }

    public void insertHero(int idHero, String name){
        String query = "INSERT INTO hero(id_hero, name_hero)" +
                "VALUES (" + idHero + ",\"" + name + "\")";
        executeUpdates(query);
    }

    public void insertComic(int idComic, String nameComic, String nameWriter, String nameEditor, String nameColorist, int idHero, String lastSync){
        String query = "INSERT INTO comic(id_comic, name_comic, name_writer, name_editor , name_colorist, id_hero" +
                ", last_sync) VALUES (" + idComic + ",\"" + nameComic + "\",";
        if(nameWriter.length() > 0)
            query+="\"" + nameWriter + "\",";
        else
            query+="" +  "null" + ",";
        if(nameEditor.length() > 0)
            query+="\"" + nameEditor + "\",";
        else
            query+="" +  "null" + ",";
        if(nameColorist.length() > 0)
            query+="\"" + nameColorist + "\",";
        else
            query+="" +  "null" + ",";

        query+= idHero + "," + "'" + lastSync + "')";

        executeUpdates(query);
    }

    public void insertInteraction(int idComic, int idHeroIn){
        String query = "INSERT INTO interaction(id_comic, id_hero_interaction)" +
                "VALUES (" + idComic + "," + idHeroIn + ")";

        System.out.println(query);
        executeUpdates(query);
    }
    // Checar cual fue la ultima fecha de actualizacion
    public LocalDate getLastDateSync(){
            String query = "select last_sync from comic order by last_sync desc limit 1";
            ResultSet resultSet = executeStatements(query);
            LocalDate date;
        try {
            if(resultSet.next()) {
                System.out.println("date found");
                date = LocalDate.parse(resultSet.getDate("last_sync").toString());
                System.out.println("" + date.toString());
                return date;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    private ResultSet executeStatements(String query){
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException ex){ ex.printStackTrace(); return null; }
    }

    private void executeUpdates(String query){
        System.out.println(query);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){ ex.printStackTrace(); }
    }


    // optiene propiedades para crear la conexion
    public Properties getSqlProperties(){
        return (config != null)?config:read.getProperties();
    }

}
