package org.example.controllers;

import org.example.models.HocKy;
import org.example.services.HocKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/hocky")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class HocKyController {

    @Autowired
    private HocKyService hocKyService;

    // Lấy tất cả học kỳ
    @GetMapping
    public ResponseEntity<List<HocKy>> layTatCaHocKy() {
        return ResponseEntity.ok(hocKyService.layTatCaHocKy());
    }

    // Lấy học kỳ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<HocKy> layHocKyTheoId(@PathVariable Integer id) {
        return hocKyService.layHocKyTheoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Thêm học kỳ mới
    @PostMapping
    public ResponseEntity<HocKy> themHocKyMoi(@RequestBody Map<String, List<Integer>> request) {
        try {
            List<Integer> danhSachHocPhan = request.get("idHocPhan");
            if (danhSachHocPhan == null) {
                danhSachHocPhan = new ArrayList<>();
            }
            HocKy hocKy = hocKyService.themHocKyMoi(danhSachHocPhan);
            return ResponseEntity.ok(hocKy);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Thêm học phần vào học kỳ
    @PostMapping("/{idHocKy}/hocphan/{idHocPhan}")
    public ResponseEntity<HocKy> themHocPhanVaoHocKy(
            @PathVariable Integer idHocKy,
            @PathVariable Integer idHocPhan) {
        try {
            HocKy hocKy = hocKyService.themHocPhanVaoHocKy(idHocKy, idHocPhan);
            return ResponseEntity.ok(hocKy);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Xóa học phần khỏi học kỳ
    @DeleteMapping("/{idHocKy}/hocphan/{idHocPhan}")
    public ResponseEntity<HocKy> xoaHocPhanKhoiHocKy(
            @PathVariable Integer idHocKy,
            @PathVariable Integer idHocPhan) {
        try {
            HocKy hocKy = hocKyService.xoaHocPhanKhoiHocKy(idHocKy, idHocPhan);
            return ResponseEntity.ok(hocKy);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Xóa học kỳ
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaHocKy(@PathVariable Integer id) {
        try {
            hocKyService.xoaHocKy(id);
            return ResponseEntity.ok(Map.of("message", "Xóa học kỳ thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
} 