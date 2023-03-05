package com.cg.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "not Empty")
    @Column(name = "full_name")
    private String fullName;
    @NotEmpty
    @Column(name = "email")
    private String email;
    @NotEmpty(message = "Phone is not blank")
    @Pattern(regexp = "^0[3798][0-9]{8}", message = "Phone is not valid")
    @Column(name = "phone")
    private String phone;
    @NotEmpty
    @Column(name = "address")
    private String address;


    @Column(name = "balance",precision = 12,scale = 0)
    private BigDecimal balance;

    public Customer() {
    }

    public Customer(Long id, String fullName, String email, String phone, String address, BigDecimal balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
