package com.kel_7.growbaks;

public class lapak1 {
    private Integer image;
    private String nama;
    private String jenis;
    private String rate;
    private Integer rating;
    private Integer line;

    public lapak1(Integer image, String nama, String jenis, String rate, Integer rating, Integer line) {
        this.image = image;
        this.nama = nama;
        this.jenis = jenis;
        this.rate = rate;
        this.rating = rating;
        this.line = line;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }
}
