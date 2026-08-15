package com.hireAI.TalentForge.dto.job;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private Long salary;
    private String location;
    private String recruiterName;
}
