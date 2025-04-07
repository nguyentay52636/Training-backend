package org.example.controllers;

import org.example.models.NguoiDung;
import org.example.services.NguoiDungServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nguoidung")
public class NguoiDungController {

    @Autowired
    private NguoiDungServices nguoiDungServices;

    @PostMapping("/dangky")
    public ResponseEntity<?> dangKy(@RequestBody NguoiDung nguoiDung) {
        try {
            NguoiDung newUser = nguoiDungServices.dangKy(nguoiDung);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/dangnhap")
    public ResponseEntity<?> dangNhap(@RequestBody Map<String, String> credentials) {
        try {
            String userName = credentials.get("userName");
            String password = credentials.get("password");
            NguoiDung user = nguoiDungServices.dangNhap(userName, password);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> layThongTinNguoiDung(@PathVariable int id) {
        try {
            NguoiDung user = nguoiDungServices.layThongTinNguoiDung(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatThongTin(@PathVariable int id, @RequestBody NguoiDung nguoiDung) {
        try {
            nguoiDung.setIdTaiKhoan(id);
            NguoiDung updatedUser = nguoiDungServices.capNhatThongTin(nguoiDung);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/doimatkhau")
    public ResponseEntity<?> doiMatKhau(
            @PathVariable int id,
            @RequestBody Map<String, String> passwords) {
        try {
            String matKhauCu = passwords.get("matKhauCu");
            String matKhauMoi = passwords.get("matKhauMoi");
            nguoiDungServices.doiMatKhau(id, matKhauCu, matKhauMoi);
            return ResponseEntity.ok(Map.of("message", "Đổi mật khẩu thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaNguoiDung(@PathVariable int id) {
        try {
            nguoiDungServices.xoaNguoiDung(id);
            return ResponseEntity.ok(Map.of("message", "Xóa người dùng thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<NguoiDung>> layDanhSachNguoiDung() {
        List<NguoiDung> users = nguoiDungServices.layDanhSachNguoiDung();
        return ResponseEntity.ok(users);
    }
}