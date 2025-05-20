package org.example.services;

import org.example.models.KeHoachDayHoc;
import org.example.models.HocPhan;
import org.example.models.HocKy;
import org.example.repositories.KeHoachDayHocRepository;
import org.example.repositories.HocPhanRepository;
import org.example.repositories.HocKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class KeHoachDayHocService {
    private static final Logger logger = LoggerFactory.getLogger(KeHoachDayHocService.class);

    @Autowired
    private KeHoachDayHocRepository keHoachDayHocRepository;

    @Autowired
    private HocPhanRepository hocPhanRepository;

    @Autowired
    private HocKyRepository hocKyRepository;

    @Transactional
    public KeHoachDayHoc themKeHoach(KeHoachDayHoc keHoachDayHoc) {
        logger.info("Adding new ke hoach day hoc: {}", keHoachDayHoc);
        KeHoachDayHoc savedKeHoach = keHoachDayHocRepository.save(keHoachDayHoc);
        mapHocKyToKeHoach(savedKeHoach);
        return savedKeHoach;
    }

    @Transactional
    public KeHoachDayHoc capNhatKeHoach(Integer id, KeHoachDayHoc keHoachDayHoc) {
        logger.info("Updating ke hoach day hoc with id: {}", id);
        KeHoachDayHoc existingKeHoach = keHoachDayHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));
        
        existingKeHoach.setTenChuyenNganh(keHoachDayHoc.getTenChuyenNganh());
        existingKeHoach.setIdHocKy(keHoachDayHoc.getIdHocKy());
        
        KeHoachDayHoc updatedKeHoach = keHoachDayHocRepository.save(existingKeHoach);
        mapHocKyToKeHoach(updatedKeHoach);
        return updatedKeHoach;
    }

    @Transactional
    public void xoaKeHoach(Integer id) {
        logger.info("Deleting ke hoach day hoc with id: {}", id);
        if (!keHoachDayHocRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy kế hoạch dạy học");
        }
        keHoachDayHocRepository.deleteById(id);
    }

    public List<KeHoachDayHoc> layTatCaKeHoach() {
        logger.info("Getting all ke hoach day hoc");
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findAll();
        for (KeHoachDayHoc keHoach : keHoachList) {
            mapHocKyToKeHoach(keHoach);
        }
        return keHoachList;
    }

    public KeHoachDayHoc layKeHoachTheoId(Integer id) {
        logger.info("Getting ke hoach day hoc with id: {}", id);
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));
        
        mapHocKyToKeHoach(keHoach);
        return keHoach;
    }

    public List<KeHoachDayHoc> layKeHoachTheoHocKy(Integer hocKy) {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findByHocKy(hocKy);
        for (KeHoachDayHoc keHoach : keHoachList) {
            mapHocKyToKeHoach(keHoach);
        }
        return keHoachList;
    }

    public List<KeHoachDayHoc> layKeHoachTheoChuyenNganhVaHocKy(Integer idChuyenNganh, Integer hocKy) {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocRepository.findByIdChuyenNganhAndHocKy(idChuyenNganh, hocKy);
        for (KeHoachDayHoc keHoach : keHoachList) {
            mapHocKyToKeHoach(keHoach);
        }
        return keHoachList;
    }

    private void mapHocKyToKeHoach(KeHoachDayHoc keHoach) {
        logger.info("Mapping hoc ky to ke hoach for chuyen nganh: {}", keHoach.getTenChuyenNganh());
        
        // Lấy thông tin học kỳ cho chuyên ngành
        List<HocKy> hocKyList = keHoach.getIdHocKy().stream()
            .map(hocKyId -> {
                HocKy hocKy = hocKyRepository.findById(hocKyId).orElse(null);
                if (hocKy != null) {
                    logger.info("Found hoc ky: id={}, ten={}", hocKy.getIdHocKy(), hocKy.getTenHocKy());
                    
                    // Lấy thông tin học phần cho học kỳ này
                    List<Integer> idHocPhanList = hocKy.getIdHocPhan();
                    if (idHocPhanList != null) {
                        List<HocPhan> hocPhanList = idHocPhanList.stream()
                            .map(id -> hocPhanRepository.findById(id).orElse(null))
                            .filter(hp -> hp != null)
                            .collect(Collectors.toList());
                        
                        // Gán danh sách học phần vào học kỳ
                        hocKy.setHocPhanList(hocPhanList);
                        
                        logger.info("Hoc ky {} contains {} hoc phan:", hocKy.getTenHocKy(), hocPhanList.size());
                        hocPhanList.forEach(hp -> 
                            logger.info("- {} ({}): {} tín chỉ", hp.getTenHP(), hp.getMaHP(), hp.getSoTinChi())
                        );
                    }
                }
                return hocKy;
            })
            .filter(hocKy -> hocKy != null)
            .collect(Collectors.toList());
        
        // Gán danh sách học kỳ vào chuyên ngành
        keHoach.setHocKyList(hocKyList);
        
        logger.info("Chuyen nganh {} contains {} hoc ky:", keHoach.getTenChuyenNganh(), hocKyList.size());
        hocKyList.forEach(hk -> 
            logger.info("- {}: {} hoc phan", hk.getTenHocKy(), hk.getHocPhanList().size())
        );
    }

    @Transactional
    public void themHocKyVaoKeHoach(Integer idKeHoach, Integer idHocKy) {
        logger.info("Adding hoc ky {} to ke hoach {}", idHocKy, idKeHoach);
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idKeHoach)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));

        List<Integer> currentIds = keHoach.getIdHocKy();
        if (!currentIds.contains(idHocKy)) {
            currentIds.add(idHocKy);
            keHoach.setIdHocKy(currentIds);
            keHoachDayHocRepository.save(keHoach);
            mapHocKyToKeHoach(keHoach);
        }
    }

    @Transactional
    public void xoaHocKyKhoiKeHoach(Integer idKeHoach, Integer idHocKy) {
        logger.info("Removing hoc ky {} from ke hoach {}", idHocKy, idKeHoach);
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idKeHoach)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch dạy học"));

        List<Integer> currentIds = keHoach.getIdHocKy();
        currentIds.remove(idHocKy);
        keHoach.setIdHocKy(currentIds);
        keHoachDayHocRepository.save(keHoach);
        mapHocKyToKeHoach(keHoach);
    }

    @Transactional
    public KeHoachDayHoc themHocPhanVaoChuyenNganh(Integer idChuyenNganh, Integer idHocPhan) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idChuyenNganh)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên ngành"));

        HocPhan hocPhan = hocPhanRepository.findById(idHocPhan)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học phần"));

        // Thêm học phần vào học kỳ tương ứng
        for (HocKy hocKy : keHoach.getHocKyList()) {
            if (hocKy.getHocPhanList() != null && !hocKy.getHocPhanList().contains(hocPhan)) {
                hocKy.getHocPhanList().add(hocPhan);
            }
        }
        return keHoachDayHocRepository.save(keHoach);
    }

    @Transactional
    public KeHoachDayHoc xoaHocPhanKhoiChuyenNganh(Integer idChuyenNganh, Integer idHocPhan) {
        KeHoachDayHoc keHoach = keHoachDayHocRepository.findById(idChuyenNganh)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên ngành"));

        // Xóa học phần khỏi tất cả học kỳ
        for (HocKy hocKy : keHoach.getHocKyList()) {
            if (hocKy.getHocPhanList() != null) {
                hocKy.getHocPhanList().removeIf(hp -> hp.getIdHocPhan().equals(idHocPhan));
            }
        }
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
        HocKy newHocKy = hocKyRepository.findById(hocKy).orElse(null);
        if (newHocKy != null) {
            List<HocPhan> hocPhanList = danhSachHocPhan.stream()
                    .map(id -> hocPhanRepository.findById(id).orElse(null))
                    .filter(hp -> hp != null)
                    .collect(Collectors.toList());
            newHocKy.setHocPhanList(hocPhanList);
            keHoach.getHocKyList().add(newHocKy);
        }

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

        // Xóa học kỳ khỏi danh sách
        keHoach.getHocKyList().removeIf(hk -> hk.getIdHocKy().equals(hocKy));

        return keHoachDayHocRepository.save(keHoach);
    }
}