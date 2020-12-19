package com.mvc.compositeKey;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietDonHangID implements Serializable {
    protected int maDon;
    protected String maSanPham;

    public ChiTietDonHangID() { }

    public ChiTietDonHangID(int maDon, String maSanPham)
    {
        this.maDon = maDon;
        this.maSanPham = maSanPham;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

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

        ChiTietDonHangID that = (ChiTietDonHangID) o;

        if(!Objects.equals(maDon, that.maDon)) return false;
        if(!Objects.equals(maSanPham, that.maSanPham)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaDon(), getMaSanPham());
    }
}
