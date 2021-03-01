package consume.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import consume.api.sqlConn.SqlConnection;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Clase que obtiene los datos relevantes del servicio consumido
 * @author Brahian VT
 * */
public class ParseApiInformation {
    SqlConnection conn = new SqlConnection();
    String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    ObjectMapper mapper;
    ObjectNode root;
    JsonNode data;

    // obtiene el result y comienza a parsear informacion
    public void readJson(String result) throws IOException {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        root = (ObjectNode) mapper.readTree(result);
        data = root.get("data");

    }

    public boolean hasData(){ return data.get("results").size() > 0;  }

    public void  parseInformation(int idMainHero){
        for (final JsonNode objNode : data.get("results")) {
            System.out.println("---------------------------------------------");
            conn.openConnection();
            int idComic = objNode.get("id").intValue();
            String nameComic = objNode.get("title").textValue();
            String writer ="", editor="", colorist="";
            if(objNode.get("creators").get("items").size() > 0){
                for(final JsonNode objCreator: objNode.get("creators").get("items")){
                    if(objCreator.get("role").textValue().equals("writer")) writer = objCreator.get("name").textValue();
                    if(objCreator.get("role").textValue().equals("editor")) editor = objCreator.get("name").textValue();
                    if(objCreator.get("role").textValue().equals("colorist")) colorist = objCreator.get("name").textValue();

                }
            }
            conn.insertComic(idComic,nameComic, writer, editor, colorist,idMainHero,now);

            if(objNode.get("characters").get("items").size() > 0){
                for(final JsonNode objCreator: objNode.get("characters").get("items")){
                    int heroId  = Integer.parseInt(objCreator.get("resourceURI").textValue().substring(47));
                    String nameHero = objCreator.get("name").textValue();
                    if(idMainHero == heroId)continue;
                    if(!conn.checkHero(heroId))conn.insertHero(heroId, nameHero);
                    if(!conn.checkInteraction(idComic, heroId)) conn.insertInteraction(idComic, heroId);
                }
            }
        }
        conn.disconnect();
    }
}
