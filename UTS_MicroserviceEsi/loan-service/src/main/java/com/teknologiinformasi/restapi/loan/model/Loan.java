package com.teknologiinformasi.restapi.loan.model;

import jakarta.persistence.*;


@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idbuku;
    private String nama;
    private int quantity;
    private String status;
    
    // Constructor tanpa parameter
    public Loan() {}

    // Constructor dengan parameter
    public Loan(Long  idbuku, String nama, int quantity, String status) {
        this.idbuku = idbuku;
        this.nama = nama;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters dan Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdbuku() {
        return idbuku;
    }
    public void setIdbuku(Long idbuku) {
        this.idbuku = idbuku;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status =status;
    }
}