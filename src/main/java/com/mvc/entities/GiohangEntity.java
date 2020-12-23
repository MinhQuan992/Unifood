package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "GIOHANG", schema = "dbo", catalog = "UNIFOOD")
public class GiohangEntity {
    private Integer maGio;
    private String maNguoiDung;

    @Id
    @Column(name = "MaGio", columnDefinition = "INT")
    public Integer getMaGio() {
        return maGio;
    }

    public void setMaGio(Integer maGio) {
        this.maGio = maGio;
    }

    @Basic
    @Column(name = "MaNguoiDung", columnDefinition = "VARCHAR(9)")
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

        if (maGio != null ? !maGio.equals(that.maGio) : that.maGio != null) return false;
        if (maNguoiDung != null ? !maNguoiDung.equals(that.maNguoiDung) : that.maNguoiDung != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maGio != null ? maGio.hashCode() : 0;
        result = 31 * result + (maNguoiDung != null ? maNguoiDung.hashCode() : 0);
        return result;
    }
}
