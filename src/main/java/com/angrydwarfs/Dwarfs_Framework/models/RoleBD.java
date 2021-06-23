package com.angrydwarfs.Dwarfs_Framework.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "roles_bd")
@Getter
@Setter
@ToString(of = {"roleName"})
public class RoleBD extends AbstractRole {

    @Enumerated(EnumType.STRING)
    @Column(name="ROLE_NAME")
    private ERolesBD roleName;

    public RoleBD(ERolesBD roleName) {
        this.roleName = roleName;
    }
}
