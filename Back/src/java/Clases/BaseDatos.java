/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {
    //ATRIBUTOS
    Connection con = null;
    Statement st = null;
    
    //CONSTRUCTOR
    public BaseDatos(){};
    
    public void conectar(){
        try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e){
                System.out.println("Error al cargar el driver JDBC de MySql: " + e.getMessage());
            }
            con = null;
            
            try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia","root","");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }
    
    public void desconectar(){
        try {
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet PedirDatos(String querry){
        ResultSet rs;   
        try {
                return st.executeQuery(querry);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public void ActuralizarDatos(String querry){
        ResultSet rs;   
        try {
                 st.executeUpdate(querry);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
