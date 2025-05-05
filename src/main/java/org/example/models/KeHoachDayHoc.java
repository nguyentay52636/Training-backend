package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.utils.ListIntegerConverter;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "ctdt_kehoachdayhoc")
public class KeHoachDayHoc {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idChuyenNganh")
    private Integer idChuyenNganh;

    @NotBlank(message = "Tên chuyên ngành không được để trống")
    @Size(max = 150, message = "Tên chuyên ngành không được vượt quá 150 ký tự")
    @Column(name = "tenChuyenNganh", nullable = false, length = 150)
    private String tenChuyenNganh;

    @NotNull(message = "Học kỳ thực hiện không được để trống")
    @Column(name = "hocKyThucHien", nullable = false)
    private Integer hocKyThucHien;

    @Column(name = "idHocPhan", nullable = false, columnDefinition = "JSON")
    @Convert(converter = ListIntegerConverter.class)
    private List<Integer> idHocPhan = new ArrayList<>();

    @Transient
    private List<HocPhan> hocPhanList = new ArrayList<>();

    // Getters and Setters
    public Integer getIdChuyenNganh() {
        return idChuyenNganh;
    }

    public void setIdChuyenNganh(Integer idChuyenNganh) {
        this.idChuyenNganh = idChuyenNganh;
    }

    public String getTenChuyenNganh() {
        return tenChuyenNganh;
    }

    public void setTenChuyenNganh(String tenChuyenNganh) {
        this.tenChuyenNganh = tenChuyenNganh;
    }

    public Integer getHocKyThucHien() {
        return hocKyThucHien;
    }

    public void setHocKyThucHien(Integer hocKyThucHien) {
        this.hocKyThucHien = hocKyThucHien;
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