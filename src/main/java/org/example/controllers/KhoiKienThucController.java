package org.example.controllers;

import org.example.models.KhoiKienThuc;
import org.example.services.KhoiKienThucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khoi-kien-thuc")
public class KhoiKienThucController {
    @Autowired
    private KhoiKienThucService khoiKienThucService;

    @PostMapping
    public ResponseEntity<KhoiKienThuc> themKhoiKienThuc(@RequestBody KhoiKienThuc khoiKienThuc) {
        try {
            KhoiKienThuc savedKhoiKienThuc = khoiKienThucService.themKhoiKienThuc(khoiKienThuc);
            return ResponseEntity.ok(savedKhoiKienThuc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<KhoiKienThuc> capNhatKhoiKienThuc(
            @PathVariable Integer id,
            @RequestBody KhoiKienThuc khoiKienThuc) {
        try {
            khoiKienThuc.setIdKhoiKienThuc(id);
            KhoiKienThuc updatedKhoiKienThuc = khoiKienThucService.capNhatKhoiKienThuc(khoiKienThuc);
            return ResponseEntity.ok(updatedKhoiKienThuc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaKhoiKienThuc(@PathVariable Integer id) {
        try {
            khoiKienThucService.xoaKhoiKienThuc(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{idKhoiKienThuc}/kien-thuc/{idKienThuc}")
    public ResponseEntity<KhoiKienThuc> themKienThucVaoKhoi(
            @PathVariable Integer idKhoiKienThuc,
            @PathVariable Integer idKienThuc) {
        try {
            KhoiKienThuc updatedKhoiKienThuc = khoiKienThucService.themKienThucVaoKhoi(idKhoiKienThuc, idKienThuc);
            return ResponseEntity.ok(updatedKhoiKienThuc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{idKhoiKienThuc}/kien-thuc/{idKienThuc}")
    public ResponseEntity<KhoiKienThuc> xoaKienThucKhoiKhoi(
            @PathVariable Integer idKhoiKienThuc,
            @PathVariable Integer idKienThuc) {
        try {
            KhoiKienThuc updatedKhoiKienThuc = khoiKienThucService.xoaKienThucKhoiKhoi(idKhoiKienThuc, idKienThuc);
            return ResponseEntity.ok(updatedKhoiKienThuc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<KhoiKienThuc>> layTatCaKhoiKienThuc() {
        try {
            List<KhoiKienThuc> khoiKienThucs = khoiKienThucService.layTatCaKhoiKienThuc();
            return ResponseEntity.ok(khoiKienThucs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhoiKienThuc> layKhoiKienThucById(@PathVariable Integer id) {
        try {
            KhoiKienThuc khoiKienThuc = khoiKienThucService.layKhoiKienThucById(id);
            return ResponseEntity.ok(khoiKienThuc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
} 