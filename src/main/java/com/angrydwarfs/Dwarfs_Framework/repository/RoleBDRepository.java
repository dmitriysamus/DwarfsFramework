package com.angrydwarfs.Dwarfs_Framework.repository;

import com.angrydwarfs.Dwarfs_Framework.models.RoleBD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleBDRepository extends JpaRepository <RoleBD, Long> {
}
