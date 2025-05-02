package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "ctdt_decuongchitiet")
public class DeCuongChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Mục tiêu không được để trống")
    @Column(name = "mucTieu", nullable = false, columnDefinition = "TEXT")
    private String mucTieu;

    @NotNull(message = "ID học phần không được để trống")
    @Column(name = "idHocPhan", nullable = false)
    private Integer idHocPhan;

    @Transient
    private HocPhan hocPhan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
    }

    public Integer getIdHocPhan() {
        return idHocPhan;
    }

    public void setIdHocPhan(Integer idHocPhan) {
        this.idHocPhan = idHocPhan;
    }

    public HocPhan getHocPhan() {
        return hocPhan;
    } 

    public void setHocPhan(HocPhan hocPhan) {
        this.hocPhan = hocPhan;
    }
    
}
