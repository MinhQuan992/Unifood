package com.mvc.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ViewAllOrder", schema = "dbo", catalog = "UNIFOOD")
public class ViewAllOrderEntity {
    private int maDon;
    private String tenNguoiDung;
    private String diaChi;
    private String phoneNumber;
    private Integer quantity;
    private String tenDonViGiaoHang;
    private Date ngayDat;
    private Date ngayGiaoHang;
    private Date ngayThanhToan;
    private Integer tongGiaTri;
    private String trangThaiDonHang;
    private String trangThaiThanhToan;

    @Id
    @Column(name = "MaDon", nullable = false)
    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    @Basic
    @Column(name = "TenNguoiDung", nullable = true, length = 50)
    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
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
    @Column(name = "PhoneNumber", nullable = true, length = 10)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "Quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "TenDonViGiaoHang", nullable = true, length = 50)
    public String getTenDonViGiaoHang() {
        return tenDonViGiaoHang;
    }

    public void setTenDonViGiaoHang(String tenDonViGiaoHang) {
        this.tenDonViGiaoHang = tenDonViGiaoHang;
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
    @Column(name = "TongGiaTri", nullable = true)
    public Integer getTongGiaTri() {
        return tongGiaTri;
    }

    public void setTongGiaTri(Integer tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }

    @Basic
    @Column(name = "TrangThaiDonHang", nullable = true, length = 50)
    public String getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(String trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    @Basic
    @Column(name = "TrangThaiThanhToan", nullable = true, length = 50)
    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewAllOrderEntity that = (ViewAllOrderEntity) o;

        if (maDon != that.maDon) return false;
        if (tenNguoiDung != null ? !tenNguoiDung.equals(that.tenNguoiDung) : that.tenNguoiDung != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (tenDonViGiaoHang != null ? !tenDonViGiaoHang.equals(that.tenDonViGiaoHang) : that.tenDonViGiaoHang != null)
            return false;
        if (ngayDat != null ? !ngayDat.equals(that.ngayDat) : that.ngayDat != null) return false;
        if (ngayGiaoHang != null ? !ngayGiaoHang.equals(that.ngayGiaoHang) : that.ngayGiaoHang != null) return false;
        if (ngayThanhToan != null ? !ngayThanhToan.equals(that.ngayThanhToan) : that.ngayThanhToan != null)
            return false;
        if (tongGiaTri != null ? !tongGiaTri.equals(that.tongGiaTri) : that.tongGiaTri != null) return false;
        if (trangThaiDonHang != null ? !trangThaiDonHang.equals(that.trangThaiDonHang) : that.trangThaiDonHang != null)
            return false;
        if (trangThaiThanhToan != null ? !trangThaiThanhToan.equals(that.trangThaiThanhToan) : that.trangThaiThanhToan != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maDon;
        result = 31 * result + (tenNguoiDung != null ? tenNguoiDung.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (tenDonViGiaoHang != null ? tenDonViGiaoHang.hashCode() : 0);
        result = 31 * result + (ngayDat != null ? ngayDat.hashCode() : 0);
        result = 31 * result + (ngayGiaoHang != null ? ngayGiaoHang.hashCode() : 0);
        result = 31 * result + (ngayThanhToan != null ? ngayThanhToan.hashCode() : 0);
        result = 31 * result + (tongGiaTri != null ? tongGiaTri.hashCode() : 0);
        result = 31 * result + (trangThaiDonHang != null ? trangThaiDonHang.hashCode() : 0);
        result = 31 * result + (trangThaiThanhToan != null ? trangThaiThanhToan.hashCode() : 0);
        return result;
    }
}
