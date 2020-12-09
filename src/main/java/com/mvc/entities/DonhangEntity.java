package com.mvc.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DONHANG", schema = "dbo", catalog = "UNIFOOD")
public class DonhangEntity {
    private int maDon;
    private Integer maGio;
    private String maGiamGia;
    private String maDonViGiaoHang;
    private String ttDonHang;
    private Boolean ttThanhToan;
    private Integer tongGiaTri;
    private Date ngayDat;
    private Date ngayGiaoHang;
    private Date ngayThanhToan;
    private GiohangEntity giohangByMaGio;
    private MagiamgiaEntity magiamgiaByMaGiamGia;
    private DonvigiaohangEntity donvigiaohangByMaDonViGiaoHang;

    @Id
    @Column(name = "MaDon")
    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    @Basic
    @Column(name = "MaGio")
    public Integer getMaGio() {
        return maGio;
    }

    public void setMaGio(Integer maGio) {
        this.maGio = maGio;
    }

    @Basic
    @Column(name = "MaGiamGia")
    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    @Basic
    @Column(name = "MaDonViGiaoHang")
    public String getMaDonViGiaoHang() {
        return maDonViGiaoHang;
    }

    public void setMaDonViGiaoHang(String maDonViGiaoHang) {
        this.maDonViGiaoHang = maDonViGiaoHang;
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

        DonhangEntity that = (DonhangEntity) o;

        if (maDon != that.maDon) return false;
        if (maGio != null ? !maGio.equals(that.maGio) : that.maGio != null) return false;
        if (maGiamGia != null ? !maGiamGia.equals(that.maGiamGia) : that.maGiamGia != null) return false;
        if (maDonViGiaoHang != null ? !maDonViGiaoHang.equals(that.maDonViGiaoHang) : that.maDonViGiaoHang != null)
            return false;
        if (ttDonHang != null ? !ttDonHang.equals(that.ttDonHang) : that.ttDonHang != null) return false;
        if (ttThanhToan != null ? !ttThanhToan.equals(that.ttThanhToan) : that.ttThanhToan != null) return false;
        if (tongGiaTri != null ? !tongGiaTri.equals(that.tongGiaTri) : that.tongGiaTri != null) return false;
        if (ngayDat != null ? !ngayDat.equals(that.ngayDat) : that.ngayDat != null) return false;
        if (ngayGiaoHang != null ? !ngayGiaoHang.equals(that.ngayGiaoHang) : that.ngayGiaoHang != null) return false;
        if (ngayThanhToan != null ? !ngayThanhToan.equals(that.ngayThanhToan) : that.ngayThanhToan != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maDon;
        result = 31 * result + (maGio != null ? maGio.hashCode() : 0);
        result = 31 * result + (maGiamGia != null ? maGiamGia.hashCode() : 0);
        result = 31 * result + (maDonViGiaoHang != null ? maDonViGiaoHang.hashCode() : 0);
        result = 31 * result + (ttDonHang != null ? ttDonHang.hashCode() : 0);
        result = 31 * result + (ttThanhToan != null ? ttThanhToan.hashCode() : 0);
        result = 31 * result + (tongGiaTri != null ? tongGiaTri.hashCode() : 0);
        result = 31 * result + (ngayDat != null ? ngayDat.hashCode() : 0);
        result = 31 * result + (ngayGiaoHang != null ? ngayGiaoHang.hashCode() : 0);
        result = 31 * result + (ngayThanhToan != null ? ngayThanhToan.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "MaGio", referencedColumnName = "MaGio")
    public GiohangEntity getGiohangByMaGio() {
        return giohangByMaGio;
    }

    public void setGiohangByMaGio(GiohangEntity giohangByMaGio) {
        this.giohangByMaGio = giohangByMaGio;
    }

    @ManyToOne
    @JoinColumn(name = "MaGiamGia", referencedColumnName = "TenMa")
    public MagiamgiaEntity getMagiamgiaByMaGiamGia() {
        return magiamgiaByMaGiamGia;
    }

    public void setMagiamgiaByMaGiamGia(MagiamgiaEntity magiamgiaByMaGiamGia) {
        this.magiamgiaByMaGiamGia = magiamgiaByMaGiamGia;
    }

    @ManyToOne
    @JoinColumn(name = "MaDonViGiaoHang", referencedColumnName = "MaDonVi")
    public DonvigiaohangEntity getDonvigiaohangByMaDonViGiaoHang() {
        return donvigiaohangByMaDonViGiaoHang;
    }

    public void setDonvigiaohangByMaDonViGiaoHang(DonvigiaohangEntity donvigiaohangByMaDonViGiaoHang) {
        this.donvigiaohangByMaDonViGiaoHang = donvigiaohangByMaDonViGiaoHang;
    }
}
