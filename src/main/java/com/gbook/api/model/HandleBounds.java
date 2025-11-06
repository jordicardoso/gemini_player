package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HandleBounds {
    private List<Handle> source;
    private List<Handle> target;

    public HandleBounds() {}

    public List<Handle> getSource() { return source; }
    public void setSource(List<Handle> source) { this.source = source; }
    public List<Handle> getTarget() { return target; }
    public void setTarget(List<Handle> target) { this.target = target; }
}