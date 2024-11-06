package ensa.Abdou;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;






public class BanqueJson {
	
	public static String toJson(Banque banque) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(banque);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


	 
	 public static Banque fromJson(String json) {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            return mapper.readValue(json, Banque.class);
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	 
	 public static void main(String args[]) {
		 
		 
		 
	 }
	 
	 
	 
	 
	 
	 
}
