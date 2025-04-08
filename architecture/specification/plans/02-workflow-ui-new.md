# Phase 2: Workflow UI
## Objective
Write a Shell command to read in a workflow specification file.

## Basic conditions
- Do not change Files inside the `com.fop.workflow.workflowengine.model.schema` package
- Als Architektur des Systems wird eine hexagonale Architektur gewählt.
- Der Sprachgebrauch orientiert sich am arc42 Architektur-Template
- Das System besteht aus 4 Bausteinen:
-- `com.fop.workflow.agents`
-- `com.fop.workflow.llmgateway`
-- `com.fop.workflow.mcpclients`
-- `com.fop.workflow.workflowengine`
- Die hexagonale Architektur ist in den Bausteinen sichtbar. Beispiel workflowengine:
-- `com.fop.workflow.workflowengine.adapter`: Enthält adapter, z.B. für input/output und ui.
-- `com.fop.workflow.workflowengine.application`: Enthält die Geschäftslogik in der keine externen Abhängigkeiten existieren
-- `com.fop.workflow.workflowengine.model`: Enthält Entitäten in denen ORM (Objekt-Relationales-Mapping) erlaubt ist.
-- `com.fop.workflow.workflowengine.port`: Enthält die input und output-Ports (packages in und out).

## Plan
1. Der `workflowengine`-Baustein benötigt einen `WorkflowService`. Dieser sollte im `application` package sein. Erzeuge ihn nur, wenn er noch nicht existiert.
2. Im input-Port des `workflowengine`-Baustein soll das Interface `WorkflowExecutionUseCase` erzeugt werden, wenn es nicht existiert.
3. Das Interface soll eine Methode enthalten die einen String-Pfad als Parameter hat und 
ein Objekt vom Typ `com.fop.workflow.workflowengine.model.schema.WorkflowSpec` zurückgibt.
Die Methode liest also eine Workflow-Spezifikation.
4. Der `WorkflowService` der gerade erzeugt wurde, falls er noch nicht existierte,
soll nun das interface `WorkflowExecutionUseCase` implementieren.
Dafür soll er den `com.fop.workflow.workflowengine.application.port.out.WorkflowSpecReaderPort`
verwenden. Diesen kann der `WorkflowService` per Dependency Injection im Konstruktor injiziert bekommen.
5. Erzeuge einen Unit-Test für den `WorkflowService`. Es sollen Mocks verwendet werden.
Der Unit-Test soll auch Fehlersituationen Testen.