package ensa.Abdou;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class CompteJson {

	   public static String toJson(Compte compte) {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            return mapper.writeValueAsString(compte);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	   
	   public static Compte fromJson(String json) {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            return mapper.readValue(json, Compte.class);
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	   
}
