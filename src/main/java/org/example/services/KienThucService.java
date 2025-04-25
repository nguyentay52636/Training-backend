package org.example.services;

import org.example.models.KienThuc;
import org.example.models.HocPhan;
import org.example.repositories.KienThucRepository;
import org.example.repositories.HocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class KienThucService {
    @Autowired
    private KienThucRepository kienThucRepository;

    @Autowired
    private HocPhanRepository hocPhanRepository;

    @Transactional
    public KienThuc themKienThuc(KienThuc kienThuc) {
        if (kienThuc.getTenKienThuc() == null || kienThuc.getTenKienThuc().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên kiến thức không được để trống");
        }
        return kienThucRepository.save(kienThuc);
    }

    @Transactional
    public KienThuc capNhatKienThuc(KienThuc kienThuc) {
        if (kienThuc.getIdKienThuc() == null) {
            throw new IllegalArgumentException("ID kiến thức không được để trống");
        }
        if (!kienThucRepository.existsById(kienThuc.getIdKienThuc())) {
            throw new IllegalArgumentException("Không tìm thấy kiến thức với ID: " + kienThuc.getIdKienThuc());
        }
        if (kienThuc.getTenKienThuc() == null || kienThuc.getTenKienThuc().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên kiến thức không được để trống");
        }
        return kienThucRepository.save(kienThuc);
    }

    @Transactional
    public void xoaKienThuc(Integer id) {
        if (!kienThucRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy kiến thức với ID: " + id);
        }
        kienThucRepository.deleteById(id);
    }

    @Transactional
    public KienThuc themHocPhanVaoKienThuc(Integer idKienThuc, Integer idHocPhan) {
        KienThuc kienThuc = layKienThucById(idKienThuc);
        HocPhan hocPhan = hocPhanRepository.findById(idHocPhan)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy học phần với ID: " + idHocPhan));

        String currentIdHocPhan = kienThuc.getIdHocPhan();
        List<String> currentIds = new ArrayList<>();
        if (currentIdHocPhan != null && !currentIdHocPhan.trim().isEmpty()) {
            currentIds.addAll(Arrays.asList(currentIdHocPhan.split(",")));
        }

        String newIdHocPhan = String.valueOf(idHocPhan);
        if (currentIds.contains(newIdHocPhan)) {
            throw new IllegalArgumentException("Học phần đã tồn tại trong kiến thức này");
        }

        currentIds.add(newIdHocPhan);
        kienThuc.setIdHocPhan(String.join(",", currentIds));
        return kienThucRepository.save(kienThuc);
    }

    @Transactional
    public KienThuc xoaHocPhanKhoiKienThuc(Integer idKienThuc, Integer idHocPhan) {
        KienThuc kienThuc = layKienThucById(idKienThuc);
        String currentIdHocPhan = kienThuc.getIdHocPhan();
        
        if (currentIdHocPhan == null || currentIdHocPhan.trim().isEmpty()) {
            throw new IllegalArgumentException("Kiến thức này không có học phần nào");
        }

        List<String> currentIds = new ArrayList<>(Arrays.asList(currentIdHocPhan.split(",")));
        String targetId = String.valueOf(idHocPhan);
        
        if (!currentIds.contains(targetId)) {
            throw new IllegalArgumentException("Học phần không tồn tại trong kiến thức này");
        }

        currentIds.remove(targetId);
        kienThuc.setIdHocPhan(String.join(",", currentIds));
        return kienThucRepository.save(kienThuc);
    }

    public List<KienThuc> layTatCaKienThuc() {
        List<KienThuc> kienThucs = kienThucRepository.findAll();
        kienThucs.forEach(this::loadHocPhans);
        return kienThucs;
    }

    public KienThuc layKienThucById(Integer id) {
        KienThuc kienThuc = kienThucRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kiến thức với ID: " + id));
        loadHocPhans(kienThuc);
        return kienThuc;
    }

    private void loadHocPhans(KienThuc kienThuc) {
        if (kienThuc.getIdHocPhan() == null || kienThuc.getIdHocPhan().trim().isEmpty()) {
            kienThuc.setHocPhans(new ArrayList<>());
            return;
        }

        List<String> hocPhanIds = Arrays.asList(kienThuc.getIdHocPhan().split(","));
        List<HocPhan> hocPhans = new ArrayList<>();

        for (String idStr : hocPhanIds) {
            try {
                Integer id = Integer.parseInt(idStr.trim());
                hocPhanRepository.findById(id).ifPresent(hocPhans::add);
            } catch (NumberFormatException e) {
                System.out.println("Invalid hoc phan ID: " + idStr);
            }
        }

        kienThuc.setHocPhans(hocPhans);
    }
} 