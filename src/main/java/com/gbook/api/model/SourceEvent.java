package com.gbook.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SourceEvent {
    private boolean isTrusted;

    public SourceEvent() {}

    public boolean isTrusted() { return isTrusted; }
    public void setTrusted(boolean trusted) { isTrusted = trusted; }
}