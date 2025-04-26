package com.teknologiinformasi.restapi.book.model;

import jakarta.persistence.*;


@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String judul;
    private String pengarang;
    private String penerbit;
    
    // Constructor tanpa parameter
    public Book() {}

    // Constructor dengan parameter
    public Book(String judul, String pengarang, String penerbit) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
    }

    // Getters dan Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getPengarang() {
        return pengarang;
    }
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    public String getPenerbit() {
        return penerbit;
    }
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
}