package org.example.services;

import org.example.models.PhanCongGiangDay;
import org.example.models.GiangVien;
import org.example.models.HocPhan;
import org.example.repositories.PhanCongGiangDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhanCongGiangDayService {
    @Autowired
    private PhanCongGiangDayRepository phanCongGiangDayRepository;

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private HocPhanService hocPhanService;

    @Transactional
    public PhanCongGiangDay themPhanCongGiangDay(PhanCongGiangDay phanCongGiangDay) {
        validatePhanCongGiangDay(phanCongGiangDay);
        return phanCongGiangDayRepository.save(phanCongGiangDay);
    }

    @Transactional
    public PhanCongGiangDay capNhatPhanCongGiangDay(PhanCongGiangDay phanCongGiangDay) {
        if (phanCongGiangDay.getIdPhanCong() == null) {
            throw new IllegalArgumentException("ID phân công không được để trống");
        }
        if (!phanCongGiangDayRepository.existsById(phanCongGiangDay.getIdPhanCong())) {
            throw new IllegalArgumentException("Không tìm thấy phân công giảng dạy với ID: " + phanCongGiangDay.getIdPhanCong());
        }
        validatePhanCongGiangDay(phanCongGiangDay);
        return phanCongGiangDayRepository.save(phanCongGiangDay);
    }

    @Transactional
    public void xoaPhanCongGiangDay(Integer id) {
        if (!phanCongGiangDayRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy phân công giảng dạy với ID: " + id);
        }
        phanCongGiangDayRepository.deleteById(id);
    }

    public List<PhanCongGiangDay> layTatCaPhanCongGiangDay() {
        List<PhanCongGiangDay> phanCongGiangDays = phanCongGiangDayRepository.findAll();
        phanCongGiangDays.forEach(this::loadGiangVienAndHocPhan);
        return phanCongGiangDays;
    }

    public PhanCongGiangDay layPhanCongGiangDayById(Integer id) {
        PhanCongGiangDay phanCongGiangDay = phanCongGiangDayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phân công giảng dạy với ID: " + id));
        loadGiangVienAndHocPhan(phanCongGiangDay);
        return phanCongGiangDay;
    }

    private void loadGiangVienAndHocPhan(PhanCongGiangDay phanCongGiangDay) {
        // Load GiangVien
        if (phanCongGiangDay.getIdGiangVien() != null) {
            try {
                GiangVien giangVien = giangVienService.layGiangVienById(phanCongGiangDay.getIdGiangVien());
                phanCongGiangDay.setGiangVien(giangVien);
            } catch (IllegalArgumentException e) {
                // Nếu không tìm thấy giảng viên, set giangVien là null
                phanCongGiangDay.setGiangVien(null);
            }
        }

        // Load HocPhan
        if (phanCongGiangDay.getIdHocPhan() != null) {
            try {
                HocPhan hocPhan = hocPhanService.layHocPhanTheoMa(String.valueOf(phanCongGiangDay.getIdHocPhan()));
                phanCongGiangDay.setHocPhan(hocPhan);
            } catch (RuntimeException e) {
                // Nếu không tìm thấy học phần, set hocPhan là null
                phanCongGiangDay.setHocPhan(null);
            }
        }
    }

    private void validatePhanCongGiangDay(PhanCongGiangDay phanCongGiangDay) {
        if (phanCongGiangDay.getIdGiangVien() == null) {
            throw new IllegalArgumentException("ID giảng viên không được để trống");
        }
        if (phanCongGiangDay.getIdHocPhan() == null) {
            throw new IllegalArgumentException("ID học phần không được để trống");
        }
        if (phanCongGiangDay.getHocKy() == null) {
            throw new IllegalArgumentException("Học kỳ không được để trống");
        }
        if (phanCongGiangDay.getTenMonHoc() == null || phanCongGiangDay.getTenMonHoc().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên môn học không được để trống");
        }
        if (phanCongGiangDay.getSoTietThucHien() == null) {
            throw new IllegalArgumentException("Số tiết thực hiện không được để trống");
        }
        if (phanCongGiangDay.getSoTietThucTe() == null) {
            throw new IllegalArgumentException("Số tiết thực tế không được để trống");
        }
    }
}