package com.example.nisumjava.model;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name = "users")
public class NUser {

    private int userId;
    //@Column(name = "name", nullable = false)
    private String name;
//    @Column(name = "email", nullable = false)
    private String email;
//    @Column(name = "password", nullable = false)
    private String password;

    private List<Phone> phones;

    public NUser() { }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @OneToMany(targetEntity = Phone.class, fetch = FetchType.EAGER)
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}

@Entity
class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int number;
    private int cityCode;
    private int countryCode;
    @ManyToOne
//    @JoinColumn(name = "userId")
    private NUser user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public NUser getUser() {
        return user;
    }

    public void setUser(NUser user) {
        this.user = user;
    }
}
