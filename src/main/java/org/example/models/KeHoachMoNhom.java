package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "ctdt_kehoachmonhom")
public class KeHoachMoNhom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "ID học phần không được để trống")
    @Column(name = "idHocPhan", nullable = false)
    private Integer idHocPhan;

    @NotBlank(message = "Năm học không được để trống")
    @Column(name = "namHoc", nullable = false)
    private String namHoc;

    @NotNull(message = "Số nhóm không được để trống")
    @Column(name = "soNhom", nullable = false)
    private Integer soNhom;

    @NotNull(message = "Học kỳ không được để trống")
    @Column(name = "hocKy", nullable = false)
    private Integer hocKy;

    @NotNull(message = "Số lượng sinh viên không được để trống")
    @Column(name = "soLuongSinhVien", nullable = false)
    private Integer soLuongSinhVien;

    @ManyToOne
    @JoinColumn(name = "idHocPhan", insertable = false, updatable = false)
    private HocPhan hocPhan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdHocPhan() {
        return idHocPhan;
    }

    public void setIdHocPhan(Integer idHocPhan) {
        this.idHocPhan = idHocPhan;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Integer getSoNhom() {
        return soNhom;
    }

    public void setSoNhom(Integer soNhom) {
        this.soNhom = soNhom;
    }

    public Integer getHocKy() {
        return hocKy;
    }

    public void setHocKy(Integer hocKy) {
        this.hocKy = hocKy;
    }

    public Integer getSoLuongSinhVien() {
        return soLuongSinhVien;
    }

    public void setSoLuongSinhVien(Integer soLuongSinhVien) {
        this.soLuongSinhVien = soLuongSinhVien;
    }

    public HocPhan getHocPhan() {
        return hocPhan;
    }

    public void setHocPhan(HocPhan hocPhan) {
        this.hocPhan = hocPhan;
    }
}
