-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 06, 2025 lúc 10:32 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlybomon`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhmuckhoikienthuc`
--

CREATE TABLE `danhmuckhoikienthuc` (
  `idKhoiKienThuc` int(11) NOT NULL,
  `tenKhoiKienThuc` varchar(150) NOT NULL,
  `tenKienThuc` varchar(150) NOT NULL,
  `hocPhan` varchar(150) NOT NULL,
  `loaiHocPhan` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `diem`
--

CREATE TABLE `diem` (
  `idCotDiem` int(11) NOT NULL,
  `maSV` varchar(50) NOT NULL,
  `tenSV` varchar(255) NOT NULL,
  `diemChuyenCan` float NOT NULL,
  `diemThucHanh` float NOT NULL,
  `diemGiuaKy` float NOT NULL,
  `diemCuoiKy` float NOT NULL,
  `bangDiemMon` varchar(255) NOT NULL,
  `hocKy` int(10) NOT NULL,
  `nam` varchar(50) NOT NULL,
  `lop` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giangvien`
--

CREATE TABLE `giangvien` (
  `maGiangVien` varchar(50) NOT NULL,
  `hoTenGV` varchar(150) NOT NULL,
  `chucDanh` varchar(150) NOT NULL,
  `namPhong` varchar(50) NOT NULL,
  `trinhDo` varchar(50) NOT NULL,
  `nuoc` varchar(150) NOT NULL,
  `namTotNghiep` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hocphan`
--

CREATE TABLE `hocphan` (
  `maHP` varchar(150) NOT NULL,
  `tenHP` varchar(150) NOT NULL,
  `soTinChi` int(50) NOT NULL,
  `soTietLyThuyet` int(50) NOT NULL,
  `soTietThucHanh` int(50) NOT NULL,
  `soTietThucTap` int(50) NOT NULL,
  `loaiHocPhan` int(50) NOT NULL,
  `tongSoTiet` int(50) NOT NULL,
  `heSoHocPhan` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `kehoachdayhoc`
--

CREATE TABLE `kehoachdayhoc` (
  `idChuyenNganh` int(11) NOT NULL,
  `tenChuyenNganh` varchar(150) NOT NULL,
  `hocKy` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `muctieudaotao`
--

CREATE TABLE `muctieudaotao` (
  `mucTieu` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanconggiangday`
--

CREATE TABLE `phanconggiangday` (
  `idPhanCong` int(11) NOT NULL,
  `maGiangVien` varchar(150) NOT NULL,
  `tenSV` varchar(150) NOT NULL,
  `maHP` varchar(150) NOT NULL,
  `tenHP` varchar(150) NOT NULL,
  `khoa` varchar(150) NOT NULL,
  `soTiet` int(50) NOT NULL,
  `nhomDay` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtinchung`
--

CREATE TABLE `thongtinchung` (
  `idThongTinChung` int(11) NOT NULL,
  `tenChuongTrinh` varchar(50) NOT NULL,
  `bac` varchar(50) NOT NULL,
  `loaiBang` varchar(50) NOT NULL,
  `loaiHinhDaoTao` varchar(50) NOT NULL,
  `thoiGian` varchar(50) NOT NULL,
  `soTinChi` int(30) NOT NULL,
  `khoaQuanLy` varchar(50) NOT NULL,
  `ngonNgu` varchar(50) NOT NULL,
  `khoaTuyen` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `nguoidung` (
  `idTaiKhoan` int(11) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `userEmail` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `danhmuckhoikienthuc`
--
ALTER TABLE `danhmuckhoikienthuc`
  ADD PRIMARY KEY (`idKhoiKienThuc`);

--
-- Chỉ mục cho bảng `diem`
--
ALTER TABLE `diem`
  ADD PRIMARY KEY (`idCotDiem`);

--
-- Chỉ mục cho bảng `kehoachdayhoc`
--
ALTER TABLE `kehoachdayhoc`
  ADD PRIMARY KEY (`idChuyenNganh`);

--
-- Chỉ mục cho bảng `phanconggiangday`
--
ALTER TABLE `phanconggiangday`
  ADD PRIMARY KEY (`idPhanCong`);

--
-- Chỉ mục cho bảng `thongtinchung`
--
ALTER TABLE `thongtinchung`
  ADD PRIMARY KEY (`idThongTinChung`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`idTaiKhoan`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `danhmuckhoikienthuc`
--
ALTER TABLE `danhmuckhoikienthuc`
  MODIFY `idKhoiKienThuc` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `diem`
--
ALTER TABLE `diem`
  MODIFY `idCotDiem` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `kehoachdayhoc`
--
ALTER TABLE `kehoachdayhoc`
  MODIFY `idChuyenNganh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `phanconggiangday`
--
ALTER TABLE `phanconggiangday`
  MODIFY `idPhanCong` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `thongtinchung`
--
ALTER TABLE `thongtinchung`
  MODIFY `idThongTinChung` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `nguoidung`
  MODIFY `idTaiKhoan` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
