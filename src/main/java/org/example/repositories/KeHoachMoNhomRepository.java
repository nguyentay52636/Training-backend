package org.example.repositories;

import org.example.models.KeHoachMoNhom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeHoachMoNhomRepository extends JpaRepository<KeHoachMoNhom, Integer> {
} 