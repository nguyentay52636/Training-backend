package org.example.repositories;

import org.example.models.PhanCongGiangDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhanCongGiangDayRepository extends JpaRepository<PhanCongGiangDay, Integer> {
    List<PhanCongGiangDay> findByIdGiangVien(Integer idGiangVien);
    void deleteByIdGiangVien(Integer idGiangVien);
} 