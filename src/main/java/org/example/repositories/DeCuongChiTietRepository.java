package org.example.repositories;

import org.example.models.DeCuongChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeCuongChiTietRepository extends JpaRepository<DeCuongChiTiet, Integer> {
} 