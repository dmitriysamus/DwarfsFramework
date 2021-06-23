package com.angrydwarfs.Dwarfs_Framework.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class AbstractRole implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;


}
