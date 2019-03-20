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
public class ChangePasswordController extends HttpServlet {

    static private final String ERROR = "error.jsp";
    static private final String INVALID = "profile.jsp";
    static private final String SUCCESS = "profile.jsp";

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
            String pass = request.getParameter("txtOldPassword");
            String newPass = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            HoangBean bean = new HoangBean();
            bean.setUsername(username);
            String oldPass = bean.getUserPassword();
            ValidationFailObj errObj = new ValidationFailObj();
            UserDTO dto = new UserDTO(username, newPass, fullname, dob, "active");
            bean.setUserDto(dto);
            boolean valid = true;

            if (!pass.equals(oldPass)) {
                valid = false;
                errObj.setOldPassMismatched("Invalid password. Please check your old password");
            } else {
                if (newPass == null) {
                    valid = false;
                    errObj.setPassErr("Password cannot be empty");
                }
                if (!confirm.equals(newPass)) {
                    valid = false;
                    errObj.setPassMismatched("Confirm doesn't match password");
                }
            }

            if (valid) {
                System.out.println(username);
                if (bean.updatePassword() == true) {
                    request.setAttribute("PASSWORD", "Change password successfully!");
                    url = SUCCESS;
                } else {
                    log("ERROR at ChangePasswordController: UserDAO.updatePassword() failed");
                    request.setAttribute("ERROR", "ERROR at ChangePasswordController: UserDAO.updatePassword() failed");
                }
            } else {
                request.setAttribute("INVALID", errObj);
                url = INVALID;
            }
            request.setAttribute("USER", dto);

        } catch (Exception e) {
            log("ERROR ChangePasswordController: " + e.getMessage());
            request.setAttribute("ERROR", "ERROR ChangePasswordController: " + e.getMessage());
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
