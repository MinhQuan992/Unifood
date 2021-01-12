package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "NHOMSANPHAM", schema = "dbo", catalog = "UNIFOOD")
public class NhomsanphamEntity {
    private short maNhom;
    private String tenNhom;

    @Id
    @Column(name = "MaNhom", nullable = false)
    public short getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(short maNhom) {
        this.maNhom = maNhom;
    }

    @Basic
    @Column(name = "TenNhom", nullable = true, length = 20)
    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NhomsanphamEntity that = (NhomsanphamEntity) o;

        if (maNhom != that.maNhom) return false;
        if (tenNhom != null ? !tenNhom.equals(that.tenNhom) : that.tenNhom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) maNhom;
        result = 31 * result + (tenNhom != null ? tenNhom.hashCode() : 0);
        return result;
    }
}
