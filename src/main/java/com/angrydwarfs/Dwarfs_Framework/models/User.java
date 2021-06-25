package com.angrydwarfs.Dwarfs_Framework.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="users")
@NoArgsConstructor
public final class User extends AbstractUser {

    @Column(name="ROLE_BD", nullable = false)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles_bd",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleBD> roleBD = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles_app",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleApp> roleApp = new HashSet<>();

    public User (String username, String userEmail, String password) {
        super(username, userEmail, password);
    }


}
