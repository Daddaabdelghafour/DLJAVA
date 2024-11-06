package ensa.Abdou;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class Banque {
    @Getter
	public int id;
    @Getter
    public String pays;
    @Getter
    public String adresse;
    public String telephone;
    public String email;
    public List<Compte> comptes;

    public Banque() {
    	
    }
    
    public Banque(int id,String pays,String adresse,String telephone , String email) {
    	this.id = id;
        this.pays = pays;
        this.adresse=adresse;
        this.telephone=telephone;
        this.email=email;
        this.comptes = new ArrayList<>();
    }

 

    public void addCompte(Compte compte) {
        comptes.add(compte);
    }
}
