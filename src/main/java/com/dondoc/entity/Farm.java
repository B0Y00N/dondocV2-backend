package com.dondoc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Farm {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
