package com.dondoc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyHistory {
    private Long id;
    private Long userId;
    private LocalDate targetMonth;
    private Float avgRatio;
    private Integer houseLevel;
}
