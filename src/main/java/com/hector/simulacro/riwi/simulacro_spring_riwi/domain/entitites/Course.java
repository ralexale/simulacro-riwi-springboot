package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11)")
    private Long id;


    @Column(length = 100, name = "course_name")
    private String courseName;

    @Column(columnDefinition = "TEXT")
    private String description;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "instructor_id")
    private User instructorId;

    @OneToMany(mappedBy = "courseId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = false)
    private Set<Enrollment> enrollments = new HashSet<>();

    @OneToMany(mappedBy = "courseId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = false)
    private Set<Message> messages = new HashSet<>();


    @OneToMany(mappedBy = "courseId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = false)
    private Set<Lesson> lessons = new HashSet<>();
}
