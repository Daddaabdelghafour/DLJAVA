package ensa.Abdou;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
public class Client {
	
    public int id;
    public String nom;
    public String prenom;
    public String adresse;
    public String telephone;
    public String email;
    public List<Compte> comptes;

    public Client(int id ,String nom, String prenom, String adresse, String telephone, String email) {
    	this.id = id;
    	this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.comptes = new ArrayList<>();
    }
    public Client() {
    	
    }
    public int getId() {
    	return id;
    }
    public String getNom() {
    	return nom;
    }
    
    public String getPrenom() {
    	return prenom;
    }
    
    public String getAdresse() {
    	return adresse;
    }
    
    public String getTelephone() {
    	return telephone;
    }
    
    public String getEmail() {
    	return email;
    }
    
    
    

    public void addCompte(Compte compte) {
        comptes.add(compte);
    }
}
