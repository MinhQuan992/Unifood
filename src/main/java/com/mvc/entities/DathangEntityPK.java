package com.mvc.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DathangEntityPK implements Serializable {
    private int maGio;
    private String maSanPham;

    @Column(name = "MaGio", nullable = false)
    @Id
    public int getMaGio() {
        return maGio;
    }

    public void setMaGio(int maGio) {
        this.maGio = maGio;
    }

    @Column(name = "MaSanPham", nullable = false, length = 10)
    @Id
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DathangEntityPK that = (DathangEntityPK) o;

        if (maGio != that.maGio) return false;
        if (maSanPham != null ? !maSanPham.equals(that.maSanPham) : that.maSanPham != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGio;
        result = 31 * result + (maSanPham != null ? maSanPham.hashCode() : 0);
        return result;
    }
}
