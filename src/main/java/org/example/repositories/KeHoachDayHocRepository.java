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
    
    @Query(value = "SELECT * FROM ctdt_kehoachdayhoc WHERE JSON_CONTAINS(idHocKy, CAST(:hocKy AS JSON), '$') = 1", nativeQuery = true)
    List<KeHoachDayHoc> findByHocKy(@Param("hocKy") Integer hocKy);
    
    @Query(value = "SELECT * FROM ctdt_kehoachdayhoc WHERE idChuyenNganh = :idChuyenNganh AND JSON_CONTAINS(idHocKy, CAST(:hocKy AS JSON), '$') = 1", nativeQuery = true)
    List<KeHoachDayHoc> findByIdChuyenNganhAndHocKy(@Param("idChuyenNganh") Integer idChuyenNganh, @Param("hocKy") Integer hocKy);
    
    @Query("SELECT k FROM KeHoachDayHoc k WHERE LOWER(k.tenChuyenNganh) LIKE LOWER(CONCAT('%', :tenChuyenNganh, '%'))")
    List<KeHoachDayHoc> findByTenChuyenNganhContainingIgnoreCase(@Param("tenChuyenNganh") String tenChuyenNganh);
} 