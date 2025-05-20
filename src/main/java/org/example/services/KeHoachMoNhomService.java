package org.example.services;

import org.example.models.KeHoachMoNhom;
import org.example.repositories.KeHoachMoNhomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KeHoachMoNhomService {
    @Autowired
    private KeHoachMoNhomRepository keHoachMoNhomRepository;

    @Transactional
    public KeHoachMoNhom themKeHoachMoNhom(KeHoachMoNhom keHoachMoNhom) {
        validateKeHoachMoNhom(keHoachMoNhom);
        return keHoachMoNhomRepository.save(keHoachMoNhom);
    }

    @Transactional
    public KeHoachMoNhom capNhatKeHoachMoNhom(KeHoachMoNhom keHoachMoNhom) {
        if (keHoachMoNhom.getId() == null) {
            throw new IllegalArgumentException("ID kế hoạch mở nhóm không được để trống");
        }
        if (!keHoachMoNhomRepository.existsById(keHoachMoNhom.getId())) {
            throw new IllegalArgumentException("Không tìm thấy kế hoạch mở nhóm với ID: " + keHoachMoNhom.getId());
        }
        validateKeHoachMoNhom(keHoachMoNhom);
        return keHoachMoNhomRepository.save(keHoachMoNhom);
    }

    private void validateKeHoachMoNhom(KeHoachMoNhom keHoachMoNhom) {
        if (keHoachMoNhom.getNamHoc() == null || keHoachMoNhom.getNamHoc().trim().isEmpty()) {
            throw new IllegalArgumentException("Năm học không được để trống");
        }
        if (keHoachMoNhom.getSoNhom() == null) {
            throw new IllegalArgumentException("Số nhóm không được để trống");
        }
        if (keHoachMoNhom.getIdHocPhan() == null) {
            throw new IllegalArgumentException("ID học phần không được để trống");
        }
        if (keHoachMoNhom.getHocKy() == null) {
            throw new IllegalArgumentException("Học kỳ không được để trống");
        }
        if (keHoachMoNhom.getSoLuongSinhVien() == null) {
            throw new IllegalArgumentException("Số lượng sinh viên không được để trống");
        }
    }

    @Transactional
    public void xoaKeHoachMoNhom(Integer id) {
        if (!keHoachMoNhomRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy kế hoạch mở nhóm với ID: " + id);
        }
        keHoachMoNhomRepository.deleteById(id);
    }

    public List<KeHoachMoNhom> layTatCaKeHoachMoNhom() {
        return keHoachMoNhomRepository.findAll();
    }

    public KeHoachMoNhom layKeHoachMoNhomById(Integer id) {
        return keHoachMoNhomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kế hoạch mở nhóm với ID: " + id));
    }
} 