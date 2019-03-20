/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.HoangBean;
import dtos.BillDTO;
import dtos.ValidationFailObj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hoang Pham
 */
public class ShowHistoryController extends HttpServlet {

    static private final String ERROR = "error.jsp";
    static private final String SUCCESS = "history.jsp";

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
            String tourId = null;
            String goDate = null;
            int days = 0;
            List<BillDTO> list = null;
            List<BillDTO> pending = new ArrayList<>();
            List<BillDTO> succesful = new ArrayList<>();
            ValidationFailObj errObj = new ValidationFailObj();
            HoangBean bean = new HoangBean();
            bean.setUsername(username);
            list = bean.viewBill();
            System.out.println("Hello");
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i).toString());
//            }
            for (BillDTO dto : list) {
                tourId = dto.getTourId();
                bean.setTourId(tourId);
                goDate = bean.getGoDate();
                days = bean.getCancelDays();
                boolean canCancel = errObj.checkCancel(goDate, days);
                System.out.println(dto.getTourId() + ": " + canCancel);
                if (canCancel) {
                    pending.add(dto);
                } else {
                    succesful.add(dto);
                }
            }
            System.out.println("Hello");
            request.setAttribute("PENDING", pending);
            request.setAttribute("SUCCESS", succesful);
            url = SUCCESS;
        } catch (Exception e) {
            log("ERROR at ShowHistoryController: " + e.getMessage());
            request.setAttribute("ERROR", "ERROR at ShowHistoryController: " + e.getMessage());
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
