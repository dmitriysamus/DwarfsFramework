package com.angrydwarfs.Dwarfs_Framework.payload.response;

import com.angrydwarfs.Dwarfs_Framework.models.RoleBD;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class JwtResponse {

    private Long id;
    private String username;
    private String userEmail;
    private List <String> rolesBD;

    public JwtResponse(Long id, String username, String userEmail, List<String> rolesBD) {
        this.id = id;
        this.username = username;
        this.userEmail = userEmail;
        this.rolesBD = rolesBD;
    }

}
