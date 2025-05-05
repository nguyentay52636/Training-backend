package org.example.services;

import org.example.models.KeHoachDayHoc;
import org.example.models.HocPhan;
import org.example.repositories.KeHoachDayHocRepository;
import org.example.repositories.HocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeHoachDayHocService {

    @Autowired
    private KeHoachDayHocRepository keHoachDayHocRepository;

    @Autowired
    private HocPhanRepository hocPhanRepository;

    @Transactional
    public KeHoachDayHoc themKeHoach(KeHoachDayHoc keHoachDayHoc) {
        return keHoachDayHocRepository.save(keHoachDayHoc);
    }

    @Transactional
    public KeHoachDayHoc capNhatKeHoach(Integer id, KeHoachDayHoc keHoachDayHoc) {
        KeHoachDayHoc existingKeHoach = keHoachDayHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));
        
        existingKeHoach.setIdChuyenNganh(keHoachDayHoc.getIdChuyenNganh());
        existingKeHoach.setTenChuyenNganh(keHoachDayHoc.getTenChuyenNganh());
        existingKeHoach.setIdHocPhan(keHoachDayHoc.getIdHocPhan());
        existingKeHoach.setHocKyThucHien(keHoachDayHoc.getHocKyThucHien());
        
        return keHoachDayHocRepository.save(existingKeHoach);
    }

    @Transactional
    public void xoaKeHoach(Integer id) {
        if (!keHoachDayHocRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy kế hoạch dạy học");
        }
        keHoachDayHocRepository.deleteById(id);
    }

    public List<KeHoachDayHoc> layTatCaKeHoach() {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findAll();
        
        for (KeHoachDayHoc keHoach : keHoachList) {
            // Lấy thông tin chi tiết của các HocPhan
            List<HocPhan> hocPhanList = keHoach.getIdHocPhan().stream()
                .map(id -> hocPhanRepository.findById(id).orElse(null))
                .filter(hocPhan -> hocPhan != null)
                .collect(Collectors.toList());
            
            // Gán danh sách HocPhan vào keHoach
            keHoach.setHocPhanList(hocPhanList);
        }
        
        return keHoachList;
    }

    public KeHoachDayHoc layKeHoachTheoId(Integer id) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));
        
        // Lấy thông tin chi tiết của các HocPhan
        List<HocPhan> hocPhanList = keHoach.getIdHocPhan().stream()
            .map(hocPhanId -> hocPhanRepository.findById(hocPhanId).orElse(null))
            .filter(hocPhan -> hocPhan != null)
            .collect(Collectors.toList());
        
        // Gán danh sách HocPhan vào keHoach
        keHoach.setHocPhanList(hocPhanList);
        
        return keHoach;
    }

    public List<KeHoachDayHoc> layKeHoachTheoHocKy(Integer hocKy) {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findByHocKyThucHien(hocKy);
        
        for (KeHoachDayHoc keHoach : keHoachList) {
            // Lấy thông tin chi tiết của các HocPhan
            List<HocPhan> hocPhanList = keHoach.getIdHocPhan().stream()
                .map(id -> hocPhanRepository.findById(id).orElse(null))
                .filter(hocPhan -> hocPhan != null)
                .collect(Collectors.toList());
            
            // Gán danh sách HocPhan vào keHoach
            keHoach.setHocPhanList(hocPhanList);
        }
        
        return keHoachList;
    }

    public List<KeHoachDayHoc> layKeHoachTheoChuyenNganhVaHocKy(Integer idChuyenNganh, Integer hocKy) {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findByIdChuyenNganhAndHocKyThucHien(idChuyenNganh, hocKy);
        
        for (KeHoachDayHoc keHoach : keHoachList) {
            // Lấy thông tin chi tiết của các HocPhan
            List<HocPhan> hocPhanList = keHoach.getIdHocPhan().stream()
                .map(id -> hocPhanRepository.findById(id).orElse(null))
                .filter(hocPhan -> hocPhan != null)
                .collect(Collectors.toList());
            
            // Gán danh sách HocPhan vào keHoach
            keHoach.setHocPhanList(hocPhanList);
        }
        
        return keHoachList;
    }

    public List<KeHoachDayHoc> layKeHoachTheoHocPhanVaHocKy(Integer hocPhanId, Integer hocKy) {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findByHocPhanIdAndHocKyThucHien(hocPhanId, hocKy);
        
        for (KeHoachDayHoc keHoach : keHoachList) {
            // Lấy thông tin chi tiết của các HocPhan
            List<HocPhan> hocPhanList = keHoach.getIdHocPhan().stream()
                .map(id -> hocPhanRepository.findById(id).orElse(null))
                .filter(hocPhan -> hocPhan != null)
                .collect(Collectors.toList());
            
            // Gán danh sách HocPhan vào keHoach
            keHoach.setHocPhanList(hocPhanList);
        }
        
        return keHoachList;
    }

    @Transactional
    public void themHocPhanVaoKeHoach(Integer idKeHoach, Integer idHocPhan) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idKeHoach)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));

        List<Integer> currentIds = keHoach.getIdHocPhan();
        if (!currentIds.contains(idHocPhan)) {
            currentIds.add(idHocPhan);
            keHoach.setIdHocPhan(currentIds);
            keHoachDayHocRepository.save(keHoach);
        }
    }

    @Transactional
    public void xoaHocPhanKhoiKeHoach(Integer idKeHoach, Integer idHocPhan) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idKeHoach)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));

        List<Integer> currentIds = keHoach.getIdHocPhan();
        currentIds.remove(idHocPhan);
        keHoach.setIdHocPhan(currentIds);
        keHoachDayHocRepository.save(keHoach);
    }
}