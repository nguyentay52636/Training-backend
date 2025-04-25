package org.example.repositories;

import org.example.models.KhoiKienThuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoiKienThucRepository extends JpaRepository<KhoiKienThuc, Integer> {
    boolean existsByIdKhoiKienThuc(Integer idKhoiKienThuc);
    
    @Query("SELECT k FROM KhoiKienThuc k WHERE k.idKienThuc LIKE %:idKienThuc%")
    List<KhoiKienThuc> findByKienThucId(@Param("idKienThuc") String idKienThuc);
    
    @Query("SELECT k FROM KhoiKienThuc k WHERE LOWER(k.tenKhoiKienThuc) LIKE LOWER(CONCAT('%', :tenKhoiKienThuc, '%'))")
    List<KhoiKienThuc> findByTenKhoiKienThucContainingIgnoreCase(@Param("tenKhoiKienThuc") String tenKhoiKienThuc);
} 