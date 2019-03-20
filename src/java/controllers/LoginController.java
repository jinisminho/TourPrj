/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.HoangBean;
import dtos.ValidationFailObj;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hoang Pham
 */
public class LoginController extends HttpServlet {

    static private final String ERROR = "error.jsp";
    static private final String ADMIN = "admin.jsp";
    static private final String USER = "PrintTourListController";
    static private final String INVALID = "login.jsp";

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
            String password = request.getParameter("txtPassword");

            //validate
            ValidationFailObj errorObj = new ValidationFailObj();
            boolean valid = true;
            if (username.isEmpty()) {
                errorObj.setUsernameErr("Username can't be blank");
                valid = false;
            }
            if (password.isEmpty()) {
                errorObj.setPassErr("Password can't be blannk");
                valid = false;
            }

            if (valid) {
                HoangBean bean = new HoangBean();
                bean.setUsername(username);
                bean.setPassword(password);
                String role = bean.checkLogin();
                if (role.equals("failed")) {
                    errorObj.setInvalidLogin("Invalid username or password");
                    request.setAttribute("INVALID", errorObj);
                    url = INVALID;
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", username);
                    if (role.equalsIgnoreCase("admin")) {
                        url = ADMIN;
                    } else if (role.equalsIgnoreCase("user")) {
                        request.setAttribute("reIndex", "index.jsp");
                        url = USER;
                    } else {
                        request.setAttribute("ERROR", "Your role is not supported");
                    }
                }
            } else {
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
            request.setAttribute("ERROR", "ERROR at LoginController: " + e.getMessage());
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
