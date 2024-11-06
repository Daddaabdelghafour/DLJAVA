package ensa.Abdou;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;






public class TransactionJson {
	
	
	public static String toJson(Transaction transaction) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(transaction);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	 public static Transaction fromJson(String json) {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            return mapper.readValue(json, Transaction.class);
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	
	
	
	
}
