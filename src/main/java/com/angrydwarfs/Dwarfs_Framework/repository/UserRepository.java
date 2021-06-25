package com.angrydwarfs.Dwarfs_Framework.repository;

import com.angrydwarfs.Dwarfs_Framework.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUsername(String userName);
    Optional<User> findById(Long id);

    Boolean existsByUsername(String username);
    Boolean existsByUserEmail(String userEmail);

}
