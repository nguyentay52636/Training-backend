package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ctdt_giangvien")
public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGiangVien")
    private Integer idGiangVien;

    @Column(name = "idTaiKhoan")
    private Integer idTaiKhoan;

    @Transient
    private NguoiDung nguoiDung;

    @NotBlank(message = "Mã giảng viên không được để trống")
    @Size(max = 50, message = "Mã giảng viên không được vượt quá 50 ký tự")
    @Column(name = "maGiangVien", nullable = false, length = 50)
    private String maGiangVien;

    @NotBlank(message = "Tên giảng viên không được để trống")
    @Size(max = 150, message = "Tên giảng viên không được vượt quá 150 ký tự")
    @Column(name = "tenGiangVien", nullable = false, length = 150)
    private String tenGiangVien;

    @NotBlank(message = "Chức danh không được để trống")
    @Size(max = 150, message = "Chức danh không được vượt quá 150 ký tự")
    @Column(name = "chucDanh", nullable = false, length = 150)
    private String chucDanh;

    @NotBlank(message = "Năm phong không được để trống")
    @Size(max = 50, message = "Năm phong không được vượt quá 50 ký tự")
    @Column(name = "namPhong", nullable = false, length = 50)
    private String namPhong;

    @NotBlank(message = "Trình độ không được để trống")
    @Size(max = 50, message = "Trình độ không được vượt quá 50 ký tự")
    @Column(name = "trinhDo", nullable = false, length = 50)
    private String trinhDo;

    @NotBlank(message = "Nước không được để trống")
    @Size(max = 150, message = "Nước không được vượt quá 150 ký tự")
    @Column(name = "nuoc", nullable = false, length = 150)
    private String nuoc;

    @NotBlank(message = "Năm tốt nghiệp không được để trống")
    @Size(max = 150, message = "Năm tốt nghiệp không được vượt quá 150 ký tự")
    @Column(name = "namTotNghiep", nullable = false, length = 150)
    private String namTotNghiep;

    // Getters and Setters
    public Integer getIdGiangVien() {
        return idGiangVien;
    }

    public void setIdGiangVien(Integer idGiangVien) {
        this.idGiangVien = idGiangVien;
    }

    public Integer getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(Integer idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public String getNamPhong() {
        return namPhong;
    }

    public void setNamPhong(String namPhong) {
        this.namPhong = namPhong;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getNuoc() {
        return nuoc;
    }

    public void setNuoc(String nuoc) {
        this.nuoc = nuoc;
    }

    public String getNamTotNghiep() {
        return namTotNghiep;
    }

    public void setNamTotNghiep(String namTotNghiep) {
        this.namTotNghiep = namTotNghiep;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }   
} 