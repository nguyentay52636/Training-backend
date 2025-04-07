package org.example.services;

import org.example.models.NguoiDung;
import org.example.repositories.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        return nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
    }

    // Cập nhật thông tin người dùng
    public NguoiDung capNhatThongTin(NguoiDung nguoiDung) {
        // Kiểm tra người dùng tồn tại
        if (!nguoiDungRepository.existsById(nguoiDung.getIdTaiKhoan())) {
            throw new RuntimeException("Người dùng không tồn tại");
        }

        // Kiểm tra email mới có bị trùng không
        Optional<NguoiDung> existingUser = nguoiDungRepository.findByUserEmail(nguoiDung.getUserEmail());
        if (existingUser.isPresent() && existingUser.get().getIdTaiKhoan() != nguoiDung.getIdTaiKhoan()) {
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
        if (!nguoiDungRepository.existsById(id)) {
            throw new RuntimeException("Người dùng không tồn tại");
        }
        nguoiDungRepository.deleteById(id);
    }

    // Lấy danh sách tất cả người dùng
    public List<NguoiDung> layDanhSachNguoiDung() {

        return nguoiDungRepository.findAll();
    }

    // Thêm tài khoản mới (dành cho admin)
    public NguoiDung themTaiKhoan(NguoiDung nguoiDung) {
        // Kiểm tra username đã tồn tại
        if (nguoiDungRepository.existsByUserName(nguoiDung.getUserName())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        // Kiểm tra email đã tồn tại
        if (nguoiDungRepository.existsByUserEmail(nguoiDung.getUserEmail())) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(nguoiDung.getPassword());
        nguoiDung.setPassword(encodedPassword);

        // Lưu người dùng mới
        return nguoiDungRepository.save(nguoiDung);
    }

    // Sửa thông tin tài khoản (dành cho admin)
    public NguoiDung suaTaiKhoan(int id, NguoiDung nguoiDung) {
        // Kiểm tra người dùng tồn tại
        NguoiDung existingUser = layThongTinNguoiDung(id);

        // Kiểm tra email mới có bị trùng không
        Optional<NguoiDung> userWithEmail = nguoiDungRepository.findByUserEmail(nguoiDung.getUserEmail());
        if (userWithEmail.isPresent() && userWithEmail.get().getIdTaiKhoan() != id) {
            throw new RuntimeException("Email đã được sử dụng bởi người dùng khác");
        }

        // Cập nhật thông tin
        existingUser.setUserName(nguoiDung.getUserName());
        existingUser.setUserEmail(nguoiDung.getUserEmail());
        
        // Nếu có mật khẩu mới thì cập nhật
        if (nguoiDung.getPassword() != null && !nguoiDung.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(nguoiDung.getPassword());
            existingUser.setPassword(encodedPassword);
        }

        return nguoiDungRepository.save(existingUser);
    }

    // Khóa tài khoản
    public void khoaTaiKhoan(int id) {
        NguoiDung nguoiDung = layThongTinNguoiDung(id);
        // Thêm trường isLocked vào model NguoiDung nếu chưa có
        // nguoiDung.setLocked(true);
        nguoiDungRepository.save(nguoiDung);
    }

    // Mở khóa tài khoản
    public void moKhoaTaiKhoan(int id) {
        NguoiDung nguoiDung = layThongTinNguoiDung(id);
        // Thêm trường isLocked vào model NguoiDung nếu chưa có
        // nguoiDung.setLocked(false);
        nguoiDungRepository.save(nguoiDung);
    }

    // Tìm kiếm người dùng theo tên
    public List<NguoiDung> timKiemTheoTen(String ten) {
        return nguoiDungRepository.findByUserNameContainingIgnoreCase(ten);
    }

    // Tìm kiếm người dùng theo email
    public List<NguoiDung> timKiemTheoEmail(String email) {
        return nguoiDungRepository.findByUserEmailContainingIgnoreCase(email);
    }

    // Tìm kiếm người dùng theo cả tên và email
    public List<NguoiDung> timKiem(String keyword) {
        return nguoiDungRepository.findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(keyword, keyword);
    }
}
