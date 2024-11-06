package ensa.Abdou;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compte {
    public int numCompte;
    public Date dateCreation;
    public Date dateUpdate;
    public String devise;
    public List<Transaction> transactions;
    public Client c;
    public Banque b;
    
    
    public Compte() {
    	
    }
    public Compte(int numcompte ,String devise) {
    	this.numCompte=numcompte;
        this.dateCreation = new Date();
        this.dateUpdate=new Date();
        this.devise = devise;
        this.transactions = new ArrayList<>();
        this.c=new Client();
        this.b=new Banque();
    }


    	public String getDevise() {
    		return this.devise;
    	}
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
