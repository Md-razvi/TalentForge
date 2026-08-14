# TalentForge

### AI-Powered Recruitment & Interview Management Platform

TalentForge is a **Spring Boot-based recruitment platform** designed to simplify the hiring process by connecting recruiters and candidates through a structured recruitment workflow.

The platform allows recruiters to create and publish job openings, while candidates can browse available jobs and apply for positions. As part of the application process, candidates complete an **AI-powered assessment** that evaluates their technical responses and generates a score.

The AI evaluation helps recruiters identify promising candidates who can then be contacted for the next stage of the recruitment process — a personal interview.

---

## Core Idea

TalentForge combines a traditional recruitment workflow with AI-assisted candidate screening.

```text
                         TALENTFORGE
                              │
              ┌───────────────┴───────────────┐
              │                               │
          RECRUITER                        CANDIDATE
              │                               │
              ▼                               ▼
         Create Job                       View Jobs
              │                               │
              ▼                               ▼
        Publish Job  ◄──────────────────── Apply
              │
              ▼
        Applications
              │
              ▼
        AI Assessment
              │
              ▼
        AI Evaluation
              │
              ▼
       Candidate Score
              │
              ▼
      Recruiter Review
              │
              ▼
          Interview
```

The AI score is used as an **initial screening mechanism** to assist recruiters. The final decision to proceed with a candidate remains with the recruiter.

---

# Features

## Authentication & Security

TalentForge provides secure authentication and authorization for users.

* User Registration
* User Login
* JWT-based Authentication
* Role-Based Authorization
* Secure REST APIs
* Password Encryption using BCrypt

---

## Recruiter Module

Recruiters are responsible for creating job opportunities and reviewing candidates.

### Recruiters can:

* Create job postings
* Define job requirements
* Specify required experience level
* Specify job role and difficulty
* Publish jobs for candidates
* View candidate applications
* Review AI-generated candidate scores
* Shortlist candidates for interviews

---

## Candidate Module

Candidates can search for available opportunities and participate in the recruitment process.

### Candidates can:

* View available jobs
* View job details
* Apply for jobs
* Participate in AI-powered assessments
* Answer technical questions
* Receive an AI-based evaluation
* Be considered for the interview stage based on their assessment

---

# AI Interview & Assessment Module

The AI module is one of the core components of TalentForge.

The system uses the ** Google Gemini API/(In Process) ** to generate and evaluate technical interview questions.

Questions are generated according to:

* Job Role
* Experience Level
* Difficulty

For example:

```text
Job Role       → Java Developer
Experience     → 2 Years
Difficulty     → Medium

             ↓

       AI Question Generation

             ↓

   Technical Interview Questions

             ↓

       Candidate Responses

             ↓

        AI Evaluation

             ↓

       Candidate Score
```

The generated score provides recruiters with an additional data point when reviewing candidates.

### AI Fallback

If the AI service is unavailable, TalentForge provides predefined fallback questions so that the assessment process can continue without completely depending on the external AI service.

---

# Recruitment Workflow

## 1. Recruiter Creates a Job

The recruiter creates a new job opening by providing the required information such as:

* Job title
* Job role
* Job description
* Required experience
* Required skills
* Assessment difficulty

---

## 2. Recruiter Publishes the Job

Once the job is created, the recruiter can publish it.

Published jobs become visible to candidates.

---

## 3. Candidate Views Available Jobs

Candidates can browse the available job postings and view the details of positions they are interested in.

---

## 4. Candidate Applies

The candidate submits an application for the selected job.

The application becomes associated with the corresponding job and candidate.

---

## 5. Candidate Completes AI Assessment

After applying, the candidate participates in an AI-powered technical assessment.

TalentForge generates questions based on the requirements of the job.

---

## 6. AI Evaluates the Candidate

The candidate's responses are evaluated by the AI system.

The system generates a score that provides an initial indication of the candidate's technical performance.

---

## 7. Recruiter Reviews the Candidate

The recruiter can review:

