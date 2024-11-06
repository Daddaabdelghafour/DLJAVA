package ensa.Abdou;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.sql.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ClientUi extends Application {

    // Client fields
    private TextField tfNom;
    private TextField tfPrenom;
    private TextField tfAdresse;
    private TextField tfTelephone;
    private TextField tfEmail;
    private ComboBox<Integer> clBanqueId;

    // Account fields
    private TextField tfDevise; // For account currency
    private ComboBox<Integer> cbClientId; // ComboBox to select Client ID for account
    private ComboBox<Integer> cbCompteId; // ComboBox for account selection
    
    // Transaction fields
    private ComboBox<Integer> cbSenderClient; // ComboBox for selecting sender client
    private ComboBox<Integer> cbReceiverClient; // ComboBox for selecting receiver client
    private Label lblTransactionType; // Label to display the transaction type

    // Bank fields
    private TextField tfNomBanque;
    private TextField tfAdresseBanque;
    private TextField tfTelephoneBanque;
    private TextField tfEmailBanque;
    private TextField tfpays;

    private ListView<String> clientListView;
    private ListView<String> accountListView;
    private ListView<String> transactionListView;
    private ListView<String> banqueListView; // ListView for banks

    private ObservableList<String> transactionList;
    private ObservableList<String> clientList;
    private ObservableList<String> accountList;
    private ObservableList<String> banqueList; // Observable list for banks
    private ComboBox<Integer> cbBanqueId; // ComboBox for bank selection

 // Search fields
    TextField searchClientField = new TextField();
    TextField searchBanqueField = new TextField();
    TextField searchTransactionField = new TextField();
    TextField searchCompteField = new TextField();
    
    
    
    
    private Dbmanager dbManager;

    @Override
    public void start(Stage stage) {
        // Initialize database connection
        try {
            String url = "jdbc:mysql://localhost:3306/td1java"; // Replace with your database details
            String user = "root";
            String password = "";

            dbManager = new Dbmanager(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Failed to connect to the database.");
            return;
        }

        // Client information fields
        tfNom = new TextField(); tfNom.setPromptText("Nom");
        tfPrenom = new TextField(); tfPrenom.setPromptText("Prénom");
        tfAdresse = new TextField(); tfAdresse.setPromptText("Adresse");
        tfTelephone = new TextField(); tfTelephone.setPromptText("Téléphone");
        tfEmail = new TextField(); tfEmail.setPromptText("Email");
        clBanqueId = new ComboBox<>();
        clBanqueId.setPromptText("Sélectionnez l'id de banque du client : ");
        // Account information field
        tfDevise = new TextField(); tfDevise.setPromptText("Devise");

        // ComboBox for selecting client
        cbClientId = new ComboBox<>();
        cbClientId.setPromptText("Sélectionnez le client");

        // ComboBox for selecting account
        cbCompteId = new ComboBox<>();
        cbCompteId.setPromptText("Sélectionnez le compte");

        // Transaction sender and receiver ComboBoxes
        cbSenderClient = new ComboBox<>();
        cbSenderClient.setPromptText("Sélectionnez le compte du client émetteur");

        cbReceiverClient = new ComboBox<>();
        cbReceiverClient.setPromptText("Sélectionnez le compte du client destinataire");

        lblTransactionType = new Label("Type de transaction: "); // Label for transaction type

        // Transaction type ComboBox
        ComboBox<String> cbTypeTransaction = new ComboBox<>();
        cbTypeTransaction.getItems().addAll("virin", "virest", "virchac", "virmulta");
        cbTypeTransaction.setPromptText("Sélectionnez le type de transaction");

        // Initialize the ComboBox for banks
        cbBanqueId = new ComboBox<>();
        cbBanqueId.setPromptText("Sélectionnez la banque");

        // Buttons for adding clients, accounts, transactions, and banks
        Button btnAddClient = new Button("Ajouter Client");
        btnAddClient.setOnAction(e -> addClient());

        Button btnAddCompte = new Button("Ajouter Compte");
        btnAddCompte.setOnAction(e -> addCompte());

        Button btnAddTransaction = new Button("Ajouter Transaction");
        btnAddTransaction.setOnAction(e -> addTransaction());

        Button btnAddBanque = new Button("Ajouter Banque");
        btnAddBanque.setOnAction(e -> addBanque());

        // ListViews for displaying clients, accounts, transactions, and banks
        clientList = FXCollections.observableArrayList();
        clientListView = new ListView<>(clientList);
        clientListView.setMinHeight(150);

        accountList = FXCollections.observableArrayList();
        accountListView = new ListView<>(accountList);
        accountListView.setMinHeight(150);

        transactionList = FXCollections.observableArrayList();
        transactionListView = new ListView<>(transactionList);
        transactionListView.setMinHeight(150);
        
        banqueList = FXCollections.observableArrayList();
        banqueListView = new ListView<>(banqueList); // ListView for banks
        banqueListView.setMinHeight(150);

        // Main layout
        tfNomBanque = new TextField(); tfNomBanque.setPromptText("Nom de Banque");
        tfAdresseBanque = new TextField(); tfAdresseBanque.setPromptText("Adresse de Banque");
        tfTelephoneBanque = new TextField(); tfTelephoneBanque.setPromptText("Téléphone de Banque");
        tfEmailBanque = new TextField(); tfEmailBanque.setPromptText("Email de Banque");
        tfpays= new TextField();tfpays.setPromptText("pays de la banque");

        //search layout
        searchClientField.setPromptText("Rechercher Client...");
        searchBanqueField.setPromptText("Rechercher Banque...");
        searchTransactionField.setPromptText("Rechercher Transaction...");
        searchCompteField.setPromptText("Rechercher Compte...");
        
        searchClientField.textProperty().addListener((observable, oldValue, newValue) -> searchClients(newValue));
        searchCompteField.textProperty().addListener((observable, oldValue, newValue) -> searchAccounts(newValue));
        searchTransactionField.textProperty().addListener((observable, oldValue, newValue) -> searchTransactions(newValue));
        searchBanqueField.textProperty().addListener((observable, oldValue, newValue) -> searchBanques(newValue));

        
        
        
        
        // Main layout
        VBox mainLayout = new VBox(10,
            new Label("Informations Client:"),  tfNom, tfPrenom, tfAdresse, tfTelephone, tfEmail, btnAddClient,
            new Label("Informations Compte:"),tfDevise, cbClientId, cbBanqueId, btnAddCompte, // Add cbBanqueId here
            new Label("Ajouter Transaction:"),cbSenderClient, cbReceiverClient, lblTransactionType, btnAddTransaction,
            new Label("Ajouter Banque:"),tfNomBanque, tfAdresseBanque, tfTelephoneBanque, tfEmailBanque,tfpays, btnAddBanque,
            new Label("Clients:"), searchClientField,clientListView,
            new Label("Comptes:"), searchCompteField,accountListView,
            new Label("Transactions:"), searchTransactionField,transactionListView,
            new Label("Banques:"), searchBanqueField,banqueListView);

        mainLayout.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Scene and stage configuration
        Scene scene = new Scene(scrollPane, 400, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Gestion des Clients et Transactions");
        stage.show();

        // Load existing data from the database
        loadClients();
        loadAccounts();
        loadTransactions();
        loadBanques(); // Load banks
    }
    private void loadClients() {
        String sql = "SELECT client_id, nom, prenom, adresse, telephone, email FROM client";

        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            clientList.clear();
            cbClientId.getItems().clear(); // Clear previous items
            cbSenderClient.getItems().clear(); // Clear previous sender items
            cbReceiverClient.getItems().clear(); // Clear previous receiver items
            

            while (rs.next()) {
                String clientInfo = rs.getString("client_id") + " - " + rs.getString("nom") + " " + rs.getString("prenom");
                clientList.add(clientInfo);

                int clientId = rs.getInt("client_id");
            

                cbClientId.getItems().add(clientId); // Add client ID to ComboBox
                
                
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Error loading clients from the database.");
        }
    }


    private void loadAccounts() {
        String sql = "SELECT cp.compte_id, cp.num_compte, cp.date_creation, cp.date_update, cp.devise, " +
                     "c.nom AS client_nom, c.prenom AS client_prenom " +
                     "FROM compte cp " +
                     "JOIN client c ON cp.client_id = c.client_id";

        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
        	
            accountList.clear();
            cbCompteId.getItems().clear(); // Clear previous items

            while (rs.next()) {
                String accountInfo = "Compte ID: " + rs.getInt("compte_id") +
                                     ", Numéro de Compte: " + rs.getString("num_compte") +
                                     ", Date de Création: " + rs.getDate("date_creation")
                                             + ", Client: " + rs.getString("client_nom") + " " + rs.getString("client_prenom");
                							int compteId = rs.getInt("compte_id");
                                             accountList.add(accountInfo);
                                             cbCompteId.getItems().add(rs.getInt("compte_id")); // Ajout des IDs de compte au ComboBox
                                             cbSenderClient.getItems().add(compteId); // Add client ID to sender ComboBox
                                             cbReceiverClient.getItems().add(compteId); // Add client ID to receiver ComboBox
                                         }

                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                         showAlert("Database Error", "Error loading accounts from the database.");
                                     }
                                 }

    private void loadTransactions() {
        String sql = "SELECT t.transaction_id, t.reference, t.timestamp, t.type, c.nom AS client_nom, c.prenom AS client_prenom " +
                     "FROM transaction t " +
                     "JOIN compte cp ON t.compte_id = cp.compte_id " +
                     "JOIN client c ON cp.client_id = c.client_id";

        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            transactionList.clear();

            while (rs.next()) {
                String transactionInfo = "Transaction ID: " + rs.getInt("transaction_id") +
                                          ", Reference: " + rs.getString("reference") +
                                          ", Timestamp: " + rs.getTimestamp("timestamp") +
                                          ", Type: " + rs.getString("type") +
                                          ", Client: " + rs.getString("client_nom") + " " + rs.getString("client_prenom");
                transactionList.add(transactionInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Error loading transactions from the database.");
        }
    }

                                 private void loadBanques() {
                                     String sql = "select banque_id , nom from banque ";

                                     try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
                                          ResultSet rs = pstmt.executeQuery()) {

                                         banqueList.clear();
                                         cbBanqueId.getItems().clear(); // Clear previous items

                                         while (rs.next()) {
                                             String banqueInfo = "Banque ID: " + rs.getInt("banque_id") +
                                                                 ", Nom: " + rs.getString("nom");
                                             banqueList.add(banqueInfo);
                                             cbBanqueId.getItems().add(rs.getInt("banque_id")); // Ajout des IDs de banque au ComboBox
                                             clBanqueId.getItems().add(rs.getInt("banque_id")); // Ajout des IDs de banque au ComboBox
                                         }

                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                         showAlert("Database Error", "Error loading banks from the database.");
                                     }
                                 }

                                 private void addClient() {
                                	    String nom = tfNom.getText();
                                	    String prenom = tfPrenom.getText();
                                	    String adresse = tfAdresse.getText();
                                	    String telephone = tfTelephone.getText();
                                	    String email = tfEmail.getText();

                                	    // Update SQL to include banque_id
                                	    String sql = "INSERT INTO client (nom, prenom, adresse, telephone, email) VALUES (?, ?, ?, ?, ?)";

                                	    try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                	        pstmt.setString(1, nom);
                                	        pstmt.setString(2, prenom);
                                	        pstmt.setString(3, adresse);
                                	        pstmt.setString(4, telephone);
                                	        pstmt.setString(5, email);

                                	        pstmt.executeUpdate();

                                	        loadClients(); // Reload clients to update the list
                                	        clearClientFields(); // Clear input fields

                                	    } catch (SQLException e) {
                                	        e.printStackTrace();
                                	        showAlert("Database Error", "Error adding client to the database.");
                                	    }
                                	}


                                 

                                 private void addCompte() {
                                     String numCompte = generateNumCompte(); // Generate a unique account number
                                     String devise = tfDevise.getText();
                                     int clientId = cbClientId.getValue();
                                     int banqueId = cbBanqueId.getValue(); // Get selected bank ID
                                     String dateCreation = getCurrentDateTime(); // Get current date and time for creation
                                     String dateUpdate = dateCreation; // Set date of update as the same initially

                                     String sql = "INSERT INTO compte (num_compte, date_creation, date_update, devise, client_id, banque_id) VALUES (?, ?, ?, ?, ?, ?)";

                                     try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                         pstmt.setString(1, numCompte);
                                         pstmt.setString(2, dateCreation);
                                         pstmt.setString(3, dateUpdate);
                                         pstmt.setString(4, devise);
                                         pstmt.setInt(5, clientId);
                                         pstmt.setInt(6, banqueId); // Set the selected bank ID
                                         pstmt.executeUpdate();

                                         loadAccounts(); // Reload accounts to update the list
                                         clearCompteFields(); // Clear input fields

                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                         showAlert("Database Error", "Error adding account to the database.");
                                     }
                                 }

                                 // Method to get the current date and time in the required format
                                 private String getCurrentDateTime() {
                                     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                     return formatter.format(new Date(1));
                                 }

                                 // Method to generate a unique account number
                                 private String generateNumCompte() {
                                     String lastNumCompte = getLastNumCompte(); // Retrieve the last num_compte from the database
                                     int newNumber = Integer.parseInt(lastNumCompte.split("-")[1]) + 1;
                                     return String.format("CPT-%03d", newNumber); // Format as CPT-004
                                 }

                                 // Method to retrieve the last num_compte from the database
                                 private String getLastNumCompte() {
                                     String sql = "SELECT num_compte FROM compte ORDER BY compte_id DESC LIMIT 1";
                                     try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
                                          ResultSet rs = pstmt.executeQuery()) {
                                         if (rs.next()) {
                                             return rs.getString("num_compte");
                                         }
                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                         showAlert("Database Error", "Error retrieving the last account number.");
                                     }
                                     return "CPT-000"; // Default value if no records found
                                 }
                                 
                                 
                                 
                                 private String generateReference() {
                                	    // Get the current timestamp
                                	    long timestamp = System.currentTimeMillis();

                                	    // Generate a random number between 1000 and 9999
                                	    int randomNum = 1000 + (int)(Math.random() * 9000); // Generates a number between 1000 and 9999

                                	    // Combine them into a reference string
                                	    return "REF-" + timestamp + "-" + randomNum;
                                	}

                                 private int getCompteIDbyClientID(int clientId) {
                                	    String sql = "SELECT compte_id FROM compte WHERE client_id = ? limit 1";
                                	    int compteId = -1; // Default value if not found

                                	    try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                	        pstmt.setInt(1, clientId); // Set the client ID parameter
                                	        ResultSet rs = pstmt.executeQuery(); // Execute the query

                                	        if (rs.next()) { // Check if a result is returned
                                	            compteId = rs.getInt("compte_id"); // Retrieve the compte_id
                                	        }
                                	    } catch (SQLException e) {
                                	        e.printStackTrace();
                                	        showAlert("Database Error", "Error retrieving compte ID for the given client ID.");
                                	    }

                                	    return compteId; // Return the found compte_id or -1 if not found
                                	}

                                 
                                 private void addTransaction() {
                                	    int senderClientId = cbSenderClient.getValue(); // Get the client ID of the sender
                                	    System.out.println(senderClientId);
                                	  //  int compteId = getCompteIDbyClientID(senderClientId); // Get the corresponding compte ID for the sender
                                	    System.out.println("Compte ID: " + senderClientId); // Log the compte ID for debugging

                                	    // Determine the transaction type using the client IDs of the sender and receiver
                                	    String transactionType = determineTransactionType(senderClientId, cbReceiverClient.getValue());

                                	    // SQL statement for inserting a new transaction
                                	    String sql = "INSERT INTO transaction (reference, type, compte_id, timestamp) VALUES (?, ?, ?, ?)";

                                	    try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                	        pstmt.setString(1, generateReference()); // Generate the transaction reference
                                	        pstmt.setString(2, transactionType); // Set the transaction type
                                	        pstmt.setInt(3, senderClientId); // Set the compte ID derived from the sender's client ID
                                	        pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Set the current timestamp
                                	        pstmt.executeUpdate(); // Execute the update

                                	        loadTransactions(); // Reload transactions to update the list

                                	    } catch (SQLException e) {
                                	        e.printStackTrace();
                                	        showAlert("Database Error", "Error adding transaction to the database.");
                                	    }
                                	}


                                 
                                 
                                 public String getCountry(int compteclientId) {
                                     String sql = "select b.pays from compte c , banque b where c.banque_id=b.banque_id  and c.compte_id=?";
                                     try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                         pstmt.setInt(1, compteclientId);
                                         ResultSet rs = pstmt.executeQuery();
                                         if (rs.next()) {
                                             // Assuming country is stored as part of the address or extracted from it
                                             String address = rs.getString("b.pays");
                                             return address;
                                         }
                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                         showAlert("Database Error", "Error retrieving country for the client.");
                                     }
                                     return null; // Return null if not found or on error
                                 }
                                 	
                                 
                                 public String getBanquename(int compteclientId) {
                                     String sql = "select b.nom from compte c , banque b where c.banque_id=b.banque_id and c.compte_id=?";
                                     try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                         pstmt.setInt(1, compteclientId);
                                         ResultSet rs = pstmt.executeQuery();
                                         if (rs.next()) {
                                             return rs.getString("b.nom");
                                         }
                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                         showAlert("Database Error", "Error retrieving bank ID for the client.");
                                     }
                                     return "none"; // Return -1 if not found or on error
                                 }
                                 
                                 
                                 public String determineTransactionType(int senderId, int receiverId) {
                                	    // Fetch the client objects (or details) for the sender and receiver
                                	    int senderClientid = senderId;
                                	    int receiverClientid = receiverId;

                                	    // Check if clients are from the same bank in the same country
                                	    if (getBanquename(senderClientid).equals( getBanquename(receiverClientid)) && 
                                	        getCountry(senderClientid).equals(getCountry(receiverClientid))) {
                                	        return "VIRIN"; // Same bank, same country
                                	    }
                                	    // Check if clients are from the same bank in different countries
                                	    else if (getBanquename(senderClientid).equals(getBanquename(receiverClientid))) {
                                	        return "VIREST"; // Same bank, different country
                                	    }
                                	    // Clients from different banks in different countries
                                	    else {
                                	        return "VIRMULTA"; // Different banks, different countries
                                	    }
                                	}

                                	// Example method to get a client by ID
                                	

                                 private void addBanque() {
                                     String nom = tfNomBanque.getText();
                                     String adresse = tfAdresseBanque.getText();
                                     String telephone = tfTelephoneBanque.getText();
                                     String email = tfEmailBanque.getText();
                                     String pays = tfpays.getText();
                                     String sql = "INSERT INTO banque (nom, adresse, telephone, email,pays) VALUES (?, ?, ?, ?,?)";

                                     try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                         pstmt.setString(1, nom);
                                         pstmt.setString(2, adresse);
                                         pstmt.setString(3, telephone);
                                         pstmt.setString(4, email);
                                         pstmt.setString(5, pays);
                                         pstmt.executeUpdate();

                                         loadBanques(); // Reload banks to update the list
                                         clearBanqueFields(); // Clear input fields

                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                         showAlert("Database Error", "Error adding bank to the database.");
                                     }
                                 }
                                 
                                 private void searchClients(String searchText) {
                                	    clientList.clear();
                                	    
                                	    String sql = "SELECT client_id, nom, prenom, adresse, telephone, email FROM client WHERE " +
                                	                 "nom LIKE ? OR prenom LIKE ? OR adresse LIKE ? OR telephone LIKE ? OR email LIKE ?";
                                	    
                                	    try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                	        String searchPattern = "%" + searchText + "%";
                                	        for (int i = 1; i <= 5; i++) {
                                	            pstmt.setString(i, searchPattern);
                                	        }

                                	        try (ResultSet rs = pstmt.executeQuery()) {
                                	            while (rs.next()) {
                                	                String clientInfo = rs.getInt("client_id") + " - " + rs.getString("nom") + " " + rs.getString("prenom");
                                	                clientList.add(clientInfo);
                                	            }
                                	        }

                                	    } catch (SQLException e) {
                                	        e.printStackTrace();
                                	        showAlert("Database Error", "Error searching clients from the database.");
                                	    }
                                	}

                                 
                                 
                                 private void searchAccounts(String searchText) {
                                	    accountList.clear();
                                	    
                                	    String sql = "SELECT cp.compte_id, cp.num_compte, cp.date_creation, cp.devise, " +
                                	                 "c.nom AS client_nom, c.prenom AS client_prenom FROM compte cp " +
                                	                 "JOIN client c ON cp.client_id = c.client_id " +
                                	                 "WHERE cp.num_compte LIKE ? OR cp.devise LIKE ? OR c.nom LIKE ? OR c.prenom LIKE ?";
                                	    
                                	    try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                	        String searchPattern = "%" + searchText + "%";
                                	        for (int i = 1; i <= 4; i++) {
                                	            pstmt.setString(i, searchPattern);
                                	        }

                                	        try (ResultSet rs = pstmt.executeQuery()) {
                                	            while (rs.next()) {
                                	                String accountInfo = "Compte ID: " + rs.getInt("compte_id") +
                                	                                     ", Numéro de Compte: " + rs.getString("num_compte") +
                                	                                     ", Date de Création: " + rs.getDate("date_creation") +
                                	                                     ", Client: " + rs.getString("client_nom") + " " + rs.getString("client_prenom");
                                	                accountList.add(accountInfo);
                                	            }
                                	        }

                                	    } catch (SQLException e) {
                                	        e.printStackTrace();
                                	        showAlert("Database Error", "Error searching accounts from the database.");
                                	    }
                                	}

                                 
                                 
                                 private void searchTransactions(String searchText) {
                                	    transactionList.clear();

                                	    String sql = "SELECT t.transaction_id, t.reference, t.timestamp, t.type, c.nom AS client_nom, " +
                                	                 "c.prenom AS client_prenom FROM transaction t " +
                                	                 "JOIN compte cp ON t.compte_id = cp.compte_id " +
                                	                 "JOIN client c ON cp.client_id = c.client_id " +
                                	                 "WHERE t.reference LIKE ? OR t.type LIKE ? OR c.nom LIKE ? OR c.prenom LIKE ?";
                                	    
                                	    try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                	        String searchPattern = "%" + searchText + "%";
                                	        for (int i = 1; i <= 4; i++) {
                                	            pstmt.setString(i, searchPattern);
                                	        }

                                	        try (ResultSet rs = pstmt.executeQuery()) {
                                	            while (rs.next()) {
                                	                String transactionInfo = "Transaction ID: " + rs.getInt("transaction_id") +
                                	                                          ", Reference: " + rs.getString("reference") +
                                	                                          ", Timestamp: " + rs.getTimestamp("timestamp") +
                                	                                          ", Type: " + rs.getString("type") +
                                	                                          ", Client: " + rs.getString("client_nom") + " " + rs.getString("client_prenom");
                                	                transactionList.add(transactionInfo);
                                	            }
                                	        }

                                	    } catch (SQLException e) {
                                	        e.printStackTrace();
                                	        showAlert("Database Error", "Error searching transactions from the database.");
                                	    }
                                	}

                                 
                                 
                                 
                                 
                                 private void searchBanques(String searchText) {
                                	    banqueList.clear();
                                	    
                                	    String sql = "SELECT banque_id, nom FROM banque WHERE nom LIKE ?";
                                	    
                                	    try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
                                	        pstmt.setString(1, "%" + searchText + "%");

                                	        try (ResultSet rs = pstmt.executeQuery()) {
                                	            while (rs.next()) {
                                	                String banqueInfo = "Banque ID: " + rs.getInt("banque_id") +
                                	                                    ", Nom: " + rs.getString("nom");
                                	                banqueList.add(banqueInfo);
                                	            }
                                	        }

                                	    } catch (SQLException e) {
                                	        e.printStackTrace();
                                	        showAlert("Database Error", "Error searching banks from the database.");
                                	    }
                                	}

                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                 private void clearClientFields() {
                                     tfNom.clear();
                                     tfPrenom.clear();
                                     tfAdresse.clear();
                                     tfTelephone.clear();
                                     tfEmail.clear();
                                 }

                                 private void clearCompteFields() {
                                     tfDevise.clear();
                                     cbClientId.getSelectionModel().clearSelection();
                                 }

                                 private void clearBanqueFields() {
                                     tfNomBanque.clear();
                                     tfAdresseBanque.clear();
                                     tfTelephoneBanque.clear();
                                     tfEmailBanque.clear();
                                     cbBanqueId.getSelectionModel().clearSelection();
                                 }

                                 private void showAlert(String title, String message) {
                                     Alert alert = new Alert(Alert.AlertType.ERROR);
                                     alert.setTitle(title);
                                     alert.setHeaderText(null);
                                     alert.setContentText(message);
                                     alert.showAndWait();
                                 }
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                 public static void main(String[] args) {
                                     launch(args);
                                 }

                                 
                             }
