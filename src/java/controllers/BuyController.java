/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.HoangBean;
import dtos.BillDTO;
import dtos.TourDTO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hoang Pham
 */
public class BuyController extends HttpServlet {

    static private final String ERROR = "error.jsp";
    static private final String SUCCESS = "PrintTourListController";

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
        String username = request.getParameter("username");
        String[] idList = request.getParameterValues("ids");
        String[] nameList = request.getParameterValues("names");
        String[] qttList = request.getParameterValues("buyQtts");
        String[] priceList = request.getParameterValues("prices");
        ArrayList<TourDTO> ooolist = new ArrayList();
        TourDTO rejectedDto = null;
        boolean outOfOrder = false;
        int tmpQtt;
        try {
            HoangBean bean = new HoangBean();
            bean.setUsername(username);
            for (int i = 0; i < idList.length; i++) {
                if (Integer.parseInt(qttList[i]) != 0) {
                    bean.setTourId(idList[i]);
                    tmpQtt = bean.getTourQtt();
                    if (Integer.parseInt(qttList[i]) <= tmpQtt) {
                        BillDTO dto = new BillDTO(username, idList[i], nameList[i], "ok", LocalDate.now().toString(), Integer.parseInt(qttList[i]), Float.parseFloat(priceList[i]));
                        bean.setBillDto(dto);
                        if (bean.insertBill() == true) {
                            if (bean.updateTourQtt(Integer.parseInt(qttList[i])) == true) {
                                if (bean.deleteCart() == true) {
                                    url = SUCCESS;
                                } else {
                                    log("ERROR at BuyController: Failed to delete buyer's cart");
                                }
                            }
                        } else {
                            log("ERROR at BuyController: Failed to create Bill info");
                        }
                    } else {
                        outOfOrder = true;
                        rejectedDto = new TourDTO(idList[i], nameList[i]);
                        ooolist.add(rejectedDto);
                    }
                }
            }
            if (outOfOrder == true) {
                request.setAttribute("OOO", ooolist);
                url = ERROR;
            }
        } catch (Exception e) {
            log("ERROR at BuyController: " + e.getMessage());
            request.setAttribute("ERROR", "ERROR at BuyController: " + e.getMessage());
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
