/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.CalcBmi;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author katharina
 */
@WebServlet(urlPatterns = {"/Calculation"})
public class Calculation extends HttpServlet {

    @Resource(mappedName = "java:jboss/exported/jms/queue/bmi")
    private Queue bmi;

    @Inject
    @JMSConnectionFactory("java:jboss/exported/jms/RemoteConnectionFactory")
    private JMSContext context;

    @EJB
    private CalcBmi calcBmi;
   
    
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
            double height = Double.parseDouble(request.getParameter("height"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            String name = request.getParameter("name");

            //calls stateless bean
            double result = calcBmi.calcBmi(height, weight);
            
            System.out.println("Calc bmi");
            
            sendMessage(new BmiRecord(name,height,weight,result));
           
            out.println("Your BMI is: " + result);
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
        processRequest(request, response);
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


    private void sendMessage(BmiRecord messageData) {

        context.createProducer().send(bmi, messageData);
        System.out.println("Message sent");
    }

}
