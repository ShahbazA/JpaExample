package com.hibernate;

import javax.persistence.*;

/**
 * Created by Shahbaz on 4/5/2018.
 */

@Entity(name = "UserDetails")
@Table(name="user_details")
public class UserDetails {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy="user_detail_id")
    private User user;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
