package org.example.controllers;

import org.example.models.NguoiDung;
import org.example.services.NguoiDungServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/nguoidung")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class NguoiDungController {

    @Autowired
    private NguoiDungServices nguoiDungServices;

    // Đăng ký tài khoản mới
    @PostMapping("/dangky")
    public ResponseEntity<?> dangKy(@RequestBody NguoiDung nguoiDung) {
        if (nguoiDung.getUserName() == null || nguoiDung.getUserName().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên đăng nhập không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        if (nguoiDung.getPassword() == null || nguoiDung.getPassword().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Mật khẩu không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        NguoiDung newUser = nguoiDungServices.dangKy(nguoiDung);
        return ResponseEntity.ok(newUser);
    }

    // Đăng nhập
    @PostMapping("/dangnhap")
    public ResponseEntity<?> dangNhap(@RequestBody Map<String, String> credentials) {
        String userName = credentials.get("userName");
        String password = credentials.get("password");
        
        if (userName == null || userName.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên đăng nhập không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        if (password == null || password.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Mật khẩu không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        
        NguoiDung user = nguoiDungServices.dangNhap(userName, password);
        return ResponseEntity.ok(user);
    }

    // Lấy thông tin người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> layThongTinNguoiDung(@PathVariable("id") int id) {
        NguoiDung user = nguoiDungServices.layThongTinNguoiDung(id);
        return ResponseEntity.ok(user);
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatThongTin(
            @PathVariable("id") int id,
            @RequestBody NguoiDung nguoiDung) {
        if (nguoiDung.getUserName() == null || nguoiDung.getUserName().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên đăng nhập không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        nguoiDung.setId(id);
        NguoiDung updatedUser = nguoiDungServices.capNhatThongTin(nguoiDung);
        return ResponseEntity.ok(updatedUser);
    }

    // Đổi mật khẩu
    @PutMapping("/{id}/doimatkhau")
    public ResponseEntity<?> doiMatKhau(
            @PathVariable("id") int id,
            @RequestBody Map<String, String> passwords) {
        String matKhauCu = passwords.get("matKhauCu");
        String matKhauMoi = passwords.get("matKhauMoi");
        
        if (matKhauCu == null || matKhauCu.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Mật khẩu cũ không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        if (matKhauMoi == null || matKhauMoi.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Mật khẩu mới không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        
        NguoiDung updatedUser = nguoiDungServices.doiMatKhau(id, matKhauCu, matKhauMoi);
        return ResponseEntity.ok(updatedUser);
    }

    // Xóa người dùng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaNguoiDung(@PathVariable("id") int id) {
        nguoiDungServices.xoaNguoiDung(id);
        return ResponseEntity.ok(Map.of("message", "Xóa người dùng thành công"));
    }

    // Lấy danh sách người dùng
    @GetMapping
    public ResponseEntity<?> layDanhSachNguoiDung() {
        List<NguoiDung> users = nguoiDungServices.layDanhSachNguoiDung();
        return ResponseEntity.ok(users);
    }

    // Thêm tài khoản mới (yêu cầu quyền admin)
    @PostMapping("/admin/them")
    public ResponseEntity<?> themTaiKhoan(
            @RequestBody NguoiDung nguoiDung,
            @RequestHeader("Admin-Id") int adminId) {
        if (nguoiDung.getUserName() == null || nguoiDung.getUserName().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên đăng nhập không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        if (nguoiDung.getPassword() == null || nguoiDung.getPassword().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Mật khẩu không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        NguoiDung newUser = nguoiDungServices.themTaiKhoan(nguoiDung, adminId);
        return ResponseEntity.ok(newUser);
    }

    // Cập nhật role người dùng (yêu cầu quyền admin)
    @PutMapping("/{id}/role")
    public ResponseEntity<?> capNhatRole(
            @PathVariable("id") int id,
            @RequestParam int newRole,
            @RequestHeader("Admin-Id") int adminId) {
        NguoiDung updatedUser = nguoiDungServices.capNhatRole(id, newRole, adminId);
        return ResponseEntity.ok(updatedUser);
    }

    // Tìm kiếm người dùng
    @GetMapping("/timkiem")
    public ResponseEntity<?> timKiemNguoiDung(
            @RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Từ khóa tìm kiếm không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<NguoiDung> users = nguoiDungServices.timKiem(keyword);
        return ResponseEntity.ok(users);
    }

    // Kiểm tra người dùng có tồn tại hay không
    @GetMapping("/kiemtra/{id}")
    public ResponseEntity<?> kiemTraNguoiDungTonTai(@PathVariable("id") int id) {
        String message = nguoiDungServices.kiemTraNguoiDungTonTai(id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}
