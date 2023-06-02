package Clases;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danis
 */
public class Doctor extends Person{
    //ATRIBUTOS
    private String pass;
    private LocalDate lastLog;
    private int session;
    private ArrayList realeseList;
    
    
    //METODOS CONSTRUCTORES

    public Doctor() {
        this.realeseList = new ArrayList<>();
    }

    public Doctor(String pass, LocalDate lastLog, int session, String nom, String mail) {
        super(nom, mail);
        this.pass = pass;
        this.lastLog = lastLog;
        this.session = session;
        this.realeseList = new ArrayList<>();
    }
    
    //METODOS
    
    //metodo login que si el mail y la contraseña son correctos carga todos los atributos de la base de datos del objeto dado 
    //ademas de cargar los atributos lastlog y session del objeto en la base de datos
    public void login(String m,String p){
        try {
            String querry = "Select * from Doctor where mail='"+m+"' AND pass='"+p+"';";
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
         
            if(rs.next()){
                if(rs.getString("pass").equals(p)){ 
                    this.setLastLog(LocalDate.now());
                    
                    Random r = new Random();
                    String code = "";
                    for(int i = 0;i<9;i++){
                        code = code + r.nextInt(10);
                    }
                    
                    this.setSession(Integer.parseInt(code));
                    
                    querry = "Update doctor SET last_log ='"+this.getLastLog()+"', session = "+this.getSession()+" where mail='"+m+"';";
                    bbdd.ActuralizarDatos(querry);
                    
                    this.load(m);
                } else{
                    
                }
            }
            bbdd.desconectar();
        } catch (Exception ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //devuelve un true si encuentra el email con la sesion en data, carga los datos con el login, en caso contrario devuelve falso
    //con el metodo lo que queremos hacer es comprobar si todo esta bien que el sesion sea del dia actual y que email concuerde, si todo funciona
    //llamamos a la funcion login y entramos. Si no esta todo devolvemos falso.
    public boolean isLogged(String m, String s){
        try {
            String querry = "Select * from Doctor where mail = '"+m+"' AND session ="+s+";";
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            
            while (!rs.isClosed() && rs.next()){
                if(rs.getInt("session") == Integer.parseInt(s) && 
                        rs.getString("last_log").equals(LocalDate.now().toString())){
                    this.load(m);
                    return true;
                }
            }
            return false;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //carga todos los datos del doctor con la id correspondiente
    public void load(String m){
        try {
            String querry = "Select * from Doctor where mail='"+m+"';";
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            while (!rs.isClosed() && rs.next()){
                this.mail = rs.getString("mail");
                this.nom = rs.getString("name");
                this.pass = rs.getString("pass");
                this.lastLog = LocalDate.parse(rs.getString("last_log"));
                this.session = rs.getInt("session");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //carge dentro del array todos los xips que estan vinculados al doctor
    public void loadReleaseList(){
        try {
            String querry = "Select * from xip where doctor_mail = '"+this.mail+"' and date >= CURDATE();";
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            Xip x;
            while (!rs.isClosed() && rs.next()){
                x = new Xip();
                x.setId(rs.getInt("id"));
                x.setDoctor(rs.getString("doctor_mail"));
                x.setMedicine(rs.getInt("id_medicine"));
                x.setPatient(rs.getString("id_patient"));
                x.setFecha(rs.getDate("date"));
                realeseList.add(x);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
    //devuelve un String que corresponde a un html de todos los xips de alta vigente del doctor
    public String getTable(){
        String tabla = "";
        
        
        Iterator<Xip> it= realeseList.iterator();
        while(it.hasNext()) {
            Xip aux = it.next();
            tabla = tabla +"<tr>\n<td>"+aux.getId()+"</td>\n"
                                +"<td>"+aux.getDoctor()+"</td>\n"
                                +"<td>"+getNombreMedicine(aux.getMedicine())+"</td>\n"
                                +"<td>"+aux.getPatient()+"</td>\n"
                                +"<td>"+aux.getFecha()+"</td>\n"+"</tr>\n";
        }
        
        
        return tabla;
    }
    
    private String getNombreMedicine(int i){
        try {
            String querry = "Select * from medicine where id = "+i+";";
            String nombre = "";
             
            BaseDatos bbdd = new BaseDatos();
            bbdd.conectar();
            ResultSet rs = bbdd.PedirDatos(querry);
            if(rs.next()){
                 nombre = rs.getString("name");
            }
            return nombre;
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    //GETTERS
    public String getPass() {
        return pass;
    }

    public LocalDate getLastLog() {
        return lastLog;
    }

    public int getSession() {
        return session;
    }

    public ArrayList getRealeseList() {
        return realeseList;
    }
    
    
    //SETTERS

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setLastLog(LocalDate lastLog) {
        this.lastLog = lastLog;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public void setRealeseList(ArrayList realeseList) {
        this.realeseList = realeseList;
    }
    
    
    //TOSTRING

    @Override
    public String toString() {
        return "Doctor{" + "pass=" + pass + ", lastLog=" + lastLog + ", session=" + session + ", realeseList=" + realeseList + '}';
    }
    
    
    
    
}
