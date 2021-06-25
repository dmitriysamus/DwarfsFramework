package com.angrydwarfs.Dwarfs_Framework.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class AbstractRole implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;


}
