package org.example.services;

import org.example.models.NguoiDung;
import org.example.repositories.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungServices {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Đăng ký người dùng mới
    public NguoiDung dangKy(NguoiDung nguoiDung) {
        if (nguoiDungRepository.existsByUserName(nguoiDung.getUserName())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        if (nguoiDungRepository.existsByUserEmail(nguoiDung.getUserEmail())) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(nguoiDung.getPassword());
        nguoiDung.setPassword(encodedPassword);

        // Lưu người dùng mới
        return nguoiDungRepository.save(nguoiDung);
    }

    // Đăng nhập
    public NguoiDung dangNhap(String userName, String password) {
        Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findByUserName(userName);
        
        if (optionalNguoiDung.isPresent()) {
            NguoiDung nguoiDung = optionalNguoiDung.get();
            if (passwordEncoder.matches(password, nguoiDung.getPassword())) {
                return nguoiDung;
            }
        }
        throw new RuntimeException("Tên đăng nhập hoặc mật khẩu không đúng");
    }

    // Lấy thông tin người dùng theo ID
    public NguoiDung layThongTinNguoiDung(int id) {
        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(id);
        if (nguoiDung.isEmpty()) {
            throw new RuntimeException("Không tìm thấy người dùng");
        }
        return nguoiDung.get();
    }

    // Cập nhật thông tin người dùng
    public NguoiDung capNhatThongTin(NguoiDung nguoiDung) {
        Optional<NguoiDung> existingUser = nguoiDungRepository.findById(nguoiDung.getIdTaiKhoan());
        if (existingUser.isEmpty()) {
            throw new RuntimeException("Người dùng không tồn tại");
        }

        // Kiểm tra email mới có bị trùng không
        Optional<NguoiDung> existingEmailUser = nguoiDungRepository.findByUserEmail(nguoiDung.getUserEmail());
        if (existingEmailUser.isPresent() && existingEmailUser.get().getIdTaiKhoan() != nguoiDung.getIdTaiKhoan()) {
            throw new RuntimeException("Email đã được sử dụng bởi người dùng khác");
        }

        return nguoiDungRepository.save(nguoiDung);
    }

    // Đổi mật khẩu
    public void doiMatKhau(int id, String matKhauCu, String matKhauMoi) {
        NguoiDung nguoiDung = layThongTinNguoiDung(id);

        // Kiểm tra mật khẩu cũ
        if (!passwordEncoder.matches(matKhauCu, nguoiDung.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        // Mã hóa và cập nhật mật khẩu mới
        String encodedPassword = passwordEncoder.encode(matKhauMoi);
        nguoiDung.setPassword(encodedPassword);
        nguoiDungRepository.save(nguoiDung);
    }

    // Xóa người dùng
    public void xoaNguoiDung(int id) {
        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(id);
        if (nguoiDung.isEmpty()) {
            throw new RuntimeException("Người dùng không tồn tại");
        }
        nguoiDungRepository.deleteById(id);
    }

    // Lấy danh sách tất cả người dùng
    public List<NguoiDung> layDanhSachNguoiDung() {
        return nguoiDungRepository.findAll();
    }

    // Tìm kiếm người dùng theo từ khóa
    public List<NguoiDung> timKiem(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return nguoiDungRepository.findAll();
        }
        return nguoiDungRepository.findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(keyword, keyword);
    }
}
