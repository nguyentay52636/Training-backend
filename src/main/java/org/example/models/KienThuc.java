package org.example.models;

import jakarta.persistence.*;
import org.example.utils.ListIntegerConverter;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "ctdt_kienthuc")
public class KienThuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKienThuc")
    private Integer idKienThuc;

    @Column(name = "tenKienThuc", nullable = false, length = 200)
    private String tenKienThuc;

    @Column(name = "idHocPhan", nullable = false, columnDefinition = "JSON")
    @Convert(converter = ListIntegerConverter.class)
    private List<Integer> idHocPhan = new ArrayList<>();

    @Column(name = "loaiHocPhan", length = 255)
    private String loaiHocPhan;

    @Transient
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHocPhan", referencedColumnName = "idHocPhan", insertable = false, updatable = false)
    private List<HocPhan> hocPhanList = new ArrayList<>();

    // Getters and Setters
    public Integer getIdKienThuc() {
        return idKienThuc;
    }

    public void setIdKienThuc(Integer idKienThuc) {
        this.idKienThuc = idKienThuc;
    }

    public String getTenKienThuc() {
        return tenKienThuc;
    }

    public void setTenKienThuc(String tenKienThuc) {
        this.tenKienThuc = tenKienThuc;
    }

    public List<Integer> getIdHocPhan() {
        return idHocPhan;
    }

    public void setIdHocPhan(List<Integer> idHocPhan) {
        this.idHocPhan = idHocPhan;
    }

    public String getLoaiHocPhan() {
        return loaiHocPhan;
    }

    public void setLoaiHocPhan(String loaiHocPhan) {
        this.loaiHocPhan = loaiHocPhan;
    }

    public List<HocPhan> getHocPhanList() {
        return hocPhanList;
    }

    public void setHocPhanList(List<HocPhan> hocPhanList) {
        this.hocPhanList = hocPhanList;
    }
} 