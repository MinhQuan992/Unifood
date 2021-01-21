package com.mvc.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DONHANG", schema = "dbo", catalog = "UNIFOOD")
public class DonhangEntity {
    private int maDon;
    private Integer maGio;
    private String maDonViGiaoHang;
    private String ttDonHang;
    private Boolean ttThanhToan;
    private Integer tongGiaTri;
    private Date ngayDat;
    private Date ngayGiaoHang;
    private Date ngayThanhToan;
    private String hoVaTen;
    private String diaChi;
    private String dienThoai;

    @Id
    @Column(name = "MaDon", nullable = false)
    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    @Basic
    @Column(name = "MaGio", nullable = true)
    public Integer getMaGio() {
        return maGio;
    }

    public void setMaGio(Integer maGio) {
        this.maGio = maGio;
    }

    @Basic
    @Column(name = "MaDonViGiaoHang", nullable = true, length = 10)
    public String getMaDonViGiaoHang() {
        return maDonViGiaoHang;
    }

    public void setMaDonViGiaoHang(String maDonViGiaoHang) {
        this.maDonViGiaoHang = maDonViGiaoHang;
    }

    @Basic
    @Column(name = "TT_DonHang", nullable = true, length = 50)
    public String getTtDonHang() {
        return ttDonHang;
    }

    public void setTtDonHang(String ttDonHang) {
        this.ttDonHang = ttDonHang;
    }

    @Basic
    @Column(name = "TT_ThanhToan", nullable = true)
    public Boolean getTtThanhToan() {
        return ttThanhToan;
    }

    public void setTtThanhToan(Boolean ttThanhToan) {
        this.ttThanhToan = ttThanhToan;
    }

    @Basic
    @Column(name = "TongGiaTri", nullable = true)
    public Integer getTongGiaTri() {
        return tongGiaTri;
    }

    public void setTongGiaTri(Integer tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }

    @Basic
    @Column(name = "NgayDat", nullable = true)
    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    @Basic
    @Column(name = "NgayGiaoHang", nullable = true)
    public Date getNgayGiaoHang() {
        return ngayGiaoHang;
    }

    public void setNgayGiaoHang(Date ngayGiaoHang) {
        this.ngayGiaoHang = ngayGiaoHang;
    }

    @Basic
    @Column(name = "NgayThanhToan", nullable = true)
    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    @Basic
    @Column(name = "HoVaTen", nullable = true, length = 50)
    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    @Basic
    @Column(name = "DiaChi", nullable = true, length = 150)
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Basic
    @Column(name = "DienThoai", nullable = true, length = 10)
    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DonhangEntity that = (DonhangEntity) o;

        if (maDon != that.maDon) return false;
        if (maGio != null ? !maGio.equals(that.maGio) : that.maGio != null) return false;
        if (maDonViGiaoHang != null ? !maDonViGiaoHang.equals(that.maDonViGiaoHang) : that.maDonViGiaoHang != null)
            return false;
        if (ttDonHang != null ? !ttDonHang.equals(that.ttDonHang) : that.ttDonHang != null) return false;
        if (ttThanhToan != null ? !ttThanhToan.equals(that.ttThanhToan) : that.ttThanhToan != null) return false;
        if (tongGiaTri != null ? !tongGiaTri.equals(that.tongGiaTri) : that.tongGiaTri != null) return false;
        if (ngayDat != null ? !ngayDat.equals(that.ngayDat) : that.ngayDat != null) return false;
        if (ngayGiaoHang != null ? !ngayGiaoHang.equals(that.ngayGiaoHang) : that.ngayGiaoHang != null) return false;
        if (ngayThanhToan != null ? !ngayThanhToan.equals(that.ngayThanhToan) : that.ngayThanhToan != null)
            return false;
        if (hoVaTen != null ? !hoVaTen.equals(that.hoVaTen) : that.hoVaTen != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;
        if (dienThoai != null ? !dienThoai.equals(that.dienThoai) : that.dienThoai != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maDon;
        result = 31 * result + (maGio != null ? maGio.hashCode() : 0);
        result = 31 * result + (maDonViGiaoHang != null ? maDonViGiaoHang.hashCode() : 0);
        result = 31 * result + (ttDonHang != null ? ttDonHang.hashCode() : 0);
        result = 31 * result + (ttThanhToan != null ? ttThanhToan.hashCode() : 0);
        result = 31 * result + (tongGiaTri != null ? tongGiaTri.hashCode() : 0);
        result = 31 * result + (ngayDat != null ? ngayDat.hashCode() : 0);
        result = 31 * result + (ngayGiaoHang != null ? ngayGiaoHang.hashCode() : 0);
        result = 31 * result + (ngayThanhToan != null ? ngayThanhToan.hashCode() : 0);
        result = 31 * result + (hoVaTen != null ? hoVaTen.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        result = 31 * result + (dienThoai != null ? dienThoai.hashCode() : 0);
        return result;
    }
}
