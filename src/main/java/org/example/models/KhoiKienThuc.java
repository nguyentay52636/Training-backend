package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "ctdt_khoikienthuc")
public class KhoiKienThuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKhoiKienThuc")
    private Integer idKhoiKienThuc;

    @Column(name = "tenKhoiKienThuc", nullable = false, length = 150)
    private String tenKhoiKienThuc;

    @Column(name = "idKienThuc", nullable = false)
    private String idKienThuc;

    @Transient
    private List<KienThuc> danhSachKienThuc;
} 