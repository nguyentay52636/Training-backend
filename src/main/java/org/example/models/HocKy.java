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
    @Column(name = "idHocKy")
    private Integer idHocKy;

    @Column(name = "idHocPhan", nullable = false, columnDefinition = "JSON")
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

    public List<Integer> getIdHocPhan() {
        return idHocPhan;
    }

    public void setIdHocPhan(List<Integer> idHocPhan) {
        this.idHocPhan = idHocPhan;
    }

    public List<HocPhan> getHocPhanList() {
        return hocPhanList;
    }

    public void setHocPhanList(List<HocPhan> hocPhanList) {
        this.hocPhanList = hocPhanList;
    }
} 