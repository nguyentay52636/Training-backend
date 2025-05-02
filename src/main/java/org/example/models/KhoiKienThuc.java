package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.utils.ListIntegerConverter;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "ctdt_khoikienthuc")
public class KhoiKienThuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKhoiKienThuc")
    private Integer idKhoiKienThuc;

    @NotBlank(message = "Tên khối kiến thức không được để trống")
    @Size(max = 150, message = "Tên khối kiến thức không được vượt quá 150 ký tự")
    @Column(name = "tenKhoiKienThuc", nullable = false, length = 150)
    private String tenKhoiKienThuc;

    @Column(name = "idKienThuc", nullable = false, columnDefinition = "JSON")
    @Convert(converter = ListIntegerConverter.class)
    private List<Integer> idKienThuc = new ArrayList<>();

    @Transient
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idKienThuc", referencedColumnName = "idKienThuc", insertable = false, updatable = false)
    private List<KienThuc> kienThucList = new ArrayList<>();

    // Getters and Setters
    public Integer getIdKhoiKienThuc() {
        return idKhoiKienThuc;
    }

    public void setIdKhoiKienThuc(Integer idKhoiKienThuc) {
        this.idKhoiKienThuc = idKhoiKienThuc;
    }

    public String getTenKhoiKienThuc() {
        return tenKhoiKienThuc;
    }

    public void setTenKhoiKienThuc(String tenKhoiKienThuc) {
        this.tenKhoiKienThuc = tenKhoiKienThuc;
    }

    public List<Integer> getIdKienThuc() {
        return idKienThuc;
    }

    public void setIdKienThuc(List<Integer> idKienThuc) {
        this.idKienThuc = idKienThuc;
    }
} 