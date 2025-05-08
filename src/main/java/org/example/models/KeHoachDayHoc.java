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

    @Column(name = "idHocKy", nullable = false, columnDefinition = "JSON")
    @Convert(converter = ListIntegerConverter.class)
    private List<Integer> idHocKy = new ArrayList<>();

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

    public List<Integer> getIdHocKy() {
        return idHocKy;
    }

    public void setIdHocKy(List<Integer> idHocKy) {
        this.idHocKy = idHocKy;
    }

    public List<HocPhan> getHocPhanList() {
        return hocPhanList;
    }

    public void setHocPhanList(List<HocPhan> hocPhanList) {
        this.hocPhanList = hocPhanList;
    }
} 