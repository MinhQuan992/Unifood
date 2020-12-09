package com.mvc.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "KHOHANG", schema = "dbo", catalog = "UNIFOOD")
public class KhohangEntity {
    private String maKho;
    private String tenKho;
    private String diaChi;
    private Collection<SanphamEntity> sanphamsByMaKho;

    @Id
    @Column(name = "MaKho")
    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    @Basic
    @Column(name = "TenKho")
    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    @Basic
    @Column(name = "DiaChi")
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

        KhohangEntity that = (KhohangEntity) o;

        if (maKho != null ? !maKho.equals(that.maKho) : that.maKho != null) return false;
        if (tenKho != null ? !tenKho.equals(that.tenKho) : that.tenKho != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maKho != null ? maKho.hashCode() : 0;
        result = 31 * result + (tenKho != null ? tenKho.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "khohangByMaKho")
    public Collection<SanphamEntity> getSanphamsByMaKho() {
        return sanphamsByMaKho;
    }

    public void setSanphamsByMaKho(Collection<SanphamEntity> sanphamsByMaKho) {
        this.sanphamsByMaKho = sanphamsByMaKho;
    }
}
