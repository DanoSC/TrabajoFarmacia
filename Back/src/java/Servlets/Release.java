/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.BaseDatos;
import Clases.Doctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danis
 */
@WebServlet(name = "Release", urlPatterns = {"/Release"})
public class Release extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Release</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Release at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("entamos en el post del release");
            String mail = request.getParameter("mail");
            String session = request.getParameter("session");
            int xipId = Integer.parseInt(request.getParameter("XipId"));
            int idMed = Integer.parseInt(request.getParameter("medicine"));
            String pMail = request.getParameter("patient");
            String fecha = request.getParameter("date");
            
            System.out.println("AHORA INTENTAMOS INSERTAR EL XIP");
            
            
            
            System.out.println(mail);
            System.out.println(session);
            Doctor d = new Doctor();
            boolean insertar = true;
            if(d.isLogged(mail, session)){
                String querry = "Select * from xip where id = "+xipId+";";
                BaseDatos bbdd = new BaseDatos();
                bbdd.conectar();
                ResultSet rs = bbdd.PedirDatos(querry);
                if(rs.next()){
                    if(rs.getInt("id") == xipId){
                        insertar = false;
                    }
                }
                if(insertar){
                    querry = "INSERT INTO xip VALUES("+xipId+",'"+mail+"',"+idMed+",'"+pMail+ "','"+fecha+"')";
                    bbdd.ActuralizarDatos(querry);
                    response.getWriter().append("insertado");
                }
                
            }
            // Permitir solicitudes desde cualquier origen
            response.setHeader("Access-Control-Allow-Origin", "*"); 
        } catch (SQLException ex) {
                        Logger.getLogger(Release.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
