package com.trvankiet.app.dto.request;

import lombok.Data;

@Data
public class AdminUpdateGroupRequest {

    private String groupId;
    private String name;
    private String description;
    private Boolean isPublic;
    private Boolean isAcceptAllRequest;

}
