/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import daos.BillDAO;
import daos.CartDAO;
import daos.TourDAO;
import daos.UserDAO;
import dtos.BillDTO;
import dtos.CartDTO;
import dtos.TourDTO;
import dtos.UserDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hoang Pham
 */
public class HoangBean implements Serializable {

    private UserDTO userDto;
    private TourDTO tourDto;
    private CartDTO cartDto;
    private BillDTO billDto;
    private String username, password;
    private String usernameSearch;
    private String tourSearchName;
    private String tourId;
    private String billId;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public BillDTO getBillDto() {
        return billDto;
    }

    public void setBillDto(BillDTO billDto) {
        this.billDto = billDto;
    }

    public CartDTO getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDTO cartDto) {
        this.cartDto = cartDto;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getTourSearchName() {
        return tourSearchName;
    }

    public void setTourSearchName(String tourSearchName) {
        this.tourSearchName = tourSearchName;
    }

    public HoangBean() {
    }

    public TourDTO getTourDto() {
        return tourDto;
    }

    public void setTourDto(TourDTO tourDto) {
        this.tourDto = tourDto;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameSearch() {
        return usernameSearch;
    }

    public void setUsernameSearch(String usernameSearch) {
        this.usernameSearch = usernameSearch;
    }

    public String checkLogin() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.checkLogin(username, password);
    }

    public List<UserDTO> printUserList() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.printList();
    }

    public boolean deleteUser() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.deleteUser(username);
    }

    public boolean updateUser() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.updateUser(userDto);
    }

    public List<TourDTO> printTourList() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.printTourList();
    }

    public boolean deleteTour() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.deleteTour(tourId);
    }

    public boolean updateTour() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.updateTour(tourDto);
    }

    public boolean insertUser() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.insertUser(userDto);
    }

    public boolean insertTour() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.insertTour(tourDto);
    }

    public List<TourDTO> searchTourByLikeName() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.searchTourByLikeName(tourSearchName);
    }

    public UserDTO searchUsername() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.searchUsername(username);
    }

    public int checkDuplicateCart() throws Exception {
        CartDAO dao = new CartDAO();
        return dao.checkDuplicate(username, tourId);
    }

    public boolean addToCart() throws Exception {
        CartDAO dao = new CartDAO();
        return dao.addToCart(cartDto);
    }

    public boolean updateCart() throws Exception {
        CartDAO dao = new CartDAO();
        return dao.updateCart(cartDto);
    }

    public List<CartDTO> printCart() throws Exception {
        CartDAO dao = new CartDAO();
        return dao.printCart(username);
    }

    public boolean updateCart2(int qtt) throws Exception {
        CartDAO dao = new CartDAO();
        return dao.updateCart2(username, tourId, qtt);
    }

    public int getTourQtt() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.getTourQtt(tourId);
    }

    public boolean updateTourQtt(int buyQtt) throws Exception {
        TourDAO dao = new TourDAO();
        return dao.updateTourQtt(tourId, buyQtt);
    }

    public boolean insertBill() throws Exception {
        BillDAO dao = new BillDAO();
        return dao.insertBill(billDto);
    }

    public boolean deleteCart() throws Exception {
        CartDAO dao = new CartDAO();
        return dao.deleteCart(username, tourId);
    }

    public List<BillDTO> viewBill() throws Exception {
        BillDAO dao = new BillDAO();
        return dao.viewBill(username);
    }

    public int getCancelDays() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.getCancelDays(tourId);
    }

    public String getGoDate() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.getGoDate(tourId);
    }

    public List<TourDTO> printTourListIndex() throws Exception {
        TourDAO dao = new TourDAO();
        return dao.printTourListIndex();
    }

    public boolean cancelBill() throws Exception {
        BillDAO dao = new BillDAO();
        return dao.cancelBill(billId);
    }

    public boolean returnTourQtt(int returnQtt) throws Exception {
        TourDAO dao = new TourDAO();
        return dao.returnTourQtt(tourId, returnQtt);
    }

    public UserDTO viewProfile() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.viewProfile(username);
    }

    public boolean updateProfile() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.updateProfile(userDto);
    }

    public boolean updatePassword() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.updatePassword(userDto);
    }

    public String getUserPassword() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.getPassword(username);
    }
    
    public TourDTO viewTourInfo() throws Exception{
        TourDAO dao = new TourDAO();
        return dao.viewTourInfo(tourId);
    }
}
