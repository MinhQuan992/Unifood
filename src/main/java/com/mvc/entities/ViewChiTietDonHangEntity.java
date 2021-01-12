package com.mvc.entities;

import com.mvc.compositeKey.ChiTietDonHangID;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(ChiTietDonHangID.class)
@Table(name = "view_ChiTietDonHang", schema = "dbo", catalog = "UNIFOOD")
public class ViewChiTietDonHangEntity {
    private int maDon;
    private String maSanPham;
    private String tenSanPham;
    private Integer soLuong;
    private Integer donGia;
    private String ghiChu;
    private String anhMinhHoa;

    @Id
    @Column(name = "MaDon")
    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    @Id
    @Column(name = "MaSanPham")
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Basic
    @Column(name = "TenSanPham")
    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    @Basic
    @Column(name = "SoLuong")
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Basic
    @Column(name = "DonGia")
    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    @Basic
    @Column(name = "GhiChu")
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Basic
    @Column(name = "AnhMinhHoa")
    public String getAnhMinhHoa() {
        return anhMinhHoa;
    }

    public void setAnhMinhHoa(String anhMinhHoa) {
        this.anhMinhHoa = anhMinhHoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewChiTietDonHangEntity that = (ViewChiTietDonHangEntity) o;

        if (maDon != that.maDon) return false;
        if (!Objects.equals(maSanPham, that.maSanPham)) return false;
        if (!Objects.equals(tenSanPham, that.tenSanPham)) return false;
        if (!Objects.equals(soLuong, that.soLuong)) return false;
        if (!Objects.equals(donGia, that.donGia)) return false;
        if (!Objects.equals(ghiChu, that.ghiChu)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maDon;
        result = 31 * result + (maSanPham != null ? maSanPham.hashCode() : 0);
        result = 31 * result + (tenSanPham != null ? tenSanPham.hashCode() : 0);
        result = 31 * result + (soLuong != null ? soLuong.hashCode() : 0);
        result = 31 * result + (donGia != null ? donGia.hashCode() : 0);
        result = 31 * result + (ghiChu != null ? ghiChu.hashCode() : 0);
        return result;
    }
}