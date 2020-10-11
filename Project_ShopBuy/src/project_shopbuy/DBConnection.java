/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_shopbuy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author junio
 */
public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/Shop_Buy"; 
    private static final String username = "postgres"; 
    private static final String password = "postgres123"; 
    
    public static Connection createConnection(){
        
        try{
            Connection connect; 
            connect = DriverManager.getConnection(url, username , password);
            return connect;
        }
        catch(SQLException e){
            System.out.println("Erro" + e);
        }
        return null;
    }
    
}
