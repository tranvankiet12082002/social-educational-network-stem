package com.trvankiet.app.controller.user;

import com.trvankiet.app.dto.GroupDto;
import com.trvankiet.app.dto.SimpleGroupDto;
import com.trvankiet.app.dto.request.CompetitionCreateRequest;
import com.trvankiet.app.dto.request.GroupConfigRequest;
import com.trvankiet.app.dto.request.GroupCreateRequest;
import com.trvankiet.app.dto.request.UpdateDetailRequest;
import com.trvankiet.app.dto.response.GenericResponse;
import com.trvankiet.app.jwt.service.JwtService;
import com.trvankiet.app.service.GroupService;
import com.trvankiet.app.service.client.UserClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/groups")
@Slf4j
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final JwtService jwtService;

    @PostMapping("/create-competition")
    public ResponseEntity<GenericResponse> createCompetition(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @RequestBody @Valid CompetitionCreateRequest competitionCreateRequest) {
        log.info("GroupController, createCompetition");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.createCompetition(userId, competitionCreateRequest);
    }

    @PostMapping
    public ResponseEntity<GenericResponse> createGroup(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @RequestBody @Valid GroupCreateRequest groupCreateRequest) {
        log.info("AdminGroupController, createGroup");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.createGroup(userId, groupCreateRequest);
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getAllGroup(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        log.info("AdminGroupController, getAllGroup");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.getGroupsByUserId(userId);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GenericResponse> getGroupById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @PathVariable("groupId") String groupId) {
        log.info("AdminGroupController, getGroupById");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.getGroupById(userId, groupId);
    }

    @GetMapping("/validate-user-in-group")
    public ResponseEntity<GenericResponse> validateUserInGroup(@RequestParam("userId") String userId
            , @RequestParam("groupId") String groupId) {
        log.info("AdminGroupController, validateUserInGroup");
        return groupService.valiadateUserInGroup(userId, groupId);
    }

    @GetMapping("/get-group-by-user")
    public ResponseEntity<List<String>> getGroupByUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        log.info("AdminGroupController, getGroupByUserId");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.getGroupByUserId(userId);
    }

    @PutMapping("/{groupId}/config")
    public ResponseEntity<GenericResponse> updateGroupConfig(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @PathVariable("groupId") String groupId
            , @RequestBody GroupConfigRequest groupConfigRequest) {
        log.info("AdminGroupController, updateGroupConfig");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.updateGroupConfig(userId, groupId, groupConfigRequest);
    }

    @PutMapping("/{groupId}/updateDetail")
    public ResponseEntity<GenericResponse> updateGroupDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @PathVariable("groupId") String groupId
            , @RequestBody UpdateDetailRequest updateDetailRequest) {
        log.info("AdminGroupController, updateGroupDetail");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.updateGroupDetail(userId, groupId, updateDetailRequest);
    }

    @PutMapping(value = "/{groupId}/updateAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponse> updateGroupAvatar(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @PathVariable("groupId") String groupId
            , @RequestPart("mediaFile") MultipartFile avatar) throws IOException {
        log.info("AdminGroupController, updateGroupAvatar");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.updateGroupAvatar(userId, groupId, avatar);
    }

    @PutMapping(value = "/{groupId}/updateCover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponse> updateGroupCover(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @PathVariable("groupId") String groupId
            , @RequestPart("mediaFile") MultipartFile cover) throws IOException {
        log.info("AdminGroupController, updateGroupCover");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.updateGroupCover(userId, groupId, cover);
    }

    @DeleteMapping(value = "/{groupId}")
    public ResponseEntity<GenericResponse> deleteGroup(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @PathVariable("groupId") String groupId) {
        log.info("AdminGroupController, deleteGroup");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.deleteGroup(userId, groupId);
    }

    @GetMapping(value = "/suggested-groups")
    public ResponseEntity<GenericResponse> suggestGroups(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.info("AdminGroupController, suggestGroup");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.suggestGroups(userId, page, size);
    }

    @GetMapping(value = "/suggested-classes")
    public ResponseEntity<GenericResponse> suggestClasses(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.info("AdminGroupController, suggestClass");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.suggestClasses(userId, page, size);
    }


    @GetMapping("/search")
    public ResponseEntity<List<GroupDto>> searchGroup(@RequestParam("query") Optional<String> query
            , @RequestParam("isClass") Optional<Boolean> isClass
            , @RequestParam("isPublic") Optional<Boolean> isPublic
            , @RequestParam("grade") Optional<Integer> grade
            , @RequestParam("subject") Optional<String> subject) {
        log.info("AdminGroupController, searchGroup");
        return groupService.searchGroup(query, isClass, isPublic, grade, subject);
    }

    @GetMapping("/simpleGroupDto/{groupId}")
    public SimpleGroupDto getSimpleGroupDto(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
            , @PathVariable("groupId") String groupId) {
        log.info("AdminGroupController, getSimpleGroupDto");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.getSimpleGroupDto(userId, groupId);
    }

    @GetMapping("/myClasses")
    public ResponseEntity<GenericResponse> getMyClasses(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        log.info("AdminGroupController, getMyClasses");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.getMyClasses(userId);
    }

    @GetMapping("/myGroups")
    public ResponseEntity<GenericResponse> getMyGroups(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        log.info("AdminGroupController, getMyGroups");
        String accessToken = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(accessToken);
        return groupService.getMyGroups(userId);
    }

    @GetMapping("/competitions")
    public ResponseEntity<GenericResponse> getCompetitions() {
        log.info("AdminGroupController, getCompetitions");
        return groupService.getCompetitions();
    }


}
