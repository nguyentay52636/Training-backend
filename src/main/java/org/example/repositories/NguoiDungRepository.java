package org.example.repositories;

import org.example.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    
    // Find user by username
    Optional<NguoiDung> findByUserName(String userName);
    
    Optional<NguoiDung> findByUserEmail(String userEmail);
    
    boolean existsByUserName(String userName);
    
    boolean existsByUserEmail(String userEmail);
    
    // Find user by username and password (for login)
    Optional<NguoiDung> findByUserNameAndPassword(String userName, String password);

    // Search methods
    List<NguoiDung> findByUserNameContainingIgnoreCase(String userName);
    List<NguoiDung> findByUserEmailContainingIgnoreCase(String userEmail);
    List<NguoiDung> findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(String userName, String userEmail);
}
