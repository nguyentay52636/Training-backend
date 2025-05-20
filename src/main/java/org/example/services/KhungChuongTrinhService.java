package org.example.services;

import org.example.models.KhungChuongTrinh;
import org.example.models.ThongTinChung;
import org.example.repositories.KhungChuongTrinhRepository;
import org.example.repositories.ThongTinChungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KhungChuongTrinhService {
    @Autowired
    private KhungChuongTrinhRepository khungChuongTrinhRepository;

    @Autowired
    private ThongTinChungRepository thongTinChungRepository;

    @Autowired
    private ThongTinChungService thongTinChungService;

    @Transactional
    public KhungChuongTrinh themKhungChuongTrinh(KhungChuongTrinh khungChuongTrinh) {
        if (khungChuongTrinh.getIdThongTin() == null) {
            throw new IllegalArgumentException("ID thông tin không được để trống");
        }
        return khungChuongTrinhRepository.save(khungChuongTrinh);
    }

    @Transactional
    public KhungChuongTrinh capNhatKhungChuongTrinh(KhungChuongTrinh khungChuongTrinh) {
        if (khungChuongTrinh.getId() == null) {
            throw new IllegalArgumentException("ID khung chương trình không được để trống");
        }
        if (!khungChuongTrinhRepository.existsById(khungChuongTrinh.getId())) {
            throw new IllegalArgumentException("Không tìm thấy khung chương trình với ID: " + khungChuongTrinh.getId());
        }
        if (khungChuongTrinh.getIdThongTin() == null) {
            throw new IllegalArgumentException("ID thông tin không được để trống");
        }
        return khungChuongTrinhRepository.save(khungChuongTrinh);
    }

    @Transactional
    public void xoaKhungChuongTrinh(Integer id) {
        if (!khungChuongTrinhRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy khung chương trình với ID: " + id);
        }
        
        KhungChuongTrinh khungChuongTrinh = khungChuongTrinhRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khung chương trình với ID: " + id));
            
        if (khungChuongTrinh.getIdThongTin() != null && thongTinChungRepository.existsById(khungChuongTrinh.getIdThongTin())) {
            throw new IllegalArgumentException("Vui lòng xóa thông tin chung trước khi xóa khung chương trình");
        }
        
        khungChuongTrinhRepository.deleteById(id);
    }

    public List<KhungChuongTrinh> layTatCaKhungChuongTrinh() {
        List<KhungChuongTrinh> khungChuongTrinhs = khungChuongTrinhRepository.findAll();
        khungChuongTrinhs.forEach(this::loadThongTinChung);
        return khungChuongTrinhs;
    }

    public KhungChuongTrinh layKhungChuongTrinhById(Integer id) {
        KhungChuongTrinh khungChuongTrinh = khungChuongTrinhRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khung chương trình với ID: " + id));
        loadThongTinChung(khungChuongTrinh);
        return khungChuongTrinh;
    }

    private void loadThongTinChung(KhungChuongTrinh khungChuongTrinh) {
        if (khungChuongTrinh.getIdThongTin() != null) {
            ThongTinChung thongTinChung = thongTinChungService.layThongTinChungById(khungChuongTrinh.getIdThongTin());
            khungChuongTrinh.setThongTinChung(List.of(thongTinChung));
        }
    }
} 