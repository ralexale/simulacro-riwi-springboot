package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites;

import com.hector.simulacro.riwi.simulacro_spring_riwi.utils.enums.Role;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11)")
    private Long id;

    @Column(length = 50)
    private String username;

    private String password;

    @Column(length = 100)
    private String email;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = false)
    private Set<Enrollment> enrollments = new HashSet<>();


    @OneToMany(mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = false)
    private Set<Submission> submissions = new HashSet<>();

    @OneToMany(mappedBy = "instructorId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = false)
    private Set<Course> courses = new HashSet<>();


    @OneToMany(mappedBy = "senderId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Message> messageSender = new HashSet<>();


    @OneToMany(mappedBy = "receiverId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Message> messageReceiver = new HashSet<>();
}
