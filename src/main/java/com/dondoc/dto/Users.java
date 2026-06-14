package com.dondoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Long id;
    private String userId;
    private String userPassword;
    private String name;
    private Integer age;
    private Integer currentPigLevel;
    private Integer currentHouseLevel;
    private Integer currentCharacterLevel;
    private Long monthlyIncome;
    private Integer targetExpenseRatio;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

    @Getter
    @AllArgsConstructor
    public static class MeResponse {
        private String name;
        private Integer age;
        private Integer currentPigLevel;
        private Integer currentHouseLevel;
        private Integer currentCharacterLevel;
        private Long monthlyIncome;
        private Integer targetExpenseRatio;
        private Long monthlyBudget;
        private Long dailyBudget;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PatchRequest {
        private String name;
        private Integer age;
        private Long monthlyIncome;
        private Integer targetExpenseRatio;
    }

    @Getter
    @AllArgsConstructor
    public static class PatchResponse {
        private Long id;
        private String name;
        private Integer age;
        private Long monthlyIncome;
        private Integer targetExpenseRatio;
        private Long monthlyBudget;
        private Long dailyBudget;
    }
}
