package org.example.controllers;

import org.example.models.Diem;
import org.example.services.DiemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/diem")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class DiemController {

    private final DiemService diemService;

    public DiemController(DiemService diemService) {
        this.diemService = diemService;
    }

    @PostMapping
    public ResponseEntity<?> themDiem(@Valid @RequestBody Diem diem) {
        Diem diemMoi = diemService.themDiem(diem);
        return ResponseEntity.ok(diemMoi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> capNhatDiem(@PathVariable Integer id, @Valid @RequestBody Diem diem) {
        Diem diemCapNhat = diemService.capNhatDiem(id, diem);
        return ResponseEntity.ok(diemCapNhat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaDiem(@PathVariable Integer id) {
        diemService.xoaDiem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> layDiemTheoId(@PathVariable Integer id) {
        Diem diem = diemService.layDiemTheoId(id);
        return ResponseEntity.ok(diem);
    }

    @GetMapping
    public ResponseEntity<?> layTatCaDiem() {
        List<Diem> dsDiem = diemService.layTatCaDiem();
        return ResponseEntity.ok(dsDiem);
    }

    @GetMapping("/timkiem/masv")
    public ResponseEntity<?> timKiemTheoMaSV(@RequestParam String maSV) {
        if (maSV == null || maSV.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Mã sinh viên không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<Diem> dsDiem = diemService.timKiemTheoMaSV(maSV);
        return ResponseEntity.ok(dsDiem);
    }

    @GetMapping("/timkiem/tensv")
    public ResponseEntity<?> timKiemTheoTenSV(@RequestParam String tenSV) {
        if (tenSV == null || tenSV.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Tên sinh viên không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<Diem> dsDiem = diemService.timKiemTheoTenSV(tenSV);
        return ResponseEntity.ok(dsDiem);
    }

    @GetMapping("/tim-kiem")
    public ResponseEntity<?> timKiem(
            @RequestParam(required = false) String maSV,
            @RequestParam(required = false) String tenSV,
            @RequestParam(required = false) String lop,
            @RequestParam(required = false) Integer hocKy,
            @RequestParam(required = false) String nam,
            @RequestParam(required = false) String bangDiemMon) {
        
        if (maSV == null && tenSV == null && lop == null && hocKy == null && nam == null && bangDiemMon == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Vui lòng cung cấp ít nhất một tiêu chí tìm kiếm");
            return ResponseEntity.badRequest().body(error);
        }

        List<Diem> ketQua;
        if (maSV != null) {
            ketQua = diemService.timKiemTheoMaSV(maSV);
        } else if (tenSV != null) {
            ketQua = diemService.timKiemTheoTenSV(tenSV);
        } else if (lop != null) {
            ketQua = diemService.timKiemTheoLop(lop);
        } else if (hocKy != null && nam != null) {
            ketQua = diemService.timKiemTheoHocKyVaNam(hocKy, nam);
        } else if (bangDiemMon != null) {
            ketQua = diemService.timKiemTheoBangDiemMon(bangDiemMon);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Vui lòng cung cấp đầy đủ thông tin tìm kiếm");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(ketQua);
    }

    @GetMapping("/timkiem/lop")
    public ResponseEntity<?> timKiemTheoLop(@RequestParam String lop) {
        if (lop == null || lop.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lớp không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<Diem> dsDiem = diemService.timKiemTheoLop(lop);
        return ResponseEntity.ok(dsDiem);
    }

    @GetMapping("/timkiem/hocky-nam")
    public ResponseEntity<?> timKiemTheoHocKyVaNam(
            @RequestParam Integer hocKy,
            @RequestParam String nam) {
        if (hocKy == null || nam == null || nam.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Học kỳ và năm không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<Diem> dsDiem = diemService.timKiemTheoHocKyVaNam(hocKy, nam);
        return ResponseEntity.ok(dsDiem);
    }

    @GetMapping("/timkiem/hocphan")
    public ResponseEntity<?> timKiemTheoHocPhan(@RequestParam String hocPhan) {
        if (hocPhan == null || hocPhan.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Học phần không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<Diem> dsDiem = diemService.timKiemTheoHocPhan(hocPhan);
        return ResponseEntity.ok(dsDiem);
    }

    @GetMapping("/timkiem/lop-hocphan")
    public ResponseEntity<?> timKiemTheoLopVaHocPhan(
            @RequestParam String lop,
            @RequestParam String hocPhan) {
        if (lop == null || lop.isEmpty() || hocPhan == null || hocPhan.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lớp và học phần không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        List<Diem> dsDiem = diemService.timKiemTheoLopVaHocPhan(lop, hocPhan);
        return ResponseEntity.ok(dsDiem);
    }

    @GetMapping("/{id}/diemtrungbinh")
    public ResponseEntity<?> tinhDiemTrungBinh(@PathVariable Integer id) {
        Diem diem = diemService.layDiemTheoId(id);
        float diemTB = diemService.tinhDiemTrungBinh(diem);
        return ResponseEntity.ok(Map.of("diemTrungBinh", diemTB));
    }

    @GetMapping("/thongke")
    public ResponseEntity<?> thongKeDiem(
            @RequestParam String lop,
            @RequestParam String hocPhan) {
        if (lop == null || lop.isEmpty() || hocPhan == null || hocPhan.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lớp và học phần không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        Map<String, Long> thongKe = diemService.thongKeDiemTheoLop(lop, hocPhan);
        return ResponseEntity.ok(thongKe);
    }

    @GetMapping("/thong-ke/{lop}/{bangDiemMon}")
    public ResponseEntity<?> thongKe(
            @PathVariable String lop,
            @PathVariable String bangDiemMon) {
        if (lop == null || lop.isEmpty() || bangDiemMon == null || bangDiemMon.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Lớp và bảng điểm môn không được để trống");
            return ResponseEntity.badRequest().body(error);
        }
        
        Map<String, Object> thongKe = new HashMap<>();
        Float diemTrungBinh = diemService.tinhDiemTrungBinhLopMon(lop, bangDiemMon);
        thongKe.put("diemTrungBinh", diemTrungBinh);
        
        Map<String, Long> thongKeSoLuong = diemService.thongKeSoLuongDat(lop, bangDiemMon);
        thongKe.put("thongKeSoLuong", thongKeSoLuong);
        
        return ResponseEntity.ok(thongKe);
    }

    @GetMapping("/tim-kiem-diem/{min}/{max}")
    public ResponseEntity<?> timTheoDiem(
            @PathVariable Float min,
            @PathVariable Float max) {
        if (min > max) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Giá trị min không được lớn hơn max");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(diemService.timTheoDiemCuoiKy(min, max));
    }
}
