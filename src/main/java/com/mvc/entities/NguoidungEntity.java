package com.mvc.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "NGUOIDUNG", schema = "dbo", catalog = "UNIFOOD")
public class NguoidungEntity {
    private String maNguoiDung;
    private String hoVaTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String dienThoai;
    private String email;
    private String tenDangNhap;
    private String matKhau;
    private Collection<GiohangEntity> giohangsByMaNguoiDung;

    @Id
    @Column(name = "MaNguoiDung")
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    @Basic
    @Column(name = "HoVaTen")
    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    @Basic
    @Column(name = "GioiTinh")
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Basic
    @Column(name = "NgaySinh")
    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Basic
    @Column(name = "DiaChi")
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Basic
    @Column(name = "DienThoai")
    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "TenDangNhap")
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    @Basic
    @Column(name = "MatKhau")
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NguoidungEntity that = (NguoidungEntity) o;

        if (maNguoiDung != null ? !maNguoiDung.equals(that.maNguoiDung) : that.maNguoiDung != null) return false;
        if (hoVaTen != null ? !hoVaTen.equals(that.hoVaTen) : that.hoVaTen != null) return false;
        if (gioiTinh != null ? !gioiTinh.equals(that.gioiTinh) : that.gioiTinh != null) return false;
        if (ngaySinh != null ? !ngaySinh.equals(that.ngaySinh) : that.ngaySinh != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;
        if (dienThoai != null ? !dienThoai.equals(that.dienThoai) : that.dienThoai != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (tenDangNhap != null ? !tenDangNhap.equals(that.tenDangNhap) : that.tenDangNhap != null) return false;
        if (matKhau != null ? !matKhau.equals(that.matKhau) : that.matKhau != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maNguoiDung != null ? maNguoiDung.hashCode() : 0;
        result = 31 * result + (hoVaTen != null ? hoVaTen.hashCode() : 0);
        result = 31 * result + (gioiTinh != null ? gioiTinh.hashCode() : 0);
        result = 31 * result + (ngaySinh != null ? ngaySinh.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        result = 31 * result + (dienThoai != null ? dienThoai.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tenDangNhap != null ? tenDangNhap.hashCode() : 0);
        result = 31 * result + (matKhau != null ? matKhau.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "nguoidungByMaNguoiDung")
    public Collection<GiohangEntity> getGiohangsByMaNguoiDung() {
        return giohangsByMaNguoiDung;
    }

    public void setGiohangsByMaNguoiDung(Collection<GiohangEntity> giohangsByMaNguoiDung) {
        this.giohangsByMaNguoiDung = giohangsByMaNguoiDung;
    }
}
