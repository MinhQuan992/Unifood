package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "DATHANG", schema = "dbo", catalog = "UNIFOOD")
@IdClass(DathangEntityPK.class)
public class DathangEntity {
    private Integer maGio;
    private String maSanPham;
    private Integer soLuong;
    private String ghiChu;

    @Id
    @Column(name = "MaGio", columnDefinition = "INT")
    public Integer getMaGio() {
        return maGio;
    }

    public void setMaGio(Integer maGio) {
        this.maGio = maGio;
    }

    @Id
    @Column(name = "MaSanPham", columnDefinition = "VARCHAR(10)")
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
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
    @Column(name = "GhiChu", columnDefinition = "NVARCHAR(200)")
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

        if (maGio != null ? !maGio.equals(that.maGio) : that.maGio != null) return false;
        if (maSanPham != null ? !maSanPham.equals(that.maSanPham) : that.maSanPham != null) return false;
        if (soLuong != null ? !soLuong.equals(that.soLuong) : that.soLuong != null) return false;
        if (ghiChu != null ? !ghiChu.equals(that.ghiChu) : that.ghiChu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGio != null ? maGio.hashCode() : 0;
        result = 31 * result + (maSanPham != null ? maSanPham.hashCode() : 0);
        result = 31 * result + (soLuong != null ? soLuong.hashCode() : 0);
        result = 31 * result + (ghiChu != null ? ghiChu.hashCode() : 0);
        return result;
    }
}
