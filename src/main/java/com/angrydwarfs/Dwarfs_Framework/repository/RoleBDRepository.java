package com.angrydwarfs.Dwarfs_Framework.repository;

import com.angrydwarfs.Dwarfs_Framework.models.ERolesBD;
import com.angrydwarfs.Dwarfs_Framework.models.RoleBD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleBDRepository extends JpaRepository <RoleBD, Long> {

    Optional <RoleBD> findByRoleName (ERolesBD eRolesBD);
}
