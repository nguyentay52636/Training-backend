package org.example.repositories;

import org.example.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    boolean existsByUserName(String userName);
    boolean existsByUserEmail(String userEmail);
    Optional<NguoiDung> findByUserName(String userName);
    Optional<NguoiDung> findByUserEmail(String userEmail);
    List<NguoiDung> findByUserNameContainingIgnoreCase(String userName);
    List<NguoiDung> findByUserEmailContainingIgnoreCase(String email);
    List<NguoiDung> findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(String userName, String email);
}
