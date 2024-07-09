package com.trvankiet.app.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SubmissionDto implements Serializable {

    private String id;
    @JsonProperty("user")
    private SimpleUserDto userDto;
    private String startedAt;
    private String endedAt;
    private Integer score;
    @JsonBackReference
    @JsonProperty("exam")
    private ExamDto examDto;
    private String createdAt;
    private String updatedAt;

}
