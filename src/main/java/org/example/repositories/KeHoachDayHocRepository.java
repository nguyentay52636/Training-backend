package org.example.repositories;

import org.example.models.KeHoachDayHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KeHoachDayHocRepository extends JpaRepository<KeHoachDayHoc, Integer> {
    
    boolean existsByIdChuyenNganh(Integer idChuyenNganh);
    
    List<KeHoachDayHoc> findByHocKyThucHien(Integer hocKyThucHien);
    
    List<KeHoachDayHoc> findByIdChuyenNganhAndHocKyThucHien(Integer idChuyenNganh, Integer hocKyThucHien);
    
    @Query(value = "SELECT * FROM ctdt_kehoachdayhoc WHERE hocKyThucHien = :hocKy AND JSON_CONTAINS(idHocPhan, CAST(:hocPhanId AS JSON), '$') = 1", nativeQuery = true)
    List<KeHoachDayHoc> findByHocPhanIdAndHocKyThucHien(@Param("hocPhanId") Integer hocPhanId, @Param("hocKy") Integer hocKy);
    
    @Query("SELECT k FROM KeHoachDayHoc k WHERE LOWER(k.tenChuyenNganh) LIKE LOWER(CONCAT('%', :tenChuyenNganh, '%'))")
    List<KeHoachDayHoc> findByTenChuyenNganhContainingIgnoreCase(@Param("tenChuyenNganh") String tenChuyenNganh);
} 