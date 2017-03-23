package com.sample.dropwizard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ankush.a on 22/03/17.
 */
public class SampleDto {

    private long id;

    private String content;

    public SampleDto() {
    }

    public SampleDto(long id, String content) {

        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
