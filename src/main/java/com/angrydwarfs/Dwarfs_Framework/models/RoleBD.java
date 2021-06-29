package com.angrydwarfs.Dwarfs_Framework.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles_bd")
@Getter
@Setter
@ToString(of = {"roleName"})
@NoArgsConstructor
public class RoleBD extends AbstractRole {

    @Enumerated(EnumType.STRING)
    @Column(name="ROLE_NAME", length = 20)
    private ERolesBD roleName;

    public RoleBD(ERolesBD roleName) {
        this.roleName = roleName;
    }
}
