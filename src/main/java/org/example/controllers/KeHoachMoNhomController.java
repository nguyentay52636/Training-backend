package org.example.controllers;

import org.example.models.KeHoachMoNhom;
import org.example.services.KeHoachMoNhomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kehoachmonhom")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class KeHoachMoNhomController {

    @Autowired
    private KeHoachMoNhomService keHoachMoNhomService;

    // Thêm kế hoạch mở nhóm mới
    @PostMapping
    public ResponseEntity<?> themKeHoachMoNhom(@Valid @RequestBody KeHoachMoNhom keHoachMoNhom) {
        try {
            KeHoachMoNhom saved = keHoachMoNhomService.themKeHoachMoNhom(keHoachMoNhom);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật kế hoạch mở nhóm
    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatKeHoachMoNhom(
            @PathVariable Integer id,
            @Valid @RequestBody KeHoachMoNhom keHoachMoNhom) {
        try {
            keHoachMoNhom.setId(id);
            KeHoachMoNhom updated = keHoachMoNhomService.capNhatKeHoachMoNhom(keHoachMoNhom);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa kế hoạch mở nhóm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaKeHoachMoNhom(@PathVariable Integer id) {
        try {
            keHoachMoNhomService.xoaKeHoachMoNhom(id);
            return ResponseEntity.ok(Map.of("message", "Xóa kế hoạch mở nhóm thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy kế hoạch mở nhóm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> layKeHoachMoNhomTheoId(@PathVariable Integer id) {
        try {
            KeHoachMoNhom keHoachMoNhom = keHoachMoNhomService.layKeHoachMoNhomById(id);
            return ResponseEntity.ok(keHoachMoNhom);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Lấy tất cả kế hoạch mở nhóm
    @GetMapping
    public ResponseEntity<List<KeHoachMoNhom>> layTatCaKeHoachMoNhom() {
        List<KeHoachMoNhom> danhSach = keHoachMoNhomService.layTatCaKeHoachMoNhom();
        return ResponseEntity.ok(danhSach);
    }

} 