package com.angrydwarfs.Dwarfs_Framework.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "username", "password", "userEmail"})
@EqualsAndHashCode(of = {"id"})
public class AbstractUser implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Username cannot be empty")
    @Column(name="USER_NAME", length = 100, nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Column(name="USER_PASSWORD", length = 100)
    private String password;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    @Column(name="USER_EMAIL", length = 100, nullable = false)
    private String userEmail;

    @Column(name="USER_CREATION_DATE", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(name="USER_LAST_VISITED_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisitedDate;

    public AbstractUser (String username, String userEmail, String password) {
        this.username = username;
        this.userEmail = userEmail;
        this.password = password;
    }

}
