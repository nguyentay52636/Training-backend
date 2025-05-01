package org.example.services;

import org.example.models.DeCuongChiTiet;
import org.example.models.HocPhan;
import org.example.repositories.DeCuongChiTietRepository;
import org.example.repositories.HocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeCuongChiTietService {
    @Autowired
    private DeCuongChiTietRepository deCuongChiTietRepository;

    @Autowired
    private HocPhanRepository hocPhanRepository;

    @Transactional
    public DeCuongChiTiet themDeCuongChiTiet(DeCuongChiTiet deCuongChiTiet) {
        if (deCuongChiTiet.getMucTieu() == null || deCuongChiTiet.getMucTieu().trim().isEmpty()) {
            throw new IllegalArgumentException("Mục tiêu không được để trống");
        }
        if (deCuongChiTiet.getIdHocPhan() == null) {
            throw new IllegalArgumentException("ID học phần không được để trống");
        }
        return deCuongChiTietRepository.save(deCuongChiTiet);
    }

    @Transactional
    public DeCuongChiTiet capNhatDeCuongChiTiet(DeCuongChiTiet deCuongChiTiet) {
        if (deCuongChiTiet.getId() == null) {
            throw new IllegalArgumentException("ID đề cương chi tiết không được để trống");
        }
        if (!deCuongChiTietRepository.existsById(deCuongChiTiet.getId())) {
            throw new IllegalArgumentException("Không tìm thấy đề cương chi tiết với ID: " + deCuongChiTiet.getId());
        }
        if (deCuongChiTiet.getMucTieu() == null || deCuongChiTiet.getMucTieu().trim().isEmpty()) {
            throw new IllegalArgumentException("Mục tiêu không được để trống");
        }
        if (deCuongChiTiet.getIdHocPhan() == null) {
            throw new IllegalArgumentException("ID học phần không được để trống");
        }
        return deCuongChiTietRepository.save(deCuongChiTiet);
    }

    @Transactional
    public void xoaDeCuongChiTiet(Integer id) {
        if (!deCuongChiTietRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy đề cương chi tiết với ID: " + id);
        }
        deCuongChiTietRepository.deleteById(id);
    }

    public List<DeCuongChiTiet> layTatCaDeCuongChiTiet() {
        List<DeCuongChiTiet> deCuongChiTiets = deCuongChiTietRepository.findAll();
        deCuongChiTiets.forEach(this::loadHocPhan);
        return deCuongChiTiets;
    }

    public DeCuongChiTiet layDeCuongChiTietById(Integer id) {
        DeCuongChiTiet deCuongChiTiet = deCuongChiTietRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đề cương chi tiết với ID: " + id));
        loadHocPhan(deCuongChiTiet);
        return deCuongChiTiet;
    }

    private void loadHocPhan(DeCuongChiTiet deCuongChiTiet) {
        if (deCuongChiTiet.getIdHocPhan() != null) {
            HocPhan hocPhan = hocPhanRepository.findById(deCuongChiTiet.getIdHocPhan())
                    .orElse(null);
            deCuongChiTiet.setHocPhan(hocPhan);
        }
    }
} 