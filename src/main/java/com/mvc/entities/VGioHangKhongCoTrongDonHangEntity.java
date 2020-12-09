package com.mvc.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "v_GioHangKhongCoTrongDonHang", schema = "dbo", catalog = "UNIFOOD")
public class VGioHangKhongCoTrongDonHangEntity {
    private int maGio;
    private String maNguoiDung;
    private String maSanPham;
    private Integer soLuong;

    @Basic
    @Column(name = "MaGio")
    public int getMaGio() {
        return maGio;
    }

    public void setMaGio(int maGio) {
        this.maGio = maGio;
    }

    @Basic
    @Column(name = "MaNguoiDung")
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    @Basic
    @Column(name = "MaSanPham")
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Basic
    @Column(name = "SoLuong")
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VGioHangKhongCoTrongDonHangEntity that = (VGioHangKhongCoTrongDonHangEntity) o;

        if (maGio != that.maGio) return false;
        if (maNguoiDung != null ? !maNguoiDung.equals(that.maNguoiDung) : that.maNguoiDung != null) return false;
        if (maSanPham != null ? !maSanPham.equals(that.maSanPham) : that.maSanPham != null) return false;
        if (soLuong != null ? !soLuong.equals(that.soLuong) : that.soLuong != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGio;
        result = 31 * result + (maNguoiDung != null ? maNguoiDung.hashCode() : 0);
        result = 31 * result + (maSanPham != null ? maSanPham.hashCode() : 0);
        result = 31 * result + (soLuong != null ? soLuong.hashCode() : 0);
        return result;
    }
}
