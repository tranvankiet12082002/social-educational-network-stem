package com.trvankiet.app.dto.request;

import lombok.Data;

@Data
public class AddGroupMemberRequest {

    private String groupId;
    private String userId;
    private String roleCode;

}
