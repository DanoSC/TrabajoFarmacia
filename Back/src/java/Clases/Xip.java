package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Xip {
    //ATRIBUTOS
    private int id;
    private String doctor;
    private int medicine;
    private String patient;
    private Date fecha;
    
    //METODOS CONTRUCTORES

    public Xip() {
    }
    
    
    public Xip(int id,String doctor, int medicine, String patient, Date fecha) {
        this.id = id;
        this.doctor = doctor;
        this.medicine = medicine;
        this.patient = patient;
        this.fecha = fecha;
    }
    
    //METODOS
    
    //carga los atributos de la base de datos del objeto
    public void load(int i){
        try {
            String querry = "Select * from xip where id="+i+";";
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            while (!rs.isClosed() && rs.next()){
                this.setId(rs.getInt("id"));
                this.setDoctor(rs.getString("doctor_mail"));
                this.setMedicine(rs.getInt("id_medicine"));
                this.setPatient(rs.getString("id_patient"));
                this.setFecha(rs.getDate("date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    //SETTERS

    public void setId(int id) {
        this.id = id;
    }
    
    public void setDoctor(String m) {
        this.doctor = m;
    }

    public void setMedicine(int medicine) {
        this.medicine = medicine;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    //GETTERS

    public int getId() {
        return id;
    }

    public String getDoctor() {
        return doctor;
    }

    
    public int getMedicine() {
        return medicine;
    }

    public String getPatient() {
        return patient;
    }

    
    public Date getFecha() {
        return fecha;
    }
    
    //TOsTRING

    @Override
    public String toString() {
        return "Xip{" + "id=" + id + ", doctor=" + doctor + ", medicine=" + medicine + ", patient=" + patient + ", fecha=" + fecha + '}';
    }
    
    
}
