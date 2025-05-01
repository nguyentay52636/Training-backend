package org.example.services;

import org.example.models.ThongTinChung;
import org.example.repositories.ThongTinChungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThongTinChungService {
    @Autowired
    private ThongTinChungRepository thongTinChungRepository;

    @Transactional
    public ThongTinChung themThongTinChung(ThongTinChung thongTinChung) {
        if (thongTinChung.getTenChuongTrinh() == null || thongTinChung.getTenChuongTrinh().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên chương trình không được để trống");
        }
        if (thongTinChung.getBac() == null || thongTinChung.getBac().trim().isEmpty()) {
            throw new IllegalArgumentException("Bậc không được để trống");
        }
        if (thongTinChung.getLoaiBang() == null || thongTinChung.getLoaiBang().trim().isEmpty()) {
            throw new IllegalArgumentException("Loại bằng không được để trống");
        }
        if (thongTinChung.getLoaiHinhDaoTao() == null || thongTinChung.getLoaiHinhDaoTao().trim().isEmpty()) {
            throw new IllegalArgumentException("Loại hình đào tạo không được để trống");
        }
        if (thongTinChung.getThoiGian() == null || thongTinChung.getThoiGian().trim().isEmpty()) {
            throw new IllegalArgumentException("Thời gian không được để trống");
        }
        if (thongTinChung.getSoTinChi() == null) {
            throw new IllegalArgumentException("Số tín chỉ không được để trống");
        }
        if (thongTinChung.getKhoaQuanLy() == null || thongTinChung.getKhoaQuanLy().trim().isEmpty()) {
            throw new IllegalArgumentException("Khoa quản lý không được để trống");
        }
        if (thongTinChung.getNgonNgu() == null || thongTinChung.getNgonNgu().trim().isEmpty()) {
            throw new IllegalArgumentException("Ngôn ngữ không được để trống");
        }
        if (thongTinChung.getKhoaTuyen() == null || thongTinChung.getKhoaTuyen().trim().isEmpty()) {
            throw new IllegalArgumentException("Khóa tuyển không được để trống");
        }
        return thongTinChungRepository.save(thongTinChung);
    }

    @Transactional
    public ThongTinChung capNhatThongTinChung(ThongTinChung thongTinChung) {
        if (thongTinChung.getId() == null) {
            throw new IllegalArgumentException("ID thông tin chung không được để trống");
        }
        if (!thongTinChungRepository.existsById(thongTinChung.getId())) {
            throw new IllegalArgumentException("Không tìm thấy thông tin chung với ID: " + thongTinChung.getId());
        }
        if (thongTinChung.getTenChuongTrinh() == null || thongTinChung.getTenChuongTrinh().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên chương trình không được để trống");
        }
        if (thongTinChung.getBac() == null || thongTinChung.getBac().trim().isEmpty()) {
            throw new IllegalArgumentException("Bậc không được để trống");
        }
        if (thongTinChung.getLoaiBang() == null || thongTinChung.getLoaiBang().trim().isEmpty()) {
            throw new IllegalArgumentException("Loại bằng không được để trống");
        }
        if (thongTinChung.getLoaiHinhDaoTao() == null || thongTinChung.getLoaiHinhDaoTao().trim().isEmpty()) {
            throw new IllegalArgumentException("Loại hình đào tạo không được để trống");
        }
        if (thongTinChung.getThoiGian() == null || thongTinChung.getThoiGian().trim().isEmpty()) {
            throw new IllegalArgumentException("Thời gian không được để trống");
        }
        if (thongTinChung.getSoTinChi() == null) {
            throw new IllegalArgumentException("Số tín chỉ không được để trống");
        }
        if (thongTinChung.getKhoaQuanLy() == null || thongTinChung.getKhoaQuanLy().trim().isEmpty()) {
            throw new IllegalArgumentException("Khoa quản lý không được để trống");
        }
        if (thongTinChung.getNgonNgu() == null || thongTinChung.getNgonNgu().trim().isEmpty()) {
            throw new IllegalArgumentException("Ngôn ngữ không được để trống");
        }
        if (thongTinChung.getKhoaTuyen() == null || thongTinChung.getKhoaTuyen().trim().isEmpty()) {
            throw new IllegalArgumentException("Khóa tuyển không được để trống");
        }
        return thongTinChungRepository.save(thongTinChung);
    }

    @Transactional
    public void xoaThongTinChung(Integer id) {
        if (!thongTinChungRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy thông tin chung với ID: " + id);
        }
        thongTinChungRepository.deleteById(id);
    }

    public List<ThongTinChung> layTatCaThongTinChung() {
        return thongTinChungRepository.findAll();
    }

    public ThongTinChung layThongTinChungById(Integer id) {
        return thongTinChungRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thông tin chung với ID: " + id));
    }
} 