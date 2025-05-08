package org.example.repositories;

import org.example.models.HocKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HocKyRepository extends JpaRepository<HocKy, Integer> {
} 