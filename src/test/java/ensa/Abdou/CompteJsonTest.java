package ensa.Abdou;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;


public class CompteJsonTest {

	
	@Test
	public void testToJson() {
        Compte c = new Compte(1,"USD");
        
        String res = CompteJson.toJson(c);
        assertTrue(res.contains("\"numCompte\":1"));
        assertTrue(res.contains("\"devise\":\"USD\""));
	}
	
	@Test
	public void testFromJson() {
        String json = "{\"typeTransaction\":\"Virin\",\"reference\":\"TRX-111\"}";
        Transaction t = TransactionJson.fromJson(json);
        assertEquals(Type.Virin , t.typeTransaction);
        assertEquals("TRX-111" , t.reference);
	}
}
