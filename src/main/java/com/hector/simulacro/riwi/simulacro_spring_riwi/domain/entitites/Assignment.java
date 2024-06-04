package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11)")
    private Long id;


    @Column(length = 100, name = "assignment_title")
    private String assignmentTitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "lesson_id")
    private Lesson lessonId;

    @OneToMany(mappedBy = "assignmentId", cascade = CascadeType.ALL)
    private Set<Submission> submissions = new HashSet<>();
}
