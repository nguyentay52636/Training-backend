package org.example.repositories;

import org.example.models.KienThuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KienThucRepository extends JpaRepository<KienThuc, Integer> {
    @Query(value = "SELECT * FROM ctdt_kienthuc WHERE JSON_CONTAINS(idHocPhan, CAST(:hocPhanId AS JSON), '$') = 1", nativeQuery = true)
    List<KienThuc> findByHocPhanId(@Param("hocPhanId") Integer hocPhanId);
    
    @Query("SELECT k FROM KienThuc k WHERE LOWER(k.tenKienThuc) LIKE LOWER(CONCAT('%', :tenKienThuc, '%'))")
    List<KienThuc> findByTenKienThucContainingIgnoreCase(@Param("tenKienThuc") String tenKienThuc);
    
    @Query("SELECT k FROM KienThuc k WHERE k.loaiHocPhan = :loaiHocPhan")
    List<KienThuc> findByLoaiHocPhan(@Param("loaiHocPhan") String loaiHocPhan);
} 