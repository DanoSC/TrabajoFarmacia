/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

public abstract class Person  {
    //Atributos
    //nombre completo de la persona 
    protected String nom;
    protected String mail;
    
    //Constructores
    public Person(String nom, String mail) {
        this.nom = nom;
        this.mail = mail;
    }

    public Person() {
    }
    
    
    //Getters y Setters
    public String getNom() {
        return this.nom;
    }

    public String getMail() {
        return this.mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    //metodo load
    abstract public void  load(String id);
    
    
}
