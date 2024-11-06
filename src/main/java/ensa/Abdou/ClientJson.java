package ensa.Abdou;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ClientJson {
	public static String toJson(Client client) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(client);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
	public static Client fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Client.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
