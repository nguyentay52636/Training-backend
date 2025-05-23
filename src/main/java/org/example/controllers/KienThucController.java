package org.example.controllers;

import org.example.models.KienThuc;
import org.example.services.KienThucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/kien-thuc")
public class KienThucController {
    @Autowired
    private KienThucService kienThucService;

    @PostMapping
    public ResponseEntity<?> themKienThuc(@RequestBody KienThuc kienThuc) {
        if (kienThuc.getTenKienThuc() == null || kienThuc.getTenKienThuc().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên kiến thức không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        KienThuc savedKienThuc = kienThucService.themKienThuc(kienThuc);
        return ResponseEntity.ok(savedKienThuc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatKienThuc(
            @PathVariable Integer id,
            @RequestBody KienThuc kienThuc) {
        if (kienThuc.getTenKienThuc() == null || kienThuc.getTenKienThuc().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên kiến thức không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        kienThuc.setIdKienThuc(id);
        KienThuc updatedKienThuc = kienThucService.capNhatKienThuc(kienThuc);
        return ResponseEntity.ok(updatedKienThuc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaKienThuc(@PathVariable Integer id) {
        kienThucService.xoaKienThuc(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idKienThuc}/hoc-phan/{idHocPhan}")
    public ResponseEntity<?> themHocPhanVaoKienThuc(
            @PathVariable Integer idKienThuc,
            @PathVariable Integer idHocPhan) {
        KienThuc updatedKienThuc = kienThucService.themHocPhanVaoKienThuc(idKienThuc, idHocPhan);
        return ResponseEntity.ok(updatedKienThuc);
    }

    @DeleteMapping("/{idKienThuc}/hoc-phan/{idHocPhan}")
    public ResponseEntity<?> xoaHocPhanKhoiKienThuc(
            @PathVariable Integer idKienThuc,
            @PathVariable Integer idHocPhan) {
        KienThuc updatedKienThuc = kienThucService.xoaHocPhanKhoiKienThuc(idKienThuc, idHocPhan);
        return ResponseEntity.ok(updatedKienThuc);
    }

    @GetMapping
    public List<KienThuc> layTatCaKienThuc() {
        return kienThucService.layTatCaKienThuc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KienThuc> layKienThucById(@PathVariable Integer id) {
        KienThuc kienThuc = kienThucService.layKienThucById(id);
        return kienThuc != null ? ResponseEntity.ok(kienThuc) : ResponseEntity.notFound().build();
    }

    @GetMapping("/hoc-phan/{hocPhanId}")
    public List<KienThuc> getKienThucByHocPhanId(@PathVariable Integer hocPhanId) {
        return kienThucService.findByHocPhanId(hocPhanId);
    }
}