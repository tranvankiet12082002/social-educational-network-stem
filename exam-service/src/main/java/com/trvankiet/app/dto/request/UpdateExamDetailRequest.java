package com.trvankiet.app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateExamDetailRequest {

    @NotNull
    private String name;
    private String description;
    @NotNull
    private Integer duration; // minutes
    @NotNull
    private String startedAt;
    @NotNull
    private String endedAt;
    @NotNull
    private Boolean isEnabled;
    @NotNull
    private Integer numberOfQuestion;
    @NotNull
    private Boolean isAutoMark;
    private String level;
    @NotNull
    private Integer maxScore;

}
