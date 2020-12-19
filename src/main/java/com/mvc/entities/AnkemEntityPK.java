package com.mvc.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AnkemEntityPK implements Serializable {
    private String maMonAnChinh;
    private String maDoAnKem;

    @Column(name = "MaMonAnChinh", nullable = false, length = 10)
    @Id
    public String getMaMonAnChinh() {
        return maMonAnChinh;
    }

    public void setMaMonAnChinh(String maMonAnChinh) {
        this.maMonAnChinh = maMonAnChinh;
    }

    @Column(name = "MaDoAnKem", nullable = false, length = 10)
    @Id
    public String getMaDoAnKem() {
        return maDoAnKem;
    }

    public void setMaDoAnKem(String maDoAnKem) {
        this.maDoAnKem = maDoAnKem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnkemEntityPK that = (AnkemEntityPK) o;

        if (maMonAnChinh != null ? !maMonAnChinh.equals(that.maMonAnChinh) : that.maMonAnChinh != null) return false;
        if (maDoAnKem != null ? !maDoAnKem.equals(that.maDoAnKem) : that.maDoAnKem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maMonAnChinh != null ? maMonAnChinh.hashCode() : 0;
        result = 31 * result + (maDoAnKem != null ? maDoAnKem.hashCode() : 0);
        return result;
    }
}
