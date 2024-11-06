package ensa.Abdou;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



public class BanqueJsonTest {

	@Test
	public void testToJson() {
        Banque b = new Banque(1,"Maroc","123 casablanca ","06889781","johndoe@example.com");
        String res = BanqueJson.toJson(b);
        System.out.println(res);
        assertTrue(res.contains("\"id\":1"));
        assertTrue(res.contains("\"pays\":\"Maroc\""));
        assertTrue(res.contains("\"adresse\":\"123 casablanca \""));
        assertTrue(res.contains("\"telephone\":\"06889781\""));
        assertTrue(res.contains("\"email\":\"johndoe@example.com\""));
	}
	
	@Test
	public void testFromJson() {
        String json = "{\"id\":1,\"pays\":\"Maroc\",\"adresse\":\"123 casablanca\",\"telephone\":\"06889781\",\"email\":\"johndoe@example.com\"}";
        Banque b = BanqueJson.fromJson(json);
        assertEquals(1, b.id);
        assertEquals("Maroc", b.pays);
        assertEquals("123 casablanca", b.adresse);
        assertEquals("06889781", b.telephone);
        assertEquals("johndoe@example.com",b.email);
	}
	
}
