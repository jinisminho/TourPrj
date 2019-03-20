/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.HoangBean;
import dtos.CartDTO;
import dtos.TourDTO;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hoang Pham
 */
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "PrintTourListController";

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
            String username = request.getParameter("username");
            String tourId = request.getParameter("tourId");
            String tourName = request.getParameter("name");
            System.out.println(request.getParameter("price"));
            float price = Float.parseFloat(request.getParameter("price"));
            TourDTO tourDto = new TourDTO(tourId, tourName, 1, price);
            UserDTO userDto = new UserDTO(username);
            CartDTO dto = new CartDTO(userDto, tourDto);
            HoangBean bean = new HoangBean();
            bean.setUsername(username);
            bean.setTourId(tourId);
            bean.setCartDto(dto);
            //validation steps
            boolean valid = true;

            if (valid) {
                if (bean.checkDuplicateCart() == -1) {
                    if (bean.addToCart() == true) {
                        url = SUCCESS;
                        request.setAttribute("reIndex", "index.jsp");
                    } else {
                        request.setAttribute("ERROR", "add to cart failed");
                    }
                } else {
                    if (bean.updateCart() == true) {
                        url = SUCCESS;
                        request.setAttribute("reIndex", "index.jsp");
                    } else {
                        request.setAttribute("ERROR", "update cart failed");
                    }
                }
            }

        } catch (Exception e) {
            log("ERROR at AddToCartController: " + e.getMessage());
            request.setAttribute("ERROR", "ERROR at AddToCartController: " + e.getMessage());
            e.printStackTrace();
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
