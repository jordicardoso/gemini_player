package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {
    private String id;
    private String type;
    private String flag;
    private Object value; // Can be boolean or other types
    private String stat;
    private String operation;

    public Action() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFlag() { return flag; }
    public void setFlag(String flag) { this.flag = flag; }

    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }

    public String getStat() { return stat; }
    public void setStat(String stat) { this.stat = stat; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
}
