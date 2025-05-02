package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ctdt_phanconggiangday")
public class PhanCongGiangDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPhanCong")
    private Integer idPhanCong;

    @NotNull(message = "ID giảng viên không được để trống")
    @Column(name = "idGiangVien", nullable = false)
    private Integer idGiangVien;

    @NotNull(message = "ID học phần không được để trống")
    @Column(name = "idHocPhan", nullable = false)
    private Integer idHocPhan;


    @NotNull(message = "Học kỳ không được để trống")
    @Column(name = "hocKy", nullable = false)
    private Integer hocKy;

    @NotBlank(message = "Tên môn học không được để trống")
    @Size(max = 150, message = "Tên môn học không được vượt quá 150 ký tự")
    @Column(name = "tenMonHoc", nullable = false, length = 150)
    private String tenMonHoc;

    @NotNull(message = "Số tiết thực hiện không được để trống")
    @Column(name = "soTietThucHien", nullable = false)
    private Integer soTietThucHien;

    @NotNull(message = "Số tiết thực tế không được để trống")
    @Column(name = "soTietThucTe", nullable = false)
    private Integer soTietThucTe;

    @Transient
    private GiangVien giangVien;

    @Transient
    private HocPhan hocPhan;
    
    
    // Getters and Setters
    public Integer getIdPhanCong() {
        return idPhanCong;
    }

    public void setIdPhanCong(Integer idPhanCong) {
        this.idPhanCong = idPhanCong;
    }

    public Integer getIdGiangVien() {
        return idGiangVien;
    }

    public void setIdGiangVien(Integer idGiangVien) {
        this.idGiangVien = idGiangVien;
    }

    public Integer getIdHocPhan() {
        return idHocPhan;
    }

    public void setIdHocPhan(Integer idHocPhan) {
        this.idHocPhan = idHocPhan;
    }


    public Integer getHocKy() {
        return hocKy;
    }

    public void setHocKy(Integer hocKy) {
        this.hocKy = hocKy;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public Integer getSoTietThucHien() {
        return soTietThucHien;
    }

    public void setSoTietThucHien(Integer soTietThucHien) {
        this.soTietThucHien = soTietThucHien;
    }

    public Integer getSoTietThucTe() {
        return soTietThucTe;
    }

    public void setSoTietThucTe(Integer soTietThucTe) {
        this.soTietThucTe = soTietThucTe;
    }

    public GiangVien getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }   

    public HocPhan getHocPhan() {
        return hocPhan;
    }   

    public void setHocPhan(HocPhan hocPhan) {
        this.hocPhan = hocPhan;
    }
    
    
} 