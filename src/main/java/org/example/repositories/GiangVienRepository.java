package org.example.repositories;

import org.example.models.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, Integer> {
    Optional<GiangVien> findByIdTaiKhoan(Integer idTaiKhoan);
    void deleteByIdTaiKhoan(Integer idTaiKhoan);
} 