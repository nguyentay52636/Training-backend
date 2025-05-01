package org.example.controllers;

import org.example.models.KhungChuongTrinh;
import org.example.services.KhungChuongTrinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/khungchuongtrinh")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class KhungChuongTrinhController {

    @Autowired
    private KhungChuongTrinhService khungChuongTrinhService;

    // Thêm khung chương trình mới
    @PostMapping
    public ResponseEntity<?> themKhungChuongTrinh(@Valid @RequestBody KhungChuongTrinh khungChuongTrinh) {
        try {
            KhungChuongTrinh saved = khungChuongTrinhService.themKhungChuongTrinh(khungChuongTrinh);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật khung chương trình
    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatKhungChuongTrinh(
            @PathVariable Integer id,
            @Valid @RequestBody KhungChuongTrinh khungChuongTrinh) {
        try {
            khungChuongTrinh.setId(id);
            KhungChuongTrinh updated = khungChuongTrinhService.capNhatKhungChuongTrinh(khungChuongTrinh);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa khung chương trình
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaKhungChuongTrinh(@PathVariable Integer id) {
        try {
            khungChuongTrinhService.xoaKhungChuongTrinh(id);
            return ResponseEntity.ok(Map.of("message", "Xóa khung chương trình thành công"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy khung chương trình theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> layKhungChuongTrinhTheoId(@PathVariable Integer id) {
        try {
            KhungChuongTrinh khungChuongTrinh = khungChuongTrinhService.layKhungChuongTrinhById(id);
            return ResponseEntity.ok(khungChuongTrinh);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy tất cả khung chương trình
    @GetMapping
    public ResponseEntity<List<KhungChuongTrinh>> layTatCaKhungChuongTrinh() {
        List<KhungChuongTrinh> danhSach = khungChuongTrinhService.layTatCaKhungChuongTrinh();
        return ResponseEntity.ok(danhSach);
    }

} 