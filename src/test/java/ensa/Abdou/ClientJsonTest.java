package ensa.Abdou;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;



public class ClientJsonTest {
	@Test
	public void testToJson() {
        Client client = new Client(1,"john","doe","123 rue","0123456789","johndoe@example.com");
        ClientJson j = new ClientJson();
        String res = ClientJson.toJson(client);
        assertTrue(res.contains("\"id\":1"));
        assertTrue(res.contains("\"nom\":\"john\""));
        assertTrue(res.contains("\"prenom\":\"doe\""));
        assertTrue(res.contains("\"adresse\":\"123 rue\""));
        assertTrue(res.contains("\"telephone\":\"0123456789\""));
        assertTrue(res.contains("\"email\":\"johndoe@example.com\""));
	}
	
	
	@Test
	public void testFromJson() {
        String json = "{\"id\":1,\"nom\":\"John\",\"prenom\":\"Doe\",\"adresse\":\"123 Rue Exemple\",\"telephone\":\"0123456789\",\"email\":\"johndoe@example.com\"}";
        Client c = ClientJson.fromJson(json);
        assertEquals(1, c.id);
        assertEquals("John", c.nom);
        assertEquals("Doe", c.prenom);
        assertEquals("123 Rue Exemple", c.adresse);
        assertEquals("0123456789", c.telephone);
        assertEquals("johndoe@example.com",c.email);
	}
	
	
	
	
	
	
	
}
