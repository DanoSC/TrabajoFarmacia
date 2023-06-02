package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Medicine {
    //ATRIBUTOS
    private int id;
    private String name;
    private float Tmax;
    private float Tmin;
    
    //METODOS CONSTRUCTORES

    public Medicine() {
    }

    public Medicine(int id, String name, float Tmax, float Tmin) {
        this.id = id;
        this.name = name;
        this.Tmax = Tmax;
        this.Tmin = Tmin;
    }
    
    //METODOS
    
    //carga todos los datos del objeto dado 
    public void load(int i){
       try {
            String querry = "Select * from medicine where id="+i+";";
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            while (!rs.isClosed() && rs.next()){
                this.setId(rs.getInt("id"));
                this.setName(rs.getString("name"));
                this.setTmax(rs.getFloat("Tmax"));
                this.setTmin(rs.getFloat("Tmin"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    //metodo para que me devuelva un arraylist con todas las medicinas de la base de datos
    public static ArrayList ListaMedicine(){
        try {
            String querry = "Select * from medicine;";
            ArrayList<Medicine> medicine = new ArrayList<>();
            Medicine m;
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            
            while (!rs.isClosed() && rs.next()){
                m = new Medicine();
                m.load(rs.getInt("id"));
                medicine.add(m);
            }
            return medicine;
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTmax(float Tmax) {
        this.Tmax = Tmax;
    }

    public void setTmin(float Tmin) {
        this.Tmin = Tmin;
    }
    
            
            
}
