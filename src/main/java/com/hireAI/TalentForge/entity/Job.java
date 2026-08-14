package com.hireAI.TalentForge.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Long salary;
    private String location;

    @ManyToOne
    @JoinColumn(name="recruiter_id")
    private User recruiter;

}
