package com.mvc.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

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

        if (!Objects.equals(maNguoiDung, that.maNguoiDung)) return false;
        if (!Objects.equals(hoVaTen, that.hoVaTen)) return false;
        if (!Objects.equals(gioiTinh, that.gioiTinh)) return false;
        if (!Objects.equals(ngaySinh, that.ngaySinh)) return false;
        if (!Objects.equals(diaChi, that.diaChi)) return false;
        if (!Objects.equals(dienThoai, that.dienThoai)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(matKhau, that.matKhau)) return false;

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
