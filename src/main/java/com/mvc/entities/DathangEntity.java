package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "DATHANG", schema = "dbo", catalog = "UNIFOOD")
@IdClass(DathangEntityPK.class)
public class DathangEntity {
    private int maGio;
    private String maSanPham;
    private Integer soLuong;
    private Integer donGia;
    private String ghiChu;

    public DathangEntity()
    {

    }

    public DathangEntity(int maGio, String maSanPham) {
        this.maGio = maGio;
        this.maSanPham = maSanPham;
    }

    @Id
    @Column(name = "MaGio", nullable = false)
    public int getMaGio() {
        return maGio;
    }

    public void setMaGio(int maGio) {
        this.maGio = maGio;
    }

    @Id
    @Column(name = "MaSanPham", nullable = false, length = 10)
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Basic
    @Column(name = "SoLuong", nullable = true)
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Basic
    @Column(name = "DonGia", nullable = true)
    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    @Basic
    @Column(name = "GhiChu", nullable = true, length = 200)
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DathangEntity that = (DathangEntity) o;

        if (maGio != that.maGio) return false;
        if (maSanPham != null ? !maSanPham.equals(that.maSanPham) : that.maSanPham != null) return false;
        if (soLuong != null ? !soLuong.equals(that.soLuong) : that.soLuong != null) return false;
        if (donGia != null ? !donGia.equals(that.donGia) : that.donGia != null) return false;
        if (ghiChu != null ? !ghiChu.equals(that.ghiChu) : that.ghiChu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGio;
        result = 31 * result + (maSanPham != null ? maSanPham.hashCode() : 0);
        result = 31 * result + (soLuong != null ? soLuong.hashCode() : 0);
        result = 31 * result + (donGia != null ? donGia.hashCode() : 0);
        result = 31 * result + (ghiChu != null ? ghiChu.hashCode() : 0);
        return result;
    }
}
