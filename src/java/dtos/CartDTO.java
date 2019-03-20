/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Hoang Pham
 */
public class CartDTO {

    UserDTO user;
    TourDTO tour;

    public CartDTO(UserDTO user, TourDTO tour) {
        this.user = user;
        this.tour = tour;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TourDTO getTour() {
        return tour;
    }

    public void setTour(TourDTO tour) {
        this.tour = tour;
    }

}
