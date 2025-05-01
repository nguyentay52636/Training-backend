package org.example.controllers;

import org.example.models.ThongTinChung;
import org.example.services.ThongTinChungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thongtinchung")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class ThongTinChungController {

    @Autowired
    private ThongTinChungService thongTinChungService;

    // Thêm thông tin chung mới
    @PostMapping
    public ResponseEntity<?> themThongTinChung(@Valid @RequestBody ThongTinChung thongTinChung) {
        try {
            ThongTinChung saved = thongTinChungService.themThongTinChung(thongTinChung);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật thông tin chung
    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatThongTinChung(
            @PathVariable Integer id,
            @Valid @RequestBody ThongTinChung thongTinChung) {
        try {
            thongTinChung.setId(id);
            ThongTinChung updated = thongTinChungService.capNhatThongTinChung(thongTinChung);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa thông tin chung
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaThongTinChung(@PathVariable Integer id) {
        try {
            thongTinChungService.xoaThongTinChung(id);
            return ResponseEntity.ok(Map.of("message", "Xóa thông tin chung thành công"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy thông tin chung theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> layThongTinChungTheoId(@PathVariable Integer id) {
        try {
            ThongTinChung thongTinChung = thongTinChungService.layThongTinChungById(id);
            return ResponseEntity.ok(thongTinChung);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy tất cả thông tin chung
    @GetMapping
    public ResponseEntity<List<ThongTinChung>> layTatCaThongTinChung() {
        List<ThongTinChung> danhSach = thongTinChungService.layTatCaThongTinChung();
        return ResponseEntity.ok(danhSach);
    }

} 