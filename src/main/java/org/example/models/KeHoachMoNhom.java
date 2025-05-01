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

    @NotBlank(message = "Năm học không được để trống")
    @Column(name = "namHoc", nullable = false)
    private String namHoc;

    @NotNull(message = "Số nhóm không được để trống")
    @Column(name = "soNhom", nullable = false)
    private Integer soNhom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
