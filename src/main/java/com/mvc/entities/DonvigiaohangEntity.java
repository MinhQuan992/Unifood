package com.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "DONVIGIAOHANG", schema = "dbo", catalog = "UNIFOOD")
public class DonvigiaohangEntity {
    private String maDonVi;
    private String tenDonVi;
    private String diaChi;

    @Id
    @Column(name = "MaDonVi", nullable = false, length = 10)
    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

    @Basic
    @Column(name = "TenDonVi", nullable = true, length = 50)
    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    @Basic
    @Column(name = "DiaChi", nullable = true, length = 150)
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DonvigiaohangEntity that = (DonvigiaohangEntity) o;

        if (maDonVi != null ? !maDonVi.equals(that.maDonVi) : that.maDonVi != null) return false;
        if (tenDonVi != null ? !tenDonVi.equals(that.tenDonVi) : that.tenDonVi != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maDonVi != null ? maDonVi.hashCode() : 0;
        result = 31 * result + (tenDonVi != null ? tenDonVi.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        return result;
    }
}
