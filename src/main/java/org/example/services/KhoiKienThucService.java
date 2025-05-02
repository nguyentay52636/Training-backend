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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        
        KienThuc kienThuc = kienThucRepository.findById(idKienThuc)
                .orElseThrow(() -> new RuntimeException("Kiến thức không tồn tại"));

        String currentIds = khoiKienThuc.getIdKienThuc();
        if (currentIds == null || currentIds.isEmpty()) {
            khoiKienThuc.setIdKienThuc(String.valueOf(idKienThuc));
        } else {
            String[] ids = currentIds.split(",");
            for (String id : ids) {
                if (id.equals(String.valueOf(idKienThuc))) {
                    throw new RuntimeException("Kiến thức đã tồn tại trong khối");
                }
            }
            khoiKienThuc.setIdKienThuc(currentIds + "," + idKienThuc);
        }

        return khoiKienThucRepository.save(khoiKienThuc);
    }

    @Transactional
    public KhoiKienThuc xoaKienThucKhoiKhoi(Integer idKhoiKienThuc, Integer idKienThuc) {
        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById(idKhoiKienThuc)
                .orElseThrow(() -> new RuntimeException("Khối kiến thức không tồn tại"));
        
        String currentIds = khoiKienThuc.getIdKienThuc();
        if (currentIds == null || currentIds.isEmpty()) {
            throw new RuntimeException("Khối kiến thức không có kiến thức nào");
        }

        String[] ids = currentIds.split(",");
        StringBuilder newIds = new StringBuilder();
        boolean found = false;

        for (String id : ids) {
            if (!id.equals(String.valueOf(idKienThuc))) {
                if (newIds.length() > 0) {
                    newIds.append(",");
                }
                newIds.append(id);
            } else {
                found = true;
            }
        }

        if (!found) {
            throw new RuntimeException("Kiến thức không tồn tại trong khối");
        }

        khoiKienThuc.setIdKienThuc(newIds.toString());
        return khoiKienThucRepository.save(khoiKienThuc);
    }

    @Transactional
    public KhoiKienThuc themKienThucMoiVaoKhoi(Integer idKhoiKienThuc, String tenKienThuc, String loaiHocPhan) {
        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById(idKhoiKienThuc)
                .orElseThrow(() -> new RuntimeException("Khối kiến thức không tồn tại"));

        KienThuc kienThucMoi = new KienThuc();
        kienThucMoi.setTenKienThuc(tenKienThuc);
        kienThucMoi.setLoaiHocPhan(loaiHocPhan);
        kienThucMoi.setIdHocPhan("");

        KienThuc savedKienThuc = kienThucRepository.save(kienThucMoi);

        String currentIds = khoiKienThuc.getIdKienThuc();
        if (currentIds == null || currentIds.isEmpty()) {
            khoiKienThuc.setIdKienThuc(String.valueOf(savedKienThuc.getIdKienThuc()));
        } else {
            khoiKienThuc.setIdKienThuc(currentIds + "," + savedKienThuc.getIdKienThuc());
        }

        return khoiKienThucRepository.save(khoiKienThuc);
    }

    public List<KhoiKienThuc> layTatCaKhoiKienThuc() {
        List<KhoiKienThuc> khoiKienThucs = khoiKienThucRepository.findAll();
        return khoiKienThucs.stream()
                .map(this::layThongTinKienThuc)
                .collect(Collectors.toList());
    }

    public KhoiKienThuc layKhoiKienThucById(Integer id) {
        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khối kiến thức không tồn tại"));
        return layThongTinKienThuc(khoiKienThuc);
    }

    private KhoiKienThuc layThongTinKienThuc(KhoiKienThuc khoiKienThuc) {
        if (khoiKienThuc.getIdKienThuc() != null && !khoiKienThuc.getIdKienThuc().isEmpty()) {
            List<KienThuc> kienThucs = Arrays.stream(khoiKienThuc.getIdKienThuc().split(","))
                    .map(Integer::parseInt)
                    .map(id -> kienThucRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Kiến thức không tồn tại")))
                    .collect(Collectors.toList());
            
            // Load subject information for each knowledge item
            kienThucs.forEach(kienThuc -> {
                if (kienThuc.getIdHocPhan() != null && !kienThuc.getIdHocPhan().isEmpty()) {
                    List<HocPhan> hocPhans = Arrays.stream(kienThuc.getIdHocPhan().split(","))
                            .map(Integer::parseInt)
                            .map(id -> hocPhanRepository.findById(id)
                                    .orElseThrow(() -> new RuntimeException("Học phần không tồn tại")))
                            .collect(Collectors.toList());
                    kienThuc.setHocPhans(hocPhans);
                } else {
                    kienThuc.setHocPhans(new ArrayList<>());
                }
            });
            
            khoiKienThuc.setDanhSachKienThuc(kienThucs);
        } else {
            khoiKienThuc.setDanhSachKienThuc(new ArrayList<>());
        }
        return khoiKienThuc;
    }
}