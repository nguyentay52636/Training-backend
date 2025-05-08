package org.example.controllers;

import org.example.models.KeHoachDayHoc;
import org.example.services.KeHoachDayHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kehoachdayhoc")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class KeHoachDayHocController {

    @Autowired
    private KeHoachDayHocService keHoachDayHocService;

    // Lấy tất cả kế hoạch dạy học
    @GetMapping
    public ResponseEntity<List<KeHoachDayHoc>> layTatCaKeHoach() {
        return ResponseEntity.ok(keHoachDayHocService.layTatCaKeHoach());
    }

    // Lấy kế hoạch dạy học theo ID
    @GetMapping("/{id}")
    public ResponseEntity<KeHoachDayHoc> layKeHoachTheoId(@PathVariable Integer id) {
        return ResponseEntity.ok(keHoachDayHocService.layKeHoachTheoId(id));
    }

    // Thêm kế hoạch dạy học mới
    @PostMapping
    public ResponseEntity<?> themKeHoach(@RequestBody KeHoachDayHoc keHoachDayHoc) {
        try {
            if (keHoachDayHoc.getTenChuyenNganh() == null || keHoachDayHoc.getTenChuyenNganh().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Tên chuyên ngành không được để trống"));
            }
            if (keHoachDayHoc.getIdHocKy() == null || keHoachDayHoc.getIdHocKy().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Danh sách học kỳ không được để trống"));
            }
            return ResponseEntity.ok(keHoachDayHocService.themKeHoach(keHoachDayHoc));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Cập nhật kế hoạch dạy học
    @PutMapping("/{id}")
    public ResponseEntity<KeHoachDayHoc> capNhatKeHoach(
            @PathVariable Integer id,
            @RequestBody KeHoachDayHoc keHoachDayHoc) {
        return ResponseEntity.ok(keHoachDayHocService.capNhatKeHoach(id, keHoachDayHoc));
    }

    // Xóa kế hoạch dạy học
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaKeHoach(@PathVariable Integer id) {
        try {
            keHoachDayHocService.xoaKeHoach(id);
            return ResponseEntity.ok(Map.of("message", "Xóa kế hoạch dạy học thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Thêm học kỳ vào kế hoạch dạy học
    @PostMapping("/{idKeHoach}/hocky/{idHocKy}")
    public ResponseEntity<?> themHocKyVaoKeHoach(
            @PathVariable Integer idKeHoach,
            @PathVariable Integer idHocKy) {
        try {
            keHoachDayHocService.themHocKyVaoKeHoach(idKeHoach, idHocKy);
            return ResponseEntity.ok(Map.of("message", "Thêm học kỳ vào kế hoạch dạy học thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Xóa học kỳ khỏi kế hoạch dạy học
    @DeleteMapping("/{idKeHoach}/hocky/{idHocKy}")
    public ResponseEntity<?> xoaHocKyKhoiKeHoach(
            @PathVariable Integer idKeHoach,
            @PathVariable Integer idHocKy) {
        try {
            keHoachDayHocService.xoaHocKyKhoiKeHoach(idKeHoach, idHocKy);
            return ResponseEntity.ok(Map.of("message", "Xóa học kỳ khỏi kế hoạch dạy học thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}