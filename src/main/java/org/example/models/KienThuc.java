package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "ctdt_kienthuc")
public class KienThuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKienThuc")
    private Integer idKienThuc;

    @NotBlank(message = "Tên kiến thức không được để trống")
    @Size(max = 200)
    @Column(name = "tenKienThuc")
    private String tenKienThuc;

    @NotBlank(message = "ID học phần không được để trống")
    @Column(name = "idHocPhan")
    private String idHocPhan;

    @NotBlank(message = "Loại học phần không được để trống")
    @Column(name = "loaiHocPhan")
    private String loaiHocPhan;

    @Transient
    private List<HocPhan> hocPhans;

    public String getIdHocPhan() {
        return idHocPhan;
    }

    public void setIdHocPhan(String idHocPhan) {
        this.idHocPhan = idHocPhan;
    }

    public String getTenKienThuc() {
        return tenKienThuc;
    }

    public void setTenKienThuc(String tenKienThuc) {
        this.tenKienThuc = tenKienThuc;
    }

    public String getLoaiHocPhan() {
        return loaiHocPhan;
    }

    public void setLoaiHocPhan(String loaiHocPhan) {
        this.loaiHocPhan = loaiHocPhan;
    }

    public List<HocPhan> getHocPhans() {
        return hocPhans;
    }

    public void setHocPhans(List<HocPhan> hocPhans) {
        this.hocPhans = hocPhans;
    }
} 