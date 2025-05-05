package org.example.services;

import org.example.models.KhoiKienThuc;
import org.example.models.KienThuc;
import org.example.models.HocPhan;
import org.example.repositories.KhoiKienThucRepository;
import org.example.repositories.KienThucRepository;
import org.example.repositories.HocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

@Service
public class KhoiKienThucService {
    @Autowired
    private KhoiKienThucRepository khoiKienThucRepository;

    @Autowired
    private KienThucRepository kienThucRepository;

    @Autowired
    private HocPhanRepository hocPhanRepository;

    @Transactional
    public KhoiKienThuc themKhoiKienThuc(KhoiKienThuc khoiKienThuc) {
        if (khoiKienThuc.getTenKhoiKienThuc() == null || khoiKienThuc.getTenKhoiKienThuc().isEmpty()) {
            throw new RuntimeException("Tên khối kiến thức không được để trống");
        }
        return khoiKienThucRepository.save(khoiKienThuc);
    }

    @Transactional
    public KhoiKienThuc capNhatKhoiKienThuc(KhoiKienThuc khoiKienThuc) {
        if (!khoiKienThucRepository.existsById(khoiKienThuc.getIdKhoiKienThuc())) {
            throw new RuntimeException("Khối kiến thức không tồn tại");
        }
        if (khoiKienThuc.getTenKhoiKienThuc() == null || khoiKienThuc.getTenKhoiKienThuc().isEmpty()) {
            throw new RuntimeException("Tên khối kiến thức không được để trống");
        }
        return khoiKienThucRepository.save(khoiKienThuc);
    }

    @Transactional
    public void xoaKhoiKienThuc(Integer id) {
        if (!khoiKienThucRepository.existsById(id)) {
            throw new RuntimeException("Khối kiến thức không tồn tại");
        }
        khoiKienThucRepository.deleteById(id);
    }

    @Transactional
    public KhoiKienThuc themKienThucVaoKhoi(Integer idKhoiKienThuc, Integer idKienThuc) {
        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById(idKhoiKienThuc)
                .orElseThrow(() -> new RuntimeException("Khối kiến thức không tồn tại"));
        
        kienThucRepository.findById(idKienThuc)
                .orElseThrow(() -> new RuntimeException("Kiến thức không tồn tại"));

        List<Integer> currentIds = khoiKienThuc.getIdKienThuc();
        if (!currentIds.contains(idKienThuc)) {
            currentIds.add(idKienThuc);
            khoiKienThuc.setIdKienThuc(currentIds);
            return khoiKienThucRepository.save(khoiKienThuc);
        } else {
            throw new RuntimeException("Kiến thức đã tồn tại trong khối");
        }
    }

    @Transactional
    public KhoiKienThuc xoaKienThucKhoiKhoi(Integer idKhoiKienThuc, Integer idKienThuc) {
        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById(idKhoiKienThuc)
                .orElseThrow(() -> new RuntimeException("Khối kiến thức không tồn tại"));
        
        List<Integer> currentIds = khoiKienThuc.getIdKienThuc();
        if (currentIds.isEmpty()) {
            throw new RuntimeException("Khối kiến thức không có kiến thức nào");
        }

        if (!currentIds.remove(idKienThuc)) {
            throw new RuntimeException("Kiến thức không tồn tại trong khối");
        }

        khoiKienThuc.setIdKienThuc(currentIds);
        return khoiKienThucRepository.save(khoiKienThuc);
    }

    @Transactional
    public KhoiKienThuc themKienThucMoiVaoKhoi(Integer idKhoiKienThuc, String tenKienThuc, String loaiHocPhan) {
        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById(idKhoiKienThuc)
                .orElseThrow(() -> new RuntimeException("Khối kiến thức không tồn tại"));

        KienThuc kienThucMoi = new KienThuc();
        kienThucMoi.setTenKienThuc(tenKienThuc);
        kienThucMoi.setLoaiHocPhan(loaiHocPhan);
        kienThucMoi.setIdHocPhan(new ArrayList<>());

        KienThuc savedKienThuc = kienThucRepository.save(kienThucMoi);

        List<Integer> currentIds = khoiKienThuc.getIdKienThuc();
        currentIds.add(savedKienThuc.getIdKienThuc());
        khoiKienThuc.setIdKienThuc(currentIds);

        return khoiKienThucRepository.save(khoiKienThuc);
    }

    public List<KhoiKienThuc> layTatCaKhoiKienThuc() {
        List<KhoiKienThuc> khoiKienThucList = khoiKienThucRepository.findAll();
        
        for (KhoiKienThuc khoiKienThuc : khoiKienThucList) {
            // Lấy danh sách KienThuc từ idKienThuc
            List<KienThuc> kienThucList = khoiKienThuc.getIdKienThuc().stream()
                .map(id -> kienThucRepository.findById(id).orElse(null))
                .filter(kienThuc -> kienThuc != null)
                .collect(Collectors.toList());
            
            // Với mỗi KienThuc, lấy thông tin chi tiết của các HocPhan
            for (KienThuc kienThuc : kienThucList) {
                List<HocPhan> hocPhanList = kienThuc.getIdHocPhan().stream()
                    .map(id -> hocPhanRepository.findById(id).orElse(null))
                    .filter(hocPhan -> hocPhan != null)
                    .collect(Collectors.toList());
                kienThuc.setHocPhanList(hocPhanList);
            }
            
            // Gán danh sách KienThuc đã có thông tin HocPhan vào khoiKienThuc
            khoiKienThuc.setKienThucList(kienThucList);
        }
        
        return khoiKienThucList;
    }

    public KhoiKienThuc layKhoiKienThucById(Integer id) {
        KhoiKienThuc khoi = khoiKienThucRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khối kiến thức với ID: " + id));

        // Lấy danh sách chi tiết các kiến thức
        List<KienThuc> kienThucList = khoi.getIdKienThuc().stream()
            .map(kienThucId -> kienThucRepository.findById(kienThucId).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        // Gán danh sách học phần cho từng kiến thức
        for (KienThuc kienThuc : kienThucList) {
            List<HocPhan> hocPhanList = kienThuc.getIdHocPhan().stream()
                .map(hocPhanId -> hocPhanRepository.findById(hocPhanId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            kienThuc.setHocPhanList(hocPhanList);
        }

        khoi.setKienThucList(kienThucList);
        return khoi;
    }

    public List<KhoiKienThuc> findByKienThucId(Integer kienThucId) {
        return khoiKienThucRepository.findByKienThucId(kienThucId);
    }
}