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
1. Wir benötigen im input-port des agent-Baustein 