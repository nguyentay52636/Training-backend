package org.example.controllers;

import org.example.models.DeCuongChiTiet;
import org.example.services.DeCuongChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/decuongchitiet")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class DeCuongChiTietController {

    @Autowired
    private DeCuongChiTietService deCuongChiTietService;

    // Thêm đề cương chi tiết mới
    @PostMapping
    public ResponseEntity<?> themDeCuongChiTiet(@Valid @RequestBody DeCuongChiTiet deCuongChiTiet) {
        try {
            DeCuongChiTiet saved = deCuongChiTietService.themDeCuongChiTiet(deCuongChiTiet);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật đề cương chi tiết
    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatDeCuongChiTiet(
            @PathVariable Integer id,
            @Valid @RequestBody DeCuongChiTiet deCuongChiTiet) {
        try {
            deCuongChiTiet.setId(id);
            DeCuongChiTiet updated = deCuongChiTietService.capNhatDeCuongChiTiet(deCuongChiTiet);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa đề cương chi tiết
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaDeCuongChiTiet(@PathVariable Integer id) {
        try {
            deCuongChiTietService.xoaDeCuongChiTiet(id);
            return ResponseEntity.ok(Map.of("message", "Xóa đề cương chi tiết thành công"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy đề cương chi tiết theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> layDeCuongChiTietTheoId(@PathVariable Integer id) {
        try {
            DeCuongChiTiet deCuongChiTiet = deCuongChiTietService.layDeCuongChiTietById(id);
            return ResponseEntity.ok(deCuongChiTiet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy tất cả đề cương chi tiết
    @GetMapping
    public ResponseEntity<List<DeCuongChiTiet>> layTatCaDeCuongChiTiet() {
        List<DeCuongChiTiet> danhSach = deCuongChiTietService.layTatCaDeCuongChiTiet();
        return ResponseEntity.ok(danhSach);
    }

} 