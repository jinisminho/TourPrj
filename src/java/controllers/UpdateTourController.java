/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.HoangBean;
import dtos.TourDTO;
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
public class UpdateTourController extends HttpServlet {
    
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchTourNameController";
    private static final String INVALID = "editTour.jsp";

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
            String id = request.getParameter("txtId");
            String name = request.getParameter("txtName");
            float price = 0;
            String desc = request.getParameter("txtDesc");
            String date = request.getParameter("txtDate");
            int cancel = 0;
            String category = request.getParameter("txtCategory");
            int qtt = 0;
            String stt = request.getParameter("txtStt");

            //validations
            boolean valid = true;
            ValidationFailObj errorObj = new ValidationFailObj();
            if (id.isEmpty()) {
                valid = false;
                errorObj.setTourIdErr("Tour ID cannot be empty");
            }
            if (name.isEmpty()) {
                valid = false;
                errorObj.setTourNameErr("Tour name cannot be empty");
            }
            try {
                price = Float.parseFloat(request.getParameter("txtPrice"));
            } catch (Exception e) {
                valid = false;
                errorObj.setPriceErr("Price must be a real number");
            }
            if (desc.isEmpty()) {
                valid = false;
                errorObj.setDescErr("Tour description cannot be empty");
            }
            if (category.isEmpty()) {
                valid = false;
                errorObj.setDescErr("Tour category cannot be empty");
            }
            if (!stt.equalsIgnoreCase("active")) {
                if (!stt.equalsIgnoreCase("inactive")) {
                    valid = false;
                    errorObj.setSttErr("What are you doing? :)");
                }
            }
            try {
                cancel = Integer.parseInt(request.getParameter("txtCancel"));
                if (cancel < 0) {
                    errorObj.setCancelErr("Cancel expriedate must be a positive number");
                    valid = false;
                }
            } catch (Exception e) {
                errorObj.setCancelErr("Cancel expiredate must be a positive number");
                valid = false;
            }
            try {
                qtt = Integer.parseInt(request.getParameter("txtQtt"));
                if (qtt < 0) {
                    errorObj.setQttErr("Tickets quantity must be a positive number");
                    valid = false;
                }
            } catch (Exception e) {
                errorObj.setCancelErr("Tickets quantity must be a positive number");
                valid = false;
            }
            try {
                if (errorObj.checkDate(date) == false) {
                    valid = false;
                    errorObj.setDateErr("Tour start date must be at least 1 day ahead");
                }
            } catch (Exception e) {
                errorObj.setDateErr("Invalid date format");
                valid = false;
            }
            
            TourDTO dto = new TourDTO(id, name, desc, date, category, stt, qtt, cancel, price);
            if (valid) {
                HoangBean bean = new HoangBean();
                bean.setTourDto(dto);
                if (bean.updateTour() == true) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Update Tour Failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("DTO", dto);
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at UpdateTourController: " + e.getMessage());
            request.setAttribute("ERROR", "ERROR at UpdateTourController: " + e.getMessage());
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
