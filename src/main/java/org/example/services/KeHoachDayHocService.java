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
        
        existingKeHoach.setTenChuyenNganh(keHoachDayHoc.getTenChuyenNganh());
        existingKeHoach.setIdHocKy(keHoachDayHoc.getIdHocKy());
        
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
        mapHocPhanToKeHoach(keHoachList);
        return keHoachList;
    }

    public KeHoachDayHoc layKeHoachTheoId(Integer id) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));
        
        // Lấy thông tin chi tiết của các HocPhan từ bảng ctdt_hocky
        List<HocPhan> hocPhanList = keHoach.getIdHocKy().stream()
            .map(hocKyId -> hocPhanRepository.findById(hocKyId).orElse(null))
            .filter(hocPhan -> hocPhan != null)
            .collect(Collectors.toList());
        
        keHoach.setHocPhanList(hocPhanList);
        return keHoach;
    }

    public List<KeHoachDayHoc> layKeHoachTheoHocKy(Integer hocKy) {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findByHocKy(hocKy);
        mapHocPhanToKeHoach(keHoachList);
        return keHoachList;
    }

    public List<KeHoachDayHoc> layKeHoachTheoChuyenNganhVaHocKy(Integer idChuyenNganh, Integer hocKy) {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findByIdChuyenNganhAndHocKy(idChuyenNganh, hocKy);
        mapHocPhanToKeHoach(keHoachList);
        return keHoachList;
    }

    private void mapHocPhanToKeHoach(List<KeHoachDayHoc> keHoachList) {
        for (KeHoachDayHoc keHoach : keHoachList) {
            List<HocPhan> hocPhanList = keHoach.getIdHocKy().stream()
                .map(hocKyId -> hocPhanRepository.findById(hocKyId).orElse(null))
                .filter(hocPhan -> hocPhan != null)
                .collect(Collectors.toList());
            keHoach.setHocPhanList(hocPhanList);
        }
    }

    @Transactional
    public void themHocKyVaoKeHoach(Integer idKeHoach, Integer idHocKy) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idKeHoach)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));

        List<Integer> currentIds = keHoach.getIdHocKy();
        if (!currentIds.contains(idHocKy)) {
            currentIds.add(idHocKy);
            keHoach.setIdHocKy(currentIds);
            keHoachDayHocRepository.save(keHoach);
        }
    }

    @Transactional
    public void xoaHocKyKhoiKeHoach(Integer idKeHoach, Integer idHocKy) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idKeHoach)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));

        List<Integer> currentIds = keHoach.getIdHocKy();
        currentIds.remove(idHocKy);
        keHoach.setIdHocKy(currentIds);
        keHoachDayHocRepository.save(keHoach);
    }

    @Transactional
    public KeHoachDayHoc themHocPhanVaoChuyenNganh(Integer idChuyenNganh, Integer idHocPhan) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idChuyenNganh)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên ngành"));

        HocPhan hocPhan = hocPhanRepository.findById(idHocPhan)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần"));

        List<HocPhan> currentHocPhanList = keHoach.getHocPhanList();
        if (!currentHocPhanList.contains(hocPhan)) {
            currentHocPhanList.add(hocPhan);
            keHoach.setHocPhanList(currentHocPhanList);
            return keHoachDayHocRepository.save(keHoach);
        }
        return keHoach;
    }

    @Transactional
    public KeHoachDayHoc xoaHocPhanKhoiChuyenNganh(Integer idChuyenNganh, Integer idHocPhan) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idChuyenNganh)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên ngành"));

        List<HocPhan> currentHocPhanList = keHoach.getHocPhanList();
        currentHocPhanList.removeIf(hp -> hp.getIdHocPhan().equals(idHocPhan));
        keHoach.setHocPhanList(currentHocPhanList);
        return keHoachDayHocRepository.save(keHoach);
    }

    @Transactional
    public KeHoachDayHoc themHocKyMoi(Integer idChuyenNganh, Integer hocKyMoi) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idChuyenNganh)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên ngành"));

        List<Integer> currentHocKyList = keHoach.getIdHocKy();
        if (!currentHocKyList.contains(hocKyMoi)) {
            currentHocKyList.add(hocKyMoi);
            keHoach.setIdHocKy(currentHocKyList);
            return keHoachDayHocRepository.save(keHoach);
        }
        return keHoach;
    }

    @Transactional
    public KeHoachDayHoc xoaHocKyKhoiChuyenNganh(Integer idChuyenNganh, Integer hocKy) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idChuyenNganh)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên ngành"));

        List<Integer> currentHocKyList = keHoach.getIdHocKy();
        currentHocKyList.remove(hocKy);
        keHoach.setIdHocKy(currentHocKyList);
        return keHoachDayHocRepository.save(keHoach);
    }

    @Transactional
    public KeHoachDayHoc themHocKyVaoChuyenNganh(Integer idChuyenNganh, Integer hocKy, List<Integer> danhSachHocPhan) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idChuyenNganh)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên ngành"));

        // Thêm học kỳ mới
        List<Integer> currentHocKyList = keHoach.getIdHocKy();
        if (!currentHocKyList.contains(hocKy)) {
            currentHocKyList.add(hocKy);
            keHoach.setIdHocKy(currentHocKyList);
        }

        // Thêm các học phần vào học kỳ
        List<HocPhan> hocPhanList = danhSachHocPhan.stream()
                .map(id -> hocPhanRepository.findById(id).orElse(null))
                .filter(hp -> hp != null)
                .collect(Collectors.toList());

        List<HocPhan> currentHocPhanList = keHoach.getHocPhanList();
        currentHocPhanList.addAll(hocPhanList);
        keHoach.setHocPhanList(currentHocPhanList);

        return keHoachDayHocRepository.save(keHoach);
    }

    @Transactional
    public KeHoachDayHoc xoaHocKyVaHocPhanKhoiChuyenNganh(Integer idChuyenNganh, Integer hocKy) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idChuyenNganh)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên ngành"));

        // Xóa học kỳ
        List<Integer> currentHocKyList = keHoach.getIdHocKy();
        currentHocKyList.remove(hocKy);
        keHoach.setIdHocKy(currentHocKyList);

        // Xóa các học phần liên quan đến học kỳ này
        List<HocPhan> currentHocPhanList = keHoach.getHocPhanList();
        currentHocPhanList.removeIf(hp -> keHoach.getIdHocKy().contains(hp.getIdHocPhan()));
        keHoach.setHocPhanList(currentHocPhanList);

        return keHoachDayHocRepository.save(keHoach);
    }
}