package com.hireAI.TalentForge.dto.job;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobCreationResponse {
    private boolean success;
    private String message;
    private String jobId;
}
