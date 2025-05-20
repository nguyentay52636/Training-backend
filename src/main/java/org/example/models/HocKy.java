package org.example.models;

import jakarta.persistence.*;
import org.example.utils.ListIntegerConverter;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "ctdt_hocky")
public class HocKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHocKy", columnDefinition = "int")
    private Integer idHocKy;

    @Column(name = "tenHocKy", nullable = false, length = 255, columnDefinition = "varchar(255)")
    private String tenHocKy;

    @Column(name = "idHocPhan", columnDefinition = "longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin")
    @Convert(converter = ListIntegerConverter.class)
    private List<Integer> idHocPhan = new ArrayList<>();

    @Transient
    private List<HocPhan> hocPhanList;

    // Getters and Setters
    public Integer getIdHocKy() {
        return idHocKy;
    }

    public void setIdHocKy(Integer idHocKy) {
        this.idHocKy = idHocKy;
    }

    public String getTenHocKy() {
        return tenHocKy;
    }

    public void setTenHocKy(String tenHocKy) {
        this.tenHocKy = tenHocKy != null ? tenHocKy.trim() : null;
    }

    public List<Integer> getIdHocPhan() {
        return idHocPhan;
    }

    public void setIdHocPhan(List<Integer> idHocPhan) {
        this.idHocPhan = idHocPhan != null ? idHocPhan : new ArrayList<>();
    }

    public List<HocPhan> getHocPhanList() {
        return hocPhanList;
    }

    public void setHocPhanList(List<HocPhan> hocPhanList) {
        this.hocPhanList = hocPhanList;
    }

    @Override
    public String toString() {
        return "HocKy{" +
                "idHocKy=" + idHocKy +
                ", tenHocKy='" + tenHocKy + '\'' +
                ", idHocPhan=" + idHocPhan +
                '}';
    }
} 