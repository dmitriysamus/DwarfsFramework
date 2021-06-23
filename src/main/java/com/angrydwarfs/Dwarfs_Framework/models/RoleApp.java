package com.angrydwarfs.Dwarfs_Framework.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles_app")
@Getter
@Setter
public class RoleApp extends AbstractRole {

    @Enumerated(EnumType.STRING)
    @Column(name="ROLE_NAME")
    private ERolesApp roleName;

    public RoleApp (ERolesApp roleName) {
        this.roleName = roleName;
    }

    public RoleApp () {
        this.roleName = ERolesApp.COMMON;
    }

}
