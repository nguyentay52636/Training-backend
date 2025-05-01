package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;


@Data
@Entity
@Table(name = "ctdt_khungchuongtrinh")
public class KhungChuongTrinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "ID thông tin không được để trống")
    @Column(name = "idThongTin", nullable = false)
    private Integer idThongTin;

    @Transient
    private List<ThongTinChung> thongTinChung;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdThongTin() {
        return idThongTin;
    }

    public void setIdThongTin(Integer idThongTin) {
        this.idThongTin = idThongTin;
    }

    public List<ThongTinChung> getThongTinChung() {
        return thongTinChung;
    }

    public void setThongTinChung(List<ThongTinChung> thongTinChung) {
        this.thongTinChung = thongTinChung;
    }

}
