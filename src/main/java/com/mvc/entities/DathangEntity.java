package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "DATHANG", schema = "dbo", catalog = "UNIFOOD")
@IdClass(DathangEntityPK.class)
public class DathangEntity {
    private int maGio;
    private String maSanPham;
    private Integer soLuong;
    private GiohangEntity giohangByMaGio;
    private SanphamEntity sanphamByMaSanPham;

    @Id
    @Column(name = "MaGio")
    public int getMaGio() {
        return maGio;
    }

    public void setMaGio(int maGio) {
        this.maGio = maGio;
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

        DathangEntity that = (DathangEntity) o;

        if (maGio != that.maGio) return false;
        if (maSanPham != null ? !maSanPham.equals(that.maSanPham) : that.maSanPham != null) return false;
        if (soLuong != null ? !soLuong.equals(that.soLuong) : that.soLuong != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGio;
        result = 31 * result + (maSanPham != null ? maSanPham.hashCode() : 0);
        result = 31 * result + (soLuong != null ? soLuong.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "MaGio", referencedColumnName = "MaGio", nullable = false)
    public GiohangEntity getGiohangByMaGio() {
        return giohangByMaGio;
    }

    public void setGiohangByMaGio(GiohangEntity giohangByMaGio) {
        this.giohangByMaGio = giohangByMaGio;
    }

    @ManyToOne
    @JoinColumn(name = "MaSanPham", referencedColumnName = "MaSanPham", nullable = false)
    public SanphamEntity getSanphamByMaSanPham() {
        return sanphamByMaSanPham;
    }

    public void setSanphamByMaSanPham(SanphamEntity sanphamByMaSanPham) {
        this.sanphamByMaSanPham = sanphamByMaSanPham;
    }
}
