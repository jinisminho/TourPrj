/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hoang Pham
 */
public class MainController extends HttpServlet {

    static private final String EDIT = "EditController";
    static private final String UPDATE = "UpdateController";
    static private final String ERROR = "error.jsp";
    static private final String LOGIN = "LoginController";
    static private final String USERLIST = "PrintUserListController";
    static private final String DELETE = "DeleteController";
    static private final String TOUR_LIST = "PrintTourListController";
    static private final String TOUR_DEL = "DeleteTourController";
    static private final String TOUR_EDIT = "EditTourController";
    static private final String TOUR_UPDATE = "UpdateTourController";
    static private final String USER_ADD = "insertUser.jsp";
    static private final String USER_INSERT = "InsertUserController";
    static private final String TOUR_INSERT = "InsertTourController";
    static private final String TOUR_ADD = "insertTour.jsp";
    static private final String TOUR_SEARCH_NAME = "SearchTourNameController";
    static private final String USERNAME_SEARCH = "SearchUsernameController";
    static private final String CART_ADD = "AddToCartController";
    static private final String CART_PRINT = "PrintCartController";
    static private final String CART_UPDATE = "UpdateCartController";
    static private final String BUY = "BuyController";
    static private final String CART_REMOVE_ITEM = "RemoveFromCartController";
    static private final String LOGOUT = "LogoutController";
    static private final String HISTORY = "ShowHistoryController";
    static private final String INDEX = "PrintIndexController";
    static private final String CANCEL = "CancelController";
    static private final String PROFILE = "ViewProfileController";
    static private final String PROFILE_UPDATE = "UpdateProfileController";
    static private final String PASS_CHANGE = "ChangePasswordController";
    static private final String ITEM_INFO = "ViewTourInfoController";
    static private final String INDEX_SEARCH = "IndexSearchTourNameController";
    static private final String PHOTO_FOLDER = "OpenPhotoFolderController";

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
            String action = request.getParameter("action");
            System.out.println(action);
            if (action.equals("Login")) {
                url = LOGIN;
            }
            if (action.equals("User List")) {
                url = USERLIST;
            }
            if (action.equals("Delete")) {
                url = DELETE;
            }
            if (action.equals("Edit")) {
                url = EDIT;
            }
            if (action.equals("Update")) {
                url = UPDATE;
            }
            if (action.equals("Tour List") || action.equals("PrintTour")) {
                url = TOUR_LIST;
            }
            if (action.equals("DeleteTour")) {
                url = TOUR_DEL;
            }
            if (action.equals("EditTour")) {
                url = TOUR_EDIT;
            }
            if (action.equals("Update Tour")) {
                url = TOUR_UPDATE;
            }
            if (action.equals("AddUser")) {
                url = USER_ADD;
            }
            if (action.equals("Insert User")) {
                url = USER_INSERT;
            }
            if (action.equals("AddTour")) {
                url = TOUR_ADD;
            }
            if (action.equals("Insert Tour")) {
                url = TOUR_INSERT;
            }
            if (action.equals("Search Tour")) {
                url = TOUR_SEARCH_NAME;
            }
            if (action.equals("Search User")) {
                url = USERNAME_SEARCH;
            }
            if (action.equals("Add to Cart")) {
                url = CART_ADD;
            }
            if (action.equals("Print Cart")) {
                url = CART_PRINT;
            }
            if (action.equals("Update Cart")) {
                url = CART_UPDATE;
            }
            if (action.equals("Buy")) {
                url = BUY;
            }
            if (action.equals("RemoveFromCart")) {
                url = CART_REMOVE_ITEM;
            }
            if (action.equals("Logout")) {
                url = LOGOUT;
            }
            if (action.equals("ShowHistory")) {
                url = HISTORY;
            }
            if (action.equals("PrintIndex")) {
                url = INDEX;
            }
            if (action.equals("Cancel Order")) {
                url = CANCEL;
            }
            if (action.equals("ViewProfile")) {
                url = PROFILE;
            }
            if (action.equals("Update Profile")) {
                url = PROFILE_UPDATE;
            }
            if (action.equals("Change Password")) {
                url = PASS_CHANGE;
            }
            if (action.equals("ViewItem")) {
                url = ITEM_INFO;
            }
            if (action.equals("Search Tour index")) {
                url = INDEX_SEARCH;
            }
            if (action.equals("Open Photo Folder")) {
                url = PHOTO_FOLDER;
            } else {
                request.setAttribute("ERROR", "actions not permitted");
            }
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
            request.setAttribute("ERROR", "ERROR at MainController: " + e.getMessage());
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
