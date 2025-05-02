package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "ctdt_thongtinchung")
public class ThongTinChung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Tên chương trình không được để trống")
    @Size(max = 200, message = "Tên chương trình không được vượt quá 200 ký tự")
    @Column(name = "tenChuongTrinh", nullable = false, length = 200)
    private String tenChuongTrinh;

    @NotBlank(message = "Bậc không được để trống")
    @Column(name = "bac", nullable = false)
    private String bac;

    @NotBlank(message = "Loại bằng không được để trống")
    @Column(name = "loaiBang", nullable = false)
    private String loaiBang;

    @NotBlank(message = "Loại hình đào tạo không được để trống")
    @Column(name = "loaiHinhDaoTao", nullable = false)
    private String loaiHinhDaoTao;

    @NotBlank(message = "Thời gian không được để trống")
    @Column(name = "thoiGian", nullable = false)
    private String thoiGian;

    @NotNull(message = "Số tín chỉ không được để trống")
    @Column(name = "soTinChi", nullable = false)
    private Integer soTinChi;

    @NotBlank(message = "Khoa quản lý không được để trống")
    @Column(name = "khoaQuanLy", nullable = false)
    private String khoaQuanLy;

    @NotBlank(message = "Ngôn ngữ không được để trống")
    @Column(name = "ngonNgu", nullable = false)
    private String ngonNgu;

    @NotBlank(message = "Khóa tuyển không được để trống")
    @Column(name = "khoaTuyen", nullable = false)
    private String khoaTuyen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenChuongTrinh() {
        return tenChuongTrinh;
    }

    public void setTenChuongTrinh(String tenChuongTrinh) {
        this.tenChuongTrinh = tenChuongTrinh;
    }

    public String getBac() {
        return bac;
    }

    public void setBac(String bac) {
        this.bac = bac;
    }

    public String getLoaiBang() {
        return loaiBang;
    }

    public void setLoaiBang(String loaiBang) {
        this.loaiBang = loaiBang;
    }

    public String getLoaiHinhDaoTao() {
        return loaiHinhDaoTao;
    }

    public void setLoaiHinhDaoTao(String loaiHinhDaoTao) {
        this.loaiHinhDaoTao = loaiHinhDaoTao;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Integer getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getKhoaQuanLy() {
        return khoaQuanLy;
    }

    public void setKhoaQuanLy(String khoaQuanLy) {
        this.khoaQuanLy = khoaQuanLy;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public String getKhoaTuyen() {
        return khoaTuyen;
    }

    public void setKhoaTuyen(String khoaTuyen) {
        this.khoaTuyen = khoaTuyen;
    }
}
