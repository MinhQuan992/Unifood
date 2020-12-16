package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "SANPHAM", schema = "dbo", catalog = "UNIFOOD")
public class SanphamEntity {
    private String maSanPham;
    private String tenSanPham;
    private String donViTinh;
    private Integer donGia;
    private Integer soLuong;
    private String anhMinhHoa;
    private Short maNhom;
    private String maKho;
    private String moTa;

    @Id
    @Column(name = "MaSanPham", columnDefinition = "VARCHAR(10)")
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Basic
    @Column(name = "TenSanPham", columnDefinition = "NVARCHAR(100)")
    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    @Basic
    @Column(name = "DonViTinh", columnDefinition = "NVARCHAR(10)")
    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    @Basic
    @Column(name = "DonGia", columnDefinition = "INT")
    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    @Basic
    @Column(name = "SoLuong", columnDefinition = "INT")
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Basic
    @Column(name = "AnhMinhHoa", columnDefinition = "VARCHAR(100)")
    public String getAnhMinhHoa() {
        return anhMinhHoa;
    }

    public void setAnhMinhHoa(String anhMinhHoa) {
        this.anhMinhHoa = anhMinhHoa;
    }

    @Basic
    @Column(name = "MaNhom", columnDefinition = "SMALLINT")
    public Short getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(Short maNhom) {
        this.maNhom = maNhom;
    }

    @Basic
    @Column(name = "MaKho", columnDefinition = "VARCHAR(10)")
    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    @Basic
    @Column(name = "MoTa", columnDefinition = "NVARCHAR(1500)")
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SanphamEntity that = (SanphamEntity) o;

        if (maSanPham != null ? !maSanPham.equals(that.maSanPham) : that.maSanPham != null) return false;
        if (tenSanPham != null ? !tenSanPham.equals(that.tenSanPham) : that.tenSanPham != null) return false;
        if (donViTinh != null ? !donViTinh.equals(that.donViTinh) : that.donViTinh != null) return false;
        if (donGia != null ? !donGia.equals(that.donGia) : that.donGia != null) return false;
        if (soLuong != null ? !soLuong.equals(that.soLuong) : that.soLuong != null) return false;
        if (anhMinhHoa != null ? !anhMinhHoa.equals(that.anhMinhHoa) : that.anhMinhHoa != null) return false;
        if (maNhom != null ? !maNhom.equals(that.maNhom) : that.maNhom != null) return false;
        if (maKho != null ? !maKho.equals(that.maKho) : that.maKho != null) return false;
        if (moTa != null ? !moTa.equals(that.moTa) : that.moTa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maSanPham != null ? maSanPham.hashCode() : 0;
        result = 31 * result + (tenSanPham != null ? tenSanPham.hashCode() : 0);
        result = 31 * result + (donViTinh != null ? donViTinh.hashCode() : 0);
        result = 31 * result + (donGia != null ? donGia.hashCode() : 0);
        result = 31 * result + (soLuong != null ? soLuong.hashCode() : 0);
        result = 31 * result + (anhMinhHoa != null ? anhMinhHoa.hashCode() : 0);
        result = 31 * result + (maNhom != null ? maNhom.hashCode() : 0);
        result = 31 * result + (maKho != null ? maKho.hashCode() : 0);
        result = 31 * result + (moTa != null ? moTa.hashCode() : 0);
        return result;
    }
}
