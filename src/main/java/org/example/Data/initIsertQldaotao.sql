
INSERT INTO `danhmuckhoikienthuc` (`idKhoiKienThuc`, `tenKhoiKienThuc`, `tenKienThuc`, `hocPhan`, `loaiHocPhan`) VALUES
(1, 'Kiến thức chung', 'Triết học Mác - Lênin', 'PHI101', 'Bắt buộc'),
(2, 'Kiến thức chuyên ngành', 'Lập trình hướng đối tượng', 'OOP201', 'Bắt buộc'),
(3, 'Kiến thức bổ trợ', 'Tiếng Anh cơ bản', 'ENG101', 'Tự chọn');

-- Chèn dữ liệu vào bảng diem
INSERT INTO `diem` (`idCotDiem`, `maSV`, `tenSV`, `diemChuyenCan`, `diemThucHanh`, `diemGiuaKy`, `diemCuoiKy`, `bangDiemMon`, `hocKy`, `nam`, `lop`) VALUES
(1, 'SV001', 'Nguyễn Văn A', 8.5, 7.0, 6.5, 7.5, 'OOP201', 1, '2024-2025', 'CNTT01'),
(2, 'SV002', 'Trần Thị B', 9.0, 8.0, 7.0, 8.5, 'PHI101', 1, '2024-2025', 'CNTT02'),
(3, 'SV003', 'Lê Văn C', 7.5, 6.5, 6.0, 7.0, 'ENG101', 2, '2024-2025', 'CNTT01');

-- Chèn dữ liệu vào bảng giangvien
INSERT INTO `giangvien` (`maGiangVien`, `hoTenGV`, `chucDanh`, `namPhong`, `trinhDo`, `nuoc`, `namTotNghiep`) VALUES
('GV001', 'Nguyễn Thị D', 'Giảng viên', '2015', 'Thạc sĩ', 'Việt Nam', '2012'),
('GV002', 'Trần Văn E', 'Phó Giáo sư', '2020', 'Tiến sĩ', 'Pháp', '2018'),
('GV003', 'Lê Thị F', 'Giảng viên', '2018', 'Thạc sĩ', 'Việt Nam', '2016');

-- Chèn dữ liệu vào bảng hocphan
INSERT INTO `hocphan` (`maHP`, `tenHP`, `soTinChi`, `soTietLyThuyet`, `soTietThucHanh`, `soTietThucTap`, `loaiHocPhan`, `tongSoTiet`, `heSoHocPhan`) VALUES
('PHI101', 'Triết học Mác - Lênin', 3, 45, 0, 0, 1, 45, 1),
('OOP201', 'Lập trình hướng đối tượng', 4, 30, 30, 0, 1, 60, 2),
('ENG101', 'Tiếng Anh cơ bản', 2, 15, 15, 0, 2, 30, 1);

-- Chèn dữ liệu vào bảng kehoachdayhoc
INSERT INTO `kehoachdayhoc` (`idChuyenNganh`, `tenChuyenNganh`, `hocKy`) VALUES
(1, 'Công nghệ thông tin', 1),
(2, 'Kinh tế', 2),
(3, 'Ngôn ngữ Anh', 1);

-- Chèn dữ liệu vào bảng muctieudaotao
INSERT INTO `muctieudaotao` (`mucTieu`) VALUES
('Đào tạo sinh viên có kiến thức chuyên môn vững vàng, kỹ năng thực hành tốt, đáp ứng nhu cầu xã hội.'),
('Phát triển tư duy sáng tạo và khả năng nghiên cứu khoa học cho sinh viên.'),
('Xây dựng phẩm chất đạo đức, trách nhiệm nghề nghiệp cho thế hệ trẻ.');

-- Chèn dữ liệu vào bảng phanconggiangday
INSERT INTO `phanconggiangday` (`idPhanCong`, `maGiangVien`, `tenSV`, `maHP`, `tenHP`, `khoa`, `soTiet`, `nhomDay`) VALUES
(1, 'GV001', 'Nguyễn Văn A', 'OOP201', 'Lập trình hướng đối tượng', 'CNTT', 60, 1),
(2, 'GV002', 'Trần Thị B', 'PHI101', 'Triết học Mác - Lênin', 'Khoa học Xã hội', 45, 2),
(3, 'GV003', 'Lê Văn C', 'ENG101', 'Tiếng Anh cơ bản', 'Ngoại ngữ', 30, 1);

-- Chèn dữ liệu vào bảng thongtinchung
INSERT INTO `thongtinchung` (`idThongTinChung`, `tenChuongTrinh`, `bac`, `loaiBang`, `loaiHinhDaoTao`, `thoiGian`, `soTinChi`, `khoaQuanLy`, `ngonNgu`, `khoaTuyen`) VALUES
(1, 'Cử nhân CNTT', 'Đại học', 'Chính quy', 'Tập trung', '4 năm', 120, 'CNTT', 'Tiếng Việt', '2024'),
(2, 'Cử nhân Kinh tế', 'Đại học', 'Chính quy', 'Tập trung', '4 năm', 130, 'Kinh tế', 'Tiếng Việt', '2024'),
(3, 'Cử nhân Ngôn ngữ Anh', 'Đại học', 'Chính quy', 'Tập trung', '4 năm', 125, 'Ngoại ngữ', 'Tiếng Anh', '2024');

-- Chèn dữ liệu vào bảng user
INSERT INTO `nguoidung` (`idTaiKhoan`, `userName`, `userEmail`, `password`) VALUES
(1, 'admin', 'admin@sgu.edu.vn', 'hashed_password_1'),
(2, 'gv001', 'gv001@sgu.edu.vn', 'hashed_password_2'),
(3, 'sv001', 'sv001@sgu.edu.vn', 'hashed_password_3');