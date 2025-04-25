package org.example.repositories;

import org.example.models.KienThuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KienThucRepository extends JpaRepository<KienThuc, Integer> {
} 