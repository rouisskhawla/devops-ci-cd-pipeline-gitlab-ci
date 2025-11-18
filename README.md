# DevOps CI/CD Pipeline (GitLab CI)

## 1. Overview
This project uses GitLab CI/CD to automatically build, test, sonar analyzis, package, containerize, and deploy a Spring Boot application.  
The workflow integrates:

- GitLab CI runners (Docker executor and Shell executor)
- SonarQube for code quality analysis
- Docker image build and push
- Automatic deployment to a staging Virtual Machine (VM)
- GitLab CI/CD variables for secure configuration

---

## 2. CI/CD Implementation Summary

### 2.1 Project Initialization
- The project was imported into GitLab.
- '.gitlab-ci.yml' was added to the repository to define all pipeline stages and jobs.

### 2.2 GitLab Runners Setup
Two runners were registered on WSL Ubuntu (Windows host):
- **Docker executor runner** (build, test, analysis, packaging, image creation)
- **Shell executor runner** (deployment to staging server)

### 2.3 Virtual Machines Setup
- **VM1 – SonarQube Server**
  - VirtualBox VM
  - Host-Only network used for GitLab access
  - SonarQube connected to GitLab using tokens

- **VM2 – Staging Server**
  - VirtualBox VM
  - NAT + Host-Only + Bridged network
  - Hosts Docker and Docker Compose for deployments

### 2.4 GitLab CI/CD Variables  
Configured under:  
**Settings → CI/CD → Variables**

Includes:
- 'SONAR_HOST_URL'
- 'SONAR_TOKEN'
- 'SSH_PRIVATE_KEY_STAGING'
- 'STAGING_VM_USER'
- 'STAGING_VM_IP'
- 'STAGING_VM_PORT'

### 2.5 Pipeline Trigger
The pipeline runs automatically on:
- Push events  
- Merge requests  

Manual triggers can be executed from:
- Repository → Build → Pipelines → New Pipeline

---

## 3. Architecture

### 3.1 Components
- **GitLab Repository**  
  Contains source code and CI/CD pipeline configuration.

- **GitLab Runners (WSL Ubuntu)**  
  - Docker executor runner for tests, SonarQube, packaging, and image creation  
  - Shell executor runner for deployment

- **VM1 – SonarQube Server**  
  - Installed on VirtualBox  
  - Connected to GitLab using tokens  
  - Accessible via Host-Only IP

- **VM2 – Staging Server**  
  - Installed on VirtualBox  
  - Runs Docker and Docker Compose  
  - Receives deployments via SSH

---

## 4. Pipeline Structure

### Stages
1. **maven-test**
2. **sonarqube-check**
3. **maven-package**
4. **image-build**
5. **deploy-staging**

Each stage uses specific runner tags to ensure execution on the appropriate executor.

---

## 5. GitLab Runners Setup

### 5.1 Docker Executor Runner (WSL Ubuntu)
Used for:
- Unit testing
- SonarQube analysis
- Maven packaging
- Docker image build

**Tag:** 'wsl-docker'

---

### 5.2 Shell Executor Runner (WSL Ubuntu)
Used for:
- Deployment to the staging VM

**Tag:** 'wsl-shell'

---

## 6. SonarQube Integration (VM1)

### 6.1 VirtualBox Network Settings
SonarQube VM uses:
- NAT for internet access  
- Host-Only adapter for communication with GitLab runner  

GitLab CI connects using the Host-Only IP:

http://192.168.56.x:9000

### 6.2 Integration Steps
- Installed SonarQube on VM1  
- Created GitLab Personal Access Token  
- Generated SonarQube project token  
- Added the following GitLab CI/CD variables:
  - 'SONAR_HOST_URL'
  - 'SONAR_TOKEN'

---

## 7. Staging Deployment Setup (VM2)

### 7.1 VirtualBox Network Settings
Staging VM uses:
- NAT  
- Host-Only  
- Bridged Adapter (used for SSH deployment)

Bridged IP is stored as a CI/CD variable.

### 7.2 GitLab CI/CD Variables
| Variable                   | Description                      |
|----------------------------|----------------------------------|
|  SSH_PRIVATE_KEY_STAGING   | SSH private key for deployment   |
|  STAGING_VM_USER           | SSH user                         |
|  STAGING_VM_IP             | Bridged IP of staging VM         |
|  STAGING_VM_PORT           | SSH port                         |

### 7.3 Deployment Workflow
The CI job connects to VM2 through SSH and performs:
1. Pull latest Docker image  
2. Stop existing containers  
3. Start updated version using Docker Compose  

---

## 8. Screenshots and Documentation
All screenshots and additional documentation are stored under:
/docs

Includes:
- Pipeline execution logs  
- Job logs  
- GitLab CI/CD variables page  
- Runner registration screenshots  
- SonarQube configuration  
- Deployment logs  

---

## 9. Repository Structure

root/
│
├── src/
├── .gitlab-ci.yml
├── Dockerfile
├── docker-compose.yml
├── README.md
└── docs/
