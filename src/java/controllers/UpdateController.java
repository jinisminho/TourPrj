/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.HoangBean;
import dtos.UserDTO;
import dtos.ValidationFailObj;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hoang Pham
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchUsernameController";
    private static final String INVALID = "edit.jsp";

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
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String fullname = request.getParameter("txtFullname");
            String dob = request.getParameter("txtDob");
            String stt = request.getParameter("txtStt");

            //validations
            boolean valid = true;
            ValidationFailObj errorObj = new ValidationFailObj();
            if (!fullname.matches("[a-zA-Z\\s]{1,50}")) {
                errorObj.setFullnameErr("Fullname cannot be empty or contain any number");
                valid = false;
            }
            if (stt.isEmpty()) {
                valid = false;
                errorObj.setSttErr("What are you doing? :)");
            }
            try {
                if (errorObj.checkDob(dob) == false) {
                    valid = false;
                    errorObj.setDobErr("You must be over 18 to create an account on this website ;)");
                }
            } catch (Exception e) {
                errorObj.setDobErr("Invalid date format");
                valid = false;
            }

            UserDTO dto = new UserDTO(username, fullname, dob, stt);
            if (valid) {
                HoangBean bean = new HoangBean();
                bean.setUserDto(dto);
                if (bean.updateUser() == true) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Update User Failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
                request.setAttribute("DTO", dto);
            }
        } catch (Exception e) {
            log("ERROR at UpdateController: " + e.getMessage());
            request.setAttribute("ERROR", "ERROR at UpdateController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

}
