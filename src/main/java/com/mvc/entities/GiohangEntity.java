package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "GIOHANG", schema = "dbo", catalog = "UNIFOOD")
public class GiohangEntity {
    private int maGio;
    private String maNguoiDung;

    @Id
    @Column(name = "MaGio", nullable = false)
    public int getMaGio() {
        return maGio;
    }

    public void setMaGio(int maGio) {
        this.maGio = maGio;
    }

    @Basic
    @Column(name = "MaNguoiDung", nullable = true, length = 9)
    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiohangEntity that = (GiohangEntity) o;

        if (maGio != that.maGio) return false;
        if (maNguoiDung != null ? !maNguoiDung.equals(that.maNguoiDung) : that.maNguoiDung != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGio;
        result = 31 * result + (maNguoiDung != null ? maNguoiDung.hashCode() : 0);
        return result;
    }
}
