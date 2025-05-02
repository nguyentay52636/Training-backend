package org.example.services;

import org.example.models.GiangVien;
import org.example.models.NguoiDung;
import org.example.repositories.GiangVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GiangVienService {
    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private NguoiDungServices nguoiDungServices;

    @Transactional
    public GiangVien themGiangVien(GiangVien giangVien) {
        validateGiangVien(giangVien);
        return giangVienRepository.save(giangVien);
    }

    @Transactional
    public GiangVien capNhatGiangVien(GiangVien giangVien) {
        if (giangVien.getIdGiangVien() == null) {
            throw new IllegalArgumentException("ID giảng viên không được để trống");
        }
        if (!giangVienRepository.existsById(giangVien.getIdGiangVien())) {
            throw new IllegalArgumentException("Không tìm thấy giảng viên với ID: " + giangVien.getIdGiangVien());
        }
        validateGiangVien(giangVien);
        return giangVienRepository.save(giangVien);
    }

    @Transactional
    public void xoaGiangVien(Integer id) {
        if (!giangVienRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy giảng viên với ID: " + id);
        }
        giangVienRepository.deleteById(id);
    }

    public List<GiangVien> layTatCaGiangVien() {
        List<GiangVien> giangViens = giangVienRepository.findAll();
        giangViens.forEach(this::loadNguoiDung);
        return giangViens;
    }

    public GiangVien layGiangVienById(Integer id) {
        GiangVien giangVien = giangVienRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giảng viên với ID: " + id));
        loadNguoiDung(giangVien);
        return giangVien;
    }

    private void loadNguoiDung(GiangVien giangVien) {
        if (giangVien.getIdTaiKhoan() != null) {
            try {
                NguoiDung nguoiDung = nguoiDungServices.layThongTinNguoiDung(giangVien.getIdTaiKhoan());
                giangVien.setNguoiDung(nguoiDung);
            } catch (RuntimeException e) {
                // Nếu không tìm thấy người dùng, set nguoiDung là null
                giangVien.setNguoiDung(null);
            }
        }
    }

    private void validateGiangVien(GiangVien giangVien) {
        if (giangVien.getMaGiangVien() == null || giangVien.getMaGiangVien().trim().isEmpty()) {
            throw new IllegalArgumentException("Mã giảng viên không được để trống");
        }
        if (giangVien.getTenGiangVien() == null || giangVien.getTenGiangVien().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên giảng viên không được để trống");
        }
        if (giangVien.getChucDanh() == null || giangVien.getChucDanh().trim().isEmpty()) {
            throw new IllegalArgumentException("Chức danh không được để trống");
        }
        if (giangVien.getNamPhong() == null || giangVien.getNamPhong().trim().isEmpty()) {
            throw new IllegalArgumentException("Năm phong không được để trống");
        }
        if (giangVien.getTrinhDo() == null || giangVien.getTrinhDo().trim().isEmpty()) {
            throw new IllegalArgumentException("Trình độ không được để trống");
        }
        if (giangVien.getNuoc() == null || giangVien.getNuoc().trim().isEmpty()) {
            throw new IllegalArgumentException("Nước không được để trống");
        }
        if (giangVien.getNamTotNghiep() == null || giangVien.getNamTotNghiep().trim().isEmpty()) {
            throw new IllegalArgumentException("Năm tốt nghiệp không được để trống");
        }
    }
} 