package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Patient extends Person{
    //ATRIBUTOS
    
    
    //METODOS CONSTRUTORES
    public Patient(String nom, String mail) {
        super(nom, mail);
    }

    public Patient() {
    }
    
    
    //METODOS
    //carga los datos del paciente dado 
    public void load(String mail){
        try {
            String querry = "Select * from patient where mail='"+mail+"';";
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            while (!rs.isClosed() && rs.next()){
                this.setMail(rs.getString("mail"));
                this.setNom(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    //metodo para que me devuelva un arraylist con todos los mail de los pacientes
    public static ArrayList ListaPatients(){
        try {
            String querry = "Select * from patient;";
            ArrayList<String> patients = new ArrayList<>();
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            while (!rs.isClosed() && rs.next()){
                patients.add(rs.getString("mail"));
            }
            return patients;
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    
    
    
    
    
}
