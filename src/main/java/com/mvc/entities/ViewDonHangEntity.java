package com.mvc.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "view_DonHang", schema = "dbo", catalog = "UNIFOOD")
public class ViewDonHangEntity {
    private String maNguoiDung;
    private int maDon;
    private String ttDonHang;
    private Boolean ttThanhToan;
    private Integer tongGiaTri;
    private Date ngayDat;
    private Date ngayGiaoHang;
    private Date ngayThanhToan;

    @Basic
    @Column(name = "MaNguoiDung")
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    @Id
    @Column(name = "MaDon")
    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    @Basic
    @Column(name = "TT_DonHang")
    public String getTtDonHang() {
        return ttDonHang;
    }

    public void setTtDonHang(String ttDonHang) {
        this.ttDonHang = ttDonHang;
    }

    @Basic
    @Column(name = "TT_ThanhToan")
    public Boolean getTtThanhToan() {
        return ttThanhToan;
    }

    public void setTtThanhToan(Boolean ttThanhToan) {
        this.ttThanhToan = ttThanhToan;
    }

    @Basic
    @Column(name = "TongGiaTri")
    public Integer getTongGiaTri() {
        return tongGiaTri;
    }

    public void setTongGiaTri(Integer tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }

    @Basic
    @Column(name = "NgayDat")
    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    @Basic
    @Column(name = "NgayGiaoHang")
    public Date getNgayGiaoHang() {
        return ngayGiaoHang;
    }

    public void setNgayGiaoHang(Date ngayGiaoHang) {
        this.ngayGiaoHang = ngayGiaoHang;
    }

    @Basic
    @Column(name = "NgayThanhToan")
    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewDonHangEntity that = (ViewDonHangEntity) o;

        if (maDon != that.maDon) return false;
        if (!Objects.equals(maNguoiDung, that.maNguoiDung)) return false;
        if (!Objects.equals(ttDonHang, that.ttDonHang)) return false;
        if (!Objects.equals(ttThanhToan, that.ttThanhToan)) return false;
        if (!Objects.equals(tongGiaTri, that.tongGiaTri)) return false;
        if (!Objects.equals(ngayDat, that.ngayDat)) return false;
        if (!Objects.equals(ngayGiaoHang, that.ngayGiaoHang)) return false;
        if (!Objects.equals(ngayThanhToan, that.ngayThanhToan))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maNguoiDung != null ? maNguoiDung.hashCode() : 0;
        result = 31 * result + maDon;
        result = 31 * result + (ttDonHang != null ? ttDonHang.hashCode() : 0);
        result = 31 * result + (ttThanhToan != null ? ttThanhToan.hashCode() : 0);
        result = 31 * result + (tongGiaTri != null ? tongGiaTri.hashCode() : 0);
        result = 31 * result + (ngayDat != null ? ngayDat.hashCode() : 0);
        result = 31 * result + (ngayGiaoHang != null ? ngayGiaoHang.hashCode() : 0);
        result = 31 * result + (ngayThanhToan != null ? ngayThanhToan.hashCode() : 0);
        return result;
    }
}