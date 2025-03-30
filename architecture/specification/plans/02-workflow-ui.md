# Phase 1: Workflow UI
## Objective
Write a Shell command to read in a workflow specification file.

## Steps
1. Create the package `service` inside the `application` package of the `workflowengine` package if it not exists.
2. Create the class `WorkflowService` inside the `service` package you have just created if it not exists.
3. Inside the `workflowengine` package is an `application` package. It contains a `port` package with an `in` package.
Create the interface `WorkflowExecutionUseCase` inside the `in` package.
4. Define the following methods inside the `WorkflowExecutionUseCase` interface if they not exists:
- `WorkflowSpec readWorkflow(String workflowPath);` This method reads the workflow file
5. Let the `WorkflowService` you have created inside the `service` package implement the `WorkflowExecutionUseCase`.
6. Implement the methods of the `WorkflowService` by using the `WorkflowSpecReaderPort`. Inject the `WorkflowSpecReaderPort` by a constructor injection into the `WorkflowService`.  
7. Create a Unit-Test for `WorkflowService` by using mocks.
8. Create the package `ui` inside the `adapter` package of the `workflowengine` package if it not exists. 
9. Create the class `WorkflowShellCommands` inside the `ui` package you've created. Let it be a SpringBoot ShellComponent.
10. Inject the `WorkflowExecutionUseCase` into the constructor of the `WorkflowShellCommands`.
11. Write the command `execute-workflow` inside `WorkflowShellCommands` class. It takes in the parameter `path` which
represents the path of the workflow-file this method should execute. 
12. Implement the `execute-workflow` command, i.e. the corresponding method:
- first validate the path. Check if it is a valid path.
- check if the path represents a file.
- check if the file is a yaml-file.
- if any check failes, throw an IllegalArgumentException with a descriptive message.
- if all checks have passed use the `WorkflowExecutionUseCase` to read the workflow file.
13. Create a Unit-Test for `WorkflowShellCommands`. Test all cases. 

