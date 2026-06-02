package com.dondoc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmMember {
    private Long id;
    private Long userId;
    private Long farmId;
    private LocalDateTime joinedAt;
}
