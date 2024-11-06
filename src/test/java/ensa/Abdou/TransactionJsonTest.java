package ensa.Abdou;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;




public class TransactionJsonTest {

	
	
	@Test
	public void testToJson() {
	
		Date d = new Date();
        Transaction t = new Transaction(Type.Virin,d,"TRX-111");
        
        String res = TransactionJson.toJson(t);
        System.out.println(res);
        assertTrue(res.contains("\"typeTransaction\":\"Virin\""));
        assertTrue(res.contains("\"reference\":\"TRX-111\""));
	}
}
