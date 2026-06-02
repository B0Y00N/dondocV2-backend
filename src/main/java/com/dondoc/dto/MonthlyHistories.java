package com.dondoc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyHistories {
    private Long id;
    private Long userId;
    private LocalDate targetMonth;
    private Float avgRatio;
    private Integer houseLevel;
}
