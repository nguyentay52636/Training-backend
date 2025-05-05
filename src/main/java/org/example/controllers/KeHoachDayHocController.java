package org.example.controllers;

import org.example.models.KeHoachDayHoc;
import org.example.services.KeHoachDayHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/kehoachdayhoc")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class KeHoachDayHocController {

    @Autowired
    private KeHoachDayHocService keHoachDayHocService;

    @PostMapping
    public ResponseEntity<?> themKeHoach(@RequestBody KeHoachDayHoc keHoachDayHoc) {
        if (keHoachDayHoc.getHocKyThucHien() == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Học kỳ thực hiện không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(keHoachDayHocService.themKeHoach(keHoachDayHoc));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatKeHoach(@PathVariable Integer id, @RequestBody KeHoachDayHoc keHoachDayHoc) {
        if (keHoachDayHoc.getHocKyThucHien() == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Học kỳ thực hiện không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        if (keHoachDayHoc.getIdChuyenNganh() == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "ID chuyên ngành không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(keHoachDayHocService.capNhatKeHoach(id, keHoachDayHoc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaKeHoach(@PathVariable Integer id) {
        keHoachDayHocService.xoaKeHoach(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> layTatCaKeHoach() {
        List<KeHoachDayHoc> keHoachList = keHoachDayHocService.layTatCaKeHoach();
        return ResponseEntity.ok(keHoachList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> layKeHoachTheoId(@PathVariable Integer id) {
        return ResponseEntity.ok(keHoachDayHocService.layKeHoachTheoId(id));
    }

    @GetMapping("/hocky/{hocKy}")
    public ResponseEntity<?> layKeHoachTheoHocKy(@PathVariable Integer hocKy) {
        if (hocKy == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Học kỳ không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(keHoachDayHocService.layKeHoachTheoHocKy(hocKy));
    }

    @GetMapping("/chuyennganh/{idChuyenNganh}/hocky/{hocKy}")
    public ResponseEntity<?> layKeHoachTheoChuyenNganhVaHocKy(
            @PathVariable Integer idChuyenNganh,
            @PathVariable Integer hocKy) {
        if (idChuyenNganh == null || hocKy == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "ID chuyên ngành và học kỳ không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(keHoachDayHocService.layKeHoachTheoChuyenNganhVaHocKy(idChuyenNganh, hocKy));
    }

    @GetMapping("/hocphan/{hocPhanId}/hocky/{hocKy}")
    public ResponseEntity<?> layKeHoachTheoHocPhanVaHocKy(
            @PathVariable Integer hocPhanId,
            @PathVariable Integer hocKy) {
        if (hocPhanId == null || hocKy == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "ID học phần và học kỳ không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(keHoachDayHocService.layKeHoachTheoHocPhanVaHocKy(hocPhanId, hocKy));
    }

    @PostMapping("/{id}/hocphan")
    public ResponseEntity<?> themHocPhanVaoKeHoach(
            @PathVariable Integer id,
            @RequestBody Map<String, Integer> request) {
        Integer idHocPhan = request.get("idHocPhan");
        if (idHocPhan == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "ID học phần không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        keHoachDayHocService.themHocPhanVaoKeHoach(id, idHocPhan);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/hocphan/{idHocPhan}")
    public ResponseEntity<?> xoaHocPhanKhoiKeHoach(
            @PathVariable Integer id,
            @PathVariable Integer idHocPhan) {
        keHoachDayHocService.xoaHocPhanKhoiKeHoach(id, idHocPhan);
        return ResponseEntity.ok().build();
    }
}