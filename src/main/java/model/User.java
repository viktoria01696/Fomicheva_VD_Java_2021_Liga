package model;

import lombok.*;
import model.service.OnlineShop;
import model.service.Shop;

import java.util.Date;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class User {

    private String ID;
    private String firstname;
    private String lastname;
    private String city;
    private Date dayOfBirth;
    private String email;
    private String password;
    private Basket basket;
    private Order order;
    private static Integer userCounter = 0;

    public User(String firstname, String lastname, String city, String email, String password, Shop shop){
        this.ID = String.valueOf(userCounter++);
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.email = email;
        this.password = password;
        this.basket = new Basket(this, shop);
    }

    public Order makeOrder(){
        this.order = this.basket.becomeAnOrder();
        return this.order;
    }




}