* Candidate information
* Job application
* Assessment result
* AI-generated score

The recruiter can then decide whether the candidate should proceed to the next stage.

---

## 8. Personal Interview

Candidates who are shortlisted can be contacted by the recruiter for a personal interview.

This creates a two-stage screening process:

```text
Application
     ↓
AI Assessment
     ↓
AI Evaluation
     ↓
Recruiter Review
     ↓
Personal Interview
```

---

# System Architecture

```text
                    Client Application
                           │
                           ▼
                    REST API Layer
                           │
                           ▼
                  Spring Boot Backend
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
        ▼                  ▼                  ▼
 Authentication       Recruitment          AI Module
 & Security            Module                │
        │                  │                  ▼
        │                  │            Google Gemini/ Or any other light weight AI 
        │                  │
        └──────────┬───────┘
                   ▼
              PostgreSQL
```

---

# Technology Stack

## Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT
* Lombok

## Database

* PostgreSQL

## AI

* Google Gemini API

## API Development & Testing

* REST APIs
* Postman

## Development Tools

* IntelliJ IDEA
* Git
* GitHub

---

# Backend Architecture

TalentForge follows a layered backend architecture to keep the application organized and maintainable.

```text
Controller
    │
    ▼
  Service
    │
    ▼
Repository
    │
    ▼
PostgreSQL
```

The major layers include:

### Controller Layer

Handles HTTP requests and exposes REST APIs.

### Service Layer

Contains the application's business logic.

### Repository Layer

Handles communication with the PostgreSQL database using Spring Data JPA.

### Entity Layer

Represents the application's database entities.

### DTO Layer

Used to transfer data between the client and backend without directly exposing database entities.

### Security Layer

Handles:

* Authentication
* JWT generation
* JWT validation
* Authorization
* Password encryption

### AI Service

Handles communication with the Google Gemini API for AI-powered question generation and evaluation.

---

# Main Application Entities

The core recruitment workflow revolves around the following concepts:

```text
User
 │
 ├── Recruiter
 │      │
 │      └── Job
 │            │
 │            └── Applications
 │                         │
 │                         ▼
 │                  AI Assessment
 │                         │
 │                         ▼
 │                  Candidate Score
 │
 └── Candidate
        │
        └── Applications
```

The exact entity relationships will evolve as additional recruitment features are implemented.

---

# Project Scope

The current development of TalentForge focuses on building the **core recruitment workflow first**.

### Current focus

* Authentication
* User management
* Recruiter functionality
* Candidate functionality
* Job creation
* Job publishing
* Job browsing
* Job applications
* AI assessment
* AI evaluation
* Candidate scoring
* Recruiter review
* Interview stage

Advanced administrative functionality will be added later.

---

# Future Enhancements

The platform can be extended with additional features such as:

* Admin dashboard
* Advanced recruiter dashboard
* Candidate profile management
* Resume upload and analysis
* Advanced candidate filtering
* Interview scheduling
* Interview feedback management
* AI-generated interview reports
* Candidate ranking
* Email notifications
* Recruitment analytics
* Application status tracking

---

# Project Goal

The goal of TalentForge is to build a recruitment platform that combines **traditional hiring workflows with AI-assisted candidate screening**.

Instead of relying entirely on manual candidate screening, recruiters can use TalentForge to evaluate candidates through an initial AI-powered assessment and use the resulting score as an additional factor when deciding which candidates should proceed to a personal interview.

TalentForge is designed to make recruitment more **structured, efficient, and scalable**, while keeping the recruiter responsible for the final hiring decision.

---

# Development Status

TalentForge is currently under active development.

The project is being developed incrementally, starting with the authentication and security foundation and then expanding into the core recruitment workflow.

```text
Authentication & Security
          ↓
      Job Module
          ↓
   Application Module
          ↓
    AI Assessment
          ↓
   Candidate Evaluation
          ↓
   Recruiter Review
          ↓
      Interview
          ↓
   Advanced Features
```
