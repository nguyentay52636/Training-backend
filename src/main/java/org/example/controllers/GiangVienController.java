package org.example.controllers;

import org.example.models.GiangVien;
import org.example.services.GiangVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/giangvien")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class GiangVienController {

    @Autowired
    private GiangVienService giangVienService;

    // Thêm giảng viên mới
    @PostMapping
    public ResponseEntity<?> themGiangVien(@Valid @RequestBody GiangVien giangVien) {
        try {
            GiangVien savedGiangVien = giangVienService.themGiangVien(giangVien);
            return ResponseEntity.ok(savedGiangVien);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật giảng viên
    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatGiangVien(
            @PathVariable Integer id,
            @Valid @RequestBody GiangVien giangVien) {
        try {
            giangVien.setIdGiangVien(id);
            GiangVien updatedGiangVien = giangVienService.capNhatGiangVien(giangVien);
            return ResponseEntity.ok(updatedGiangVien);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa giảng viên
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaGiangVien(@PathVariable Integer id) {
        try {
            giangVienService.xoaGiangVien(id);
            return ResponseEntity.ok(Map.of("message", "Xóa giảng viên thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy giảng viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> layGiangVienTheoId(@PathVariable Integer id) {
        try {
            GiangVien giangVien = giangVienService.layGiangVienById(id);
            return ResponseEntity.ok(giangVien);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy tất cả giảng viên
    @GetMapping
    public ResponseEntity<List<GiangVien>> layTatCaGiangVien() {
        List<GiangVien> danhSachGiangVien = giangVienService.layTatCaGiangVien();
        return ResponseEntity.ok(danhSachGiangVien);
    }


} 