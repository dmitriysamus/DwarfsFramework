package com.angrydwarfs.Dwarfs_Framework.repository;

import com.angrydwarfs.Dwarfs_Framework.models.RoleApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAppRepository extends JpaRepository <RoleApp, Long> {
}
