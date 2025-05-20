package org.example.services;

import org.example.models.HocKy;
import org.example.models.HocPhan;
import org.example.repositories.HocKyRepository;
import org.example.repositories.HocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HocKyService {
    private static final Logger logger = LoggerFactory.getLogger(HocKyService.class);

    @Autowired
    private HocKyRepository hocKyRepository;

    @Autowired
    private HocPhanRepository hocPhanRepository;

    // Lấy tất cả học kỳ với danh sách học phần
    public List<HocKy> layTatCaHocKy() {
        List<HocKy> hocKyList = hocKyRepository.findAll();
        logger.info("Found {} học kỳ", hocKyList.size());
        for (HocKy hocKy : hocKyList) {
            logger.info("Processing học kỳ: {}", hocKy);
            mapHocPhanToHocKy(hocKy);
        }
        return hocKyList;
    }

    // Lấy học kỳ theo ID với danh sách học phần
    public Optional<HocKy> layHocKyTheoId(Integer id) {
        Optional<HocKy> hocKy = hocKyRepository.findById(id);
        hocKy.ifPresent(hk -> {
            logger.info("Found học kỳ: {}", hk);
            mapHocPhanToHocKy(hk);
        });
        return hocKy;
    }

    // Thêm học kỳ mới
    @Transactional
    public HocKy themHocKyMoi(String tenHocKy, List<Integer> danhSachHocPhan) {
        if (tenHocKy == null || tenHocKy.trim().isEmpty()) {
            throw new RuntimeException("Tên học kỳ không được để trống");
        }
        
        logger.info("Creating new học kỳ with tenHocKy: {} and danhSachHocPhan: {}", tenHocKy, danhSachHocPhan);
        
        HocKy hocKy = new HocKy();
        hocKy.setTenHocKy(tenHocKy.trim());
        hocKy.setIdHocPhan(danhSachHocPhan != null ? danhSachHocPhan : new ArrayList<>());
        
        hocKy = hocKyRepository.save(hocKy);
        logger.info("Saved học kỳ: {}", hocKy);
        
        mapHocPhanToHocKy(hocKy);
        return hocKy;
    }

    // Thêm học phần vào học kỳ
    @Transactional
    public HocKy themHocPhanVaoHocKy(Integer idHocKy, Integer idHocPhan) {
        HocKy hocKy = hocKyRepository.findById(idHocKy)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học kỳ"));

        logger.info("Adding học phần {} to học kỳ {}", idHocPhan, hocKy);

        List<Integer> idHocPhanList = hocKy.getIdHocPhan();
        if (!idHocPhanList.contains(idHocPhan)) {
            idHocPhanList.add(idHocPhan);
            hocKy.setIdHocPhan(idHocPhanList);
            hocKy = hocKyRepository.save(hocKy);
            logger.info("Updated học kỳ: {}", hocKy);
            mapHocPhanToHocKy(hocKy);
        }
        return hocKy;
    }

    // Xóa học phần khỏi học kỳ
    @Transactional
    public HocKy xoaHocPhanKhoiHocKy(Integer idHocKy, Integer idHocPhan) {
        HocKy hocKy = hocKyRepository.findById(idHocKy)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học kỳ"));

        logger.info("Removing học phần {} from học kỳ {}", idHocPhan, hocKy);

        List<Integer> idHocPhanList = hocKy.getIdHocPhan();
        if (idHocPhanList.remove(idHocPhan)) {
            hocKy.setIdHocPhan(idHocPhanList);
            hocKy = hocKyRepository.save(hocKy);
            logger.info("Updated học kỳ: {}", hocKy);
            mapHocPhanToHocKy(hocKy);
        }
        return hocKy;
    }

    // Xóa học kỳ
    @Transactional
    public void xoaHocKy(Integer idHocKy) {
        if (!hocKyRepository.existsById(idHocKy)) {
            throw new RuntimeException("Không tìm thấy học kỳ");
        }
        logger.info("Deleting học kỳ with id: {}", idHocKy);
        hocKyRepository.deleteById(idHocKy);
    }

    // Helper method để map danh sách học phần vào học kỳ
    private void mapHocPhanToHocKy(HocKy hocKy) {
        List<HocPhan> hocPhanList = new ArrayList<>();
        for (Integer idHocPhan : hocKy.getIdHocPhan()) {
            hocPhanRepository.findById(idHocPhan).ifPresent(hocPhanList::add);
        }
        hocKy.setHocPhanList(hocPhanList);
        logger.info("Mapped {} học phần to học kỳ {}", hocPhanList.size(), hocKy);
    }
} 