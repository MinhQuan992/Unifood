package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "NHOMSANPHAM", schema = "dbo", catalog = "UNIFOOD")
public class NhomsanphamEntity {
    private Short maNhom;
    private String tenNhom;

    @Id
    @Column(name = "MaNhom")
    public Short getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(Short maNhom) {
        this.maNhom = maNhom;
    }

    @Basic
    @Column(name = "TenNhom")
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

        if (maNhom != null ? !maNhom.equals(that.maNhom) : that.maNhom != null) return false;
        if (tenNhom != null ? !tenNhom.equals(that.tenNhom) : that.tenNhom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maNhom != null ? maNhom.hashCode() : 0;
        result = 31 * result + (tenNhom != null ? tenNhom.hashCode() : 0);
        return result;
    }
}
