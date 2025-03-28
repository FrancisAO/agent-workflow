# Phase 1: Workflow Specification
## Objective
Write a JSON schema file containing the schema of a workflow specification.
Then write the code for reading a workflow specification file.

## Steps
1. Create a new file named `workflow-spec.json` in the `architecture/specification` directory.
2. Write a JSON schema for the workflow specification in the `workflow-spec.json` file.
3. The schema should contain the following properties:
   - `version`: A string representing the version of the workflow, e.g "1.0" (optional).
   - `name`: A string representing the name of the workflow (optional).
   - `description`: A string representing the description of the workflow (optional).
   - `agents`: An array of agent objects representing the agents in the workflow (neccessary).
   - `workflow`: An array of containing the steps of the workflow (neccessary).
4. Each agent object should contain the following properties:
   - `name`: A string representing the name of the agent (neccessary).
   - `type`: A string the type of the agent (neccessary).
   - `description`: A string representing the description of the agent (optional).
   - `system_message`: A string representing the system message of the agent (optional).
   - `prompt`: A string representing the prompt of the agent (optional).
5. Each step object in the workflow array should contain the following properties:
   - `from`: A string representing the name of the agent the step is coming from (neccessary).
   - `to`: A string representing the name of the agent the step is going to (neccessary).
   - `loop`: An array of loop properties representing the loop of the step (optional).   
6. Each loop object should contain the following properties:
   - `condition`: A string representing the condition of the loop (neccessary).
   - `max_iterations`: An integer representing the maximum number of iterations of the loop (neccessary).
7. Write a YAML file named `workflow-example.yaml` in the `architecture/specification` directory. 
   The YAML file should contain an example of a workflow specification that conforms to the schema defined in the `workflow-spec.json` file.
   The YAML file should contain all the properties defined in the schema.
8. Create the package `port` inside the `com.fop.workflow.workflowengine` package if it is not allready created.
9. Create the packages `in` and `out` inside the package `com.fop.workflow.workflowengine.port` if they are not allready created.
10. Execute the gradle task `generateJsonSchema2Pojo`. This should create the schema classes inside the `build/generated-sources` directory.
11. Create the package `model` with the sub-package `schema` inside the `com.fop.workflow.workflowengine` if they are not allready created.
12. Move the generated schema classes into the `schema` package.
13. Create the interface `WorkflowSpecReaderPort` inside the `com.fop.workflow.workflowengine.port.out` package
14. Create the method `readWorkflowSpec(String path)` inside the `WorkflowSpecReaderPort`interface.
This method returns the root class of a deserialized workflow file.
15. Create the class `WorkflowSpecReaderAdapter` inside the package `com.fop.workflow.workflowengine.infrastructure.adapter`. Let this class
implement the interface `WorkflowSpecReaderPort`.
16. Implement the method. The method should read the workflow specification file, convert it to load the json schema, validate the json schema and if the schema is valid should return the workflow class. Use Jackson.
17. Write a test and assertions for the `workflow-example.yaml` file.