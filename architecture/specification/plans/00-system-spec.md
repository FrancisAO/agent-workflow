# Project details
## Build configuration
Read the build.gradle file to get information about the project setup.

### Operating System

- Operating System: Windows 11
- Development Environment: Visual Studio Code
- Shell: Powershell
- Build Tool: Gradle
- Language: Java
- Frameworks: Spring Boot, Spring AI, Spring Shell

## General information
1. Read the build.gradle file to have a clear understandig about the project setup. 
2. Be aware that `com.fop` is the group id. The `workflow` package is inside this package. 
3. There are the following main building blocks of the system:
- agents
- llmgateway
- mcpclients
- workflowengine
All those main building blocks are under the package `workflowengine`
4. The architecture used in this project is a hexagonal architecture. But be aware:
- We do not use domain driven design in this architecture
- We do not use this architecture to build a microservice
5. Use the Montenegro-Style for all JUnit-Tests.
