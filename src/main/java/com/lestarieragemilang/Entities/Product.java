package com.lestarieragemilang.Entities;

public class Product {
    private String harga_beli;
    private String harga_jual;
    private String id_kategori;
    private String jenis;
    private String merek;
    private String satuan;
    private String stok;
    private String ukuran;
    public String getHarga_beli() {
        return harga_beli;
    }
    public void setHarga_beli(String harga_beli) {
        this.harga_beli = harga_beli;
    }
    public String getHarga_jual() {
        return harga_jual;
    }
    public void setHarga_jual(String harga_jual) {
        this.harga_jual = harga_jual;
    }
    public String getId_kategori() {
        return id_kategori;
    }
    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }
    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public String getMerek() {
        return merek;
    }
    public void setMerek(String merek) {
        this.merek = merek;
    }
    public String getSatuan() {
        return satuan;
    }
    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
    public String getStok() {
        return stok;
    }
    public void setStok(String stok) {
        this.stok = stok;
    }
    public String getUkuran() {
        return ukuran;
    }
    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }
    public Product(String harga_beli, String harga_jual, String id_kategori, String jenis, String merek, String satuan,
            String stok, String ukuran) {
        this.harga_beli = harga_beli;
        this.harga_jual = harga_jual;
        this.id_kategori = id_kategori;
        this.jenis = jenis;
        this.merek = merek;
        this.satuan = satuan;
        this.stok = stok;
        this.ukuran = ukuran;
    }
    
}