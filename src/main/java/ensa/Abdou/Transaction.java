package ensa.Abdou;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
    public Type typeTransaction;
    public Date timestamp;
    public String reference;
    public List<Compte> comptes;

    
    public Transaction() {
    	
    }
    
    public Transaction(Type typeTransaction, Date timestamp, String reference) {
        this.typeTransaction = typeTransaction;
        this.timestamp = timestamp;
        this.reference = reference;
        this.comptes=new ArrayList<Compte>();
    }

    public Type getType() {
    	return this.typeTransaction;
    }
    

}
