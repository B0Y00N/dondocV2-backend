package com.dondoc.controller;

import com.dondoc.dto.ApiResponse;
import com.dondoc.dto.Users;
import com.dondoc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService =userService;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Users.MeResponse>> getUserMe(
            @RequestHeader(value = "userId", required = false) Long userId) {
        Users.MeResponse response = userService.getUserMe(userId);
        return ResponseEntity.ok(ApiResponse.ok(response, "월별 요약 통계 조회 성공"));
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateUserMe(
            @RequestHeader(value = "userId", required = false) Long userId,
            @RequestBody Users.PatchRequest request){
        return ResponseEntity.ok(userService.updateUserMe(userId, request));
    }
}
