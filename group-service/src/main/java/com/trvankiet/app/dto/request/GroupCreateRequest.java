package com.trvankiet.app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GroupCreateRequest {

    @NotNull(message = "name is required")
    private String name;
    private String description;
    @NotNull(message = "isClass is required")
    private Boolean isClass;
    @NotNull(message = "isPublic is required")
    private Boolean isPublic;
    @NotNull(message = "isAcceptAllRequest is required")
    private Boolean isAcceptAllRequest;
    private String subject;
    private Integer grade;
    private Integer yearFrom;
    private Integer yearTo;

}
