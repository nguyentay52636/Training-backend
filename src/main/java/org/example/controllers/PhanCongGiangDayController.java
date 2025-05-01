package org.example.controllers;

import org.example.models.PhanCongGiangDay;
import org.example.services.PhanCongGiangDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/phanconggiangday")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class PhanCongGiangDayController {

    @Autowired
    private PhanCongGiangDayService phanCongGiangDayService;

    // Thêm phân công giảng dạy mới
    @PostMapping
    public ResponseEntity<?> themPhanCongGiangDay(@Valid @RequestBody PhanCongGiangDay phanCongGiangDay) {
        try {
            PhanCongGiangDay savedPhanCong = phanCongGiangDayService.themPhanCongGiangDay(phanCongGiangDay);
            return ResponseEntity.ok(savedPhanCong);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật phân công giảng dạy
    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatPhanCongGiangDay(
            @PathVariable Integer id,
            @Valid @RequestBody PhanCongGiangDay phanCongGiangDay) {
        try {
            phanCongGiangDay.setIdPhanCong(id);
            PhanCongGiangDay updatedPhanCong = phanCongGiangDayService.capNhatPhanCongGiangDay(phanCongGiangDay);
            return ResponseEntity.ok(updatedPhanCong);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa phân công giảng dạy
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaPhanCongGiangDay(@PathVariable Integer id) {
        try {
            phanCongGiangDayService.xoaPhanCongGiangDay(id);
            return ResponseEntity.ok(Map.of("message", "Xóa phân công giảng dạy thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy phân công giảng dạy theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> layPhanCongGiangDayTheoId(@PathVariable Integer id) {
        try {
            PhanCongGiangDay phanCongGiangDay = phanCongGiangDayService.layPhanCongGiangDayById(id);
            return ResponseEntity.ok(phanCongGiangDay);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy tất cả phân công giảng dạy
    @GetMapping
    public ResponseEntity<List<PhanCongGiangDay>> layTatCaPhanCongGiangDay() {
        List<PhanCongGiangDay> danhSachPhanCong = phanCongGiangDayService.layTatCaPhanCongGiangDay();
        return ResponseEntity.ok(danhSachPhanCong);
    }
} 