package com.mvc.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "GIOHANG", schema = "dbo", catalog = "UNIFOOD")
public class GiohangEntity {
    private int maGio;
    private String maNguoiDung;
    private Collection<DathangEntity> dathangsByMaGio;
    private Collection<DonhangEntity> donhangsByMaGio;
    private NguoidungEntity nguoidungByMaNguoiDung;

    @Id
    @Column(name = "MaGio")
    public int getMaGio() {
        return maGio;
    }

    public void setMaGio(int maGio) {
        this.maGio = maGio;
    }

    @Basic
    @Column(name = "MaNguoiDung")
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

    @OneToMany(mappedBy = "giohangByMaGio")
    public Collection<DathangEntity> getDathangsByMaGio() {
        return dathangsByMaGio;
    }

    public void setDathangsByMaGio(Collection<DathangEntity> dathangsByMaGio) {
        this.dathangsByMaGio = dathangsByMaGio;
    }

    @OneToMany(mappedBy = "giohangByMaGio")
    public Collection<DonhangEntity> getDonhangsByMaGio() {
        return donhangsByMaGio;
    }

    public void setDonhangsByMaGio(Collection<DonhangEntity> donhangsByMaGio) {
        this.donhangsByMaGio = donhangsByMaGio;
    }

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung")
    public NguoidungEntity getNguoidungByMaNguoiDung() {
        return nguoidungByMaNguoiDung;
    }

    public void setNguoidungByMaNguoiDung(NguoidungEntity nguoidungByMaNguoiDung) {
        this.nguoidungByMaNguoiDung = nguoidungByMaNguoiDung;
    }
}
