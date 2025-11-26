// Fichero: src/main/java/com/gbook/api/model/JsonGamebook.java
package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonGamebook {
    private Meta meta;
    private List<Node> nodes;
    // private List<Edge> edges; // Removed in new version
    private List<Asset> assets;
    private List<Object> variables;
    private Viewport viewport;
    private CharacterSheetSchema characterSheetSchema;
    private CharacterSheet characterSheet;

    public JsonGamebook() {}

    // Getters y Setters existentes...
    public Meta getMeta() { return meta; }
    public void setMeta(Meta meta) { this.meta = meta; }

    public List<Node> getNodes() { return nodes; }
    public void setNodes(List<Node> nodes) { this.nodes = nodes; }

    // Edges are no longer used in the new format, but we keep the field to avoid JSON parsing errors if present
    // or we can just ignore it via @JsonIgnoreProperties
    // public List<Edge> getEdges() { return edges; }
    // public void setEdges(List<Edge> edges) { this.edges = edges; }

    public List<Asset> getAssets() { return assets; }
    public void setAssets(List<Asset> assets) { this.assets = assets; }

    public List<Object> getVariables() { return variables; }
    public void setVariables(List<Object> variables) { this.variables = variables; }

    public Viewport getViewport() { return viewport; }
    public void setViewport(Viewport viewport) { this.viewport = viewport; }

    public CharacterSheetSchema getCharacterSheetSchema() { return characterSheetSchema; }
    public void setCharacterSheetSchema(CharacterSheetSchema characterSheetSchema) { this.characterSheetSchema = characterSheetSchema; }

    public CharacterSheet getCharacterSheet() { return characterSheet; }
    public void setCharacterSheet(CharacterSheet characterSheet) { this.characterSheet = characterSheet; }

    /**
     * Busca y devuelve el ID del nodo inicial (el nodo con type="start").
     * @return El ID del nodo inicial, o null si no se encuentra.
     */
    public String getStartNodeId() {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }
        Optional<Node> startNode = nodes.stream()
                .filter(node -> "start".equalsIgnoreCase(node.getType()))
                .findFirst();
        return startNode.map(Node::getId).orElse(null);
    }
}