package ensa.Abdou;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dbmanager {
    private Connection connection;

    // Constructor for DB connection
    public Dbmanager(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    // Get the database connection
    public Connection getConnection() {
        return connection;
    }

    // Add a client to the database
    /* 
    public void addClient(Client client) {
        String sql = "INSERT INTO clients (nom, prenom, adresse, telephone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getPrenom());
            pstmt.setString(3, client.getAdresse());
            pstmt.setString(4, client.getTelephone());
            pstmt.setString(5, client.getEmail());
            pstmt.executeUpdate();
            System.out.println("Client added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new compte for a client
    public void addCompte(Compte compte, int clientId) {
        String sql = "INSERT INTO comptes (devise, client_id) VALUES (?, ?)"; // Assuming client_id is the foreign key
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, compte.getDevise());
            pstmt.setInt(2, clientId);
            pstmt.executeUpdate();
            System.out.println("Compte added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new transaction for a specific compte
    public void addTransaction(Transaction transaction, int compteId) {
       
    }
    */

    // Close the connection
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
