package org.example.controllers;

import org.example.models.KhoiKienThuc;
import org.example.services.KhoiKienThucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/khoi-kien-thuc")
public class KhoiKienThucController {
    @Autowired
    private KhoiKienThucService khoiKienThucService;

    @PostMapping
    public ResponseEntity<?> themKhoiKienThuc(@RequestBody KhoiKienThuc khoiKienThuc) {
        if (khoiKienThuc.getTenKhoiKienThuc() == null || khoiKienThuc.getTenKhoiKienThuc().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên khối kiến thức không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        KhoiKienThuc savedKhoiKienThuc = khoiKienThucService.themKhoiKienThuc(khoiKienThuc);
        return ResponseEntity.ok(savedKhoiKienThuc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatKhoiKienThuc(
            @PathVariable Integer id,
            @RequestBody KhoiKienThuc khoiKienThuc) {
        if (khoiKienThuc.getTenKhoiKienThuc() == null || khoiKienThuc.getTenKhoiKienThuc().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên khối kiến thức không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        khoiKienThuc.setIdKhoiKienThuc(id);
        KhoiKienThuc updatedKhoiKienThuc = khoiKienThucService.capNhatKhoiKienThuc(khoiKienThuc);
        return ResponseEntity.ok(updatedKhoiKienThuc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaKhoiKienThuc(@PathVariable Integer id) {
        khoiKienThucService.xoaKhoiKienThuc(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idKhoiKienThuc}/kien-thuc/{idKienThuc}")
    public ResponseEntity<?> themKienThucVaoKhoi(
            @PathVariable Integer idKhoiKienThuc,
            @PathVariable Integer idKienThuc) {
        KhoiKienThuc updatedKhoiKienThuc = khoiKienThucService.themKienThucVaoKhoi(idKhoiKienThuc, idKienThuc);
        return ResponseEntity.ok(updatedKhoiKienThuc);
    }

    @DeleteMapping("/{idKhoiKienThuc}/kien-thuc/{idKienThuc}")
    public ResponseEntity<?> xoaKienThucKhoiKhoi(
            @PathVariable Integer idKhoiKienThuc,
            @PathVariable Integer idKienThuc) {
        KhoiKienThuc updatedKhoiKienThuc = khoiKienThucService.xoaKienThucKhoiKhoi(idKhoiKienThuc, idKienThuc);
        return ResponseEntity.ok(updatedKhoiKienThuc);
    }

    @GetMapping
    public List<KhoiKienThuc> layTatCaKhoiKienThuc() {
        return khoiKienThucService.layTatCaKhoiKienThuc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhoiKienThuc> layKhoiKienThucById(@PathVariable Integer id) {
        KhoiKienThuc khoiKienThuc = khoiKienThucService.layKhoiKienThucById(id);
        return khoiKienThuc != null ? ResponseEntity.ok(khoiKienThuc) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{idKhoiKienThuc}/kien-thuc-moi")
    public ResponseEntity<?> themKienThucMoiVaoKhoi(
            @PathVariable Integer idKhoiKienThuc,
            @RequestBody Map<String, String> request) {
        String tenKienThuc = request.get("tenKienThuc");
        String loaiHocPhan = request.get("loaiHocPhan");

        if (tenKienThuc == null || tenKienThuc.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên kiến thức không được để trống");
            return ResponseEntity.badRequest().body(error);
        }

        if (loaiHocPhan == null || loaiHocPhan.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Loại học phần không được để trống");
            return ResponseEntity.badRequest().body(error);
        }

        KhoiKienThuc updatedKhoiKienThuc = khoiKienThucService.themKienThucMoiVaoKhoi(
                idKhoiKienThuc, tenKienThuc, loaiHocPhan);
        return ResponseEntity.ok(updatedKhoiKienThuc);
    }

    @GetMapping("/kien-thuc/{kienThucId}")
    public List<KhoiKienThuc> getKhoiKienThucByKienThucId(@PathVariable Integer kienThucId) {
        return khoiKienThucService.findByKienThucId(kienThucId);
    }
} 