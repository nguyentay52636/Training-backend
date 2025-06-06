package org.example.controllers;

import org.example.models.HocPhan;
import org.example.services.HocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/hocphan")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class HocPhanController {

    @Autowired
    private HocPhanService hocPhanService;

    // Thêm học phần mới
    @PostMapping
    public ResponseEntity<?> themHocPhan(@Valid @RequestBody HocPhan hocPhan) {
        try {
            HocPhan savedHocPhan = hocPhanService.themHocPhan(hocPhan);
            return ResponseEntity.ok(savedHocPhan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật học phần
    @PutMapping("/{idHocPhan}")
    public ResponseEntity<?> capNhatHocPhan(
            @PathVariable int idHocPhan,
            @Valid @RequestBody HocPhan hocPhan) {
        try {
            HocPhan updatedHocPhan = hocPhanService.capNhatHocPhan(idHocPhan, hocPhan);
            return ResponseEntity.ok(updatedHocPhan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa học phần
    @DeleteMapping("/{idHocPhan}")
    public ResponseEntity<?> xoaHocPhan(@PathVariable int idHocPhan) {
        try {
            hocPhanService.xoaHocPhan(idHocPhan);
            return ResponseEntity.ok(Map.of("message", "Xóa học phần thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy học phần theo ID
    @GetMapping("/{idHocPhan}")
    public ResponseEntity<?> layHocPhanTheoId(@PathVariable int idHocPhan) {
        try {
            HocPhan hocPhan = hocPhanService.layHocPhanTheoId(idHocPhan);
            return ResponseEntity.ok(hocPhan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy tất cả học phần
    @GetMapping
    public ResponseEntity<?> layTatCaHocPhan() {
        try {
            List<HocPhan> danhSachHocPhan = hocPhanService.layTatCaHocPhan();
            if (danhSachHocPhan.isEmpty()) {
                return ResponseEntity.ok(Map.of("message", "Không có học phần nào trong hệ thống"));
            }
            return ResponseEntity.ok(danhSachHocPhan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Tìm kiếm theo từ khóa (mã hoặc tên)
    @GetMapping("/timkiem")
    public ResponseEntity<?> timKiem(
            @RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Từ khóa tìm kiếm không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<HocPhan> ketQua = hocPhanService.timKiem(keyword);
        return ResponseEntity.ok(ketQua);
    }

    // Tìm kiếm theo tên học phần
    @GetMapping("/timkiem/ten")
    public ResponseEntity<?> timKiemTheoTen(
            @RequestParam String tenHP) {
        if (tenHP == null || tenHP.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên học phần không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<HocPhan> ketQua = hocPhanService.timKiemTheoTen(tenHP);
        return ResponseEntity.ok(ketQua);
    }

    // Tìm kiếm theo số tín chỉ
    @GetMapping("/timkiem/tinchi")
    public ResponseEntity<?> timKiemTheoTinChi(
            @RequestParam Integer soTinChi) {
        if (soTinChi == null || soTinChi < 1 || soTinChi > 6) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Số tín chỉ phải từ 1 đến 6");
            return ResponseEntity.badRequest().body(error);
        }
        List<HocPhan> ketQua = hocPhanService.timKiemTheoTinChi(soTinChi);
        return ResponseEntity.ok(ketQua);
    }

    // Tìm kiếm theo loại học phần
    @GetMapping("/timkiem/loai")
    public ResponseEntity<?> timKiemTheoLoai(
            @RequestParam Integer loaiHocPhan) {
        if (loaiHocPhan == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Loại học phần không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<HocPhan> ketQua = hocPhanService.timKiemTheoLoai(loaiHocPhan);
        return ResponseEntity.ok(ketQua);
    }
}
