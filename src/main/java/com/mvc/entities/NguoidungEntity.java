package com.mvc.entities;

import javax.persistence.*;
import java.sql.Date;

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
    private String matKhau;

    public NguoidungEntity()
    {

    }

    public NguoidungEntity(String userID, String fullName, String gender, Date birthdate, String address, String phone, String email, String password)
    {
        this.maNguoiDung = userID;
        this.hoVaTen = fullName;
        this.gioiTinh = gender;
        this.ngaySinh = birthdate;
        this.diaChi = address;
        this.dienThoai = phone;
        this.email = email;
        this.matKhau = password;
    }

    @Id
    @Column(name = "MaNguoiDung", nullable = false, length = 9)
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
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
    @Column(name = "GioiTinh", nullable = true, length = 3)
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Basic
    @Column(name = "NgaySinh", nullable = true)
    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    @Basic
    @Column(name = "Email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "MatKhau", nullable = true, length = 50)
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
        result = 31 * result + (matKhau != null ? matKhau.hashCode() : 0);
        return result;
    }
}
