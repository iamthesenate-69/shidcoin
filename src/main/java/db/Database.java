package db;

import java.sql.*;

/*
User ID for Discord users: "discord:<uid>" for example discord:376857210485080064
User ID for Smart Contracts: "contract:<uid>" for example contract:

Item ID format: "<namespace>:<item>"
Items:
- shidcoin
 - shidcoin:shidcoin
* */

public class Database {

    private Connection conn;

    // things for dthusian:
    // list items



    //global stuff
    public final static int conjugationGain = 1;
    public final static int pinGain = 50;
    public final static int conjugationLoss = 1;
    public final static String conjugationCurrency = "shidcoin:shidcoin";


    public Database() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:shidders.sqlite");
        Statement initStmt = conn.createStatement();
        initStmt.setQueryTimeout(30);
        initStmt.executeUpdate("create table if not exists ShidUsers (userId text primary key, userDisplayName text, socialCredit integer, smartContract blob)");
        initStmt.executeUpdate("create table if not exists ShidItems (userId text, itemName text, quantity integer)");
    }

    // I don't care about sqli
    private static String escapeSql(String str) {
        return str.replace('\'', ' ').replace('\\', '/');
    }


    public void registerUser(String discordUid, String displayName) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = String.format("insert into ShidUsers values ('%s', '%s', 1000, '')", getSQID(discordUid), escapeSql(displayName));
        stmt.executeUpdate(query);
    }

    public int getItemQuantity(String user, String item) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = String.format("select (quantity) from ShidItems where userId = '%s' and itemName = '%s'", escapeSql(user), escapeSql(item));
        ResultSet results = stmt.executeQuery(query);

        return results.getInt(0);
    }


    public void setItemQuantity(String user, String item, int quantity) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = String.format("update ShidUsers set quantity = %d where userId = %s and itemName = %s", quantity, escapeSql(user), escapeSql(item));
        int rowsAffected = stmt.executeUpdate(query);
        if(rowsAffected == 0) {
            query = String.format("insert into ShidUsers values ('%s', '%s', %d)", escapeSql(user), escapeSql(item), quantity);
            stmt.executeUpdate(query);
        }
    }

    public void close() throws SQLException {
        conn.close();
    }

    // SQID game
    public static String getSQID(String id) {
        return "discord:" + id;
    }
}