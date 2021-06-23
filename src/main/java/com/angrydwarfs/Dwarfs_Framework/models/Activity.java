package com.angrydwarfs.Dwarfs_Framework.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class Activity {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userActivities;

    @Column(name="ACTIVITY_CREATION_DATE", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @NotBlank(message = "Title cannot be empty")
    private String activityTitle;
    @NotBlank(message = "Body cannot be empty")
    private String activityBody;

    private Boolean isFree;

//    private String activityBody

}
