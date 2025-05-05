package org.example.services;

import org.example.models.KienThuc;
import org.example.models.HocPhan;
import org.example.repositories.KienThucRepository;
import org.example.repositories.HocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        List<Integer> currentIds = kienThuc.getIdHocPhan();
        if (!currentIds.contains(idHocPhan)) {
            currentIds.add(idHocPhan);
            kienThuc.setIdHocPhan(currentIds);
            return kienThucRepository.save(kienThuc);
        } else {
            throw new IllegalArgumentException("Học phần đã tồn tại trong kiến thức này");
        }
    }

    @Transactional
    public KienThuc xoaHocPhanKhoiKienThuc(Integer idKienThuc, Integer idHocPhan) {
        KienThuc kienThuc = layKienThucById(idKienThuc);
        List<Integer> currentIds = kienThuc.getIdHocPhan();
        
        if (currentIds.isEmpty()) {
            throw new IllegalArgumentException("Kiến thức này không có học phần nào");
        }

        if (!currentIds.remove(idHocPhan)) {
            throw new IllegalArgumentException("Học phần không tồn tại trong kiến thức này");
        }

        kienThuc.setIdHocPhan(currentIds);
        return kienThucRepository.save(kienThuc);
    }

    public List<KienThuc> layTatCaKienThuc() {
        List<KienThuc> kienThucList = kienThucRepository.findAll();
        
        for (KienThuc kienThuc : kienThucList) {
            // Lấy thông tin chi tiết của các HocPhan
            List<HocPhan> hocPhanList = kienThuc.getIdHocPhan().stream()
                .map(id -> hocPhanRepository.findById(id).orElse(null))
                .filter(hocPhan -> hocPhan != null)
                .collect(Collectors.toList());
            
            // Gán danh sách HocPhan vào kienThuc
            kienThuc.setHocPhanList(hocPhanList);
        }
        
        return kienThucList;
    }

    public KienThuc layKienThucById(Integer id) {
        KienThuc kienThuc = kienThucRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kiến thức với ID: " + id));
        
        // Lấy thông tin chi tiết của các HocPhan
        List<HocPhan> hocPhanList = kienThuc.getIdHocPhan().stream()
            .map(hocPhanId -> hocPhanRepository.findById(hocPhanId).orElse(null))
            .filter(hocPhan -> hocPhan != null)
            .collect(Collectors.toList());
        
        // Gán danh sách HocPhan vào kienThuc
        kienThuc.setHocPhanList(hocPhanList);
        
        return kienThuc;
    }

    public List<KienThuc> findByHocPhanId(Integer hocPhanId) {
        return kienThucRepository.findByHocPhanId(hocPhanId);
    }
} 