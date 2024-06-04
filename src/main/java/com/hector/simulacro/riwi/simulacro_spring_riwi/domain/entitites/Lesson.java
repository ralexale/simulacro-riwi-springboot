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
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11)")
    private Long id;

    @Column(length = 100, name = "lesson_title")
    private String lessonTitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id")
    private Course courseId;

    @OneToMany(mappedBy = "lessonId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = false)
    private Set<Assignment> assignments = new HashSet<>();
}
