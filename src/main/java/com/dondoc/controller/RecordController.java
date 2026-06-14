package com.dondoc.controller;

import com.dondoc.dto.Categories;
import com.dondoc.dto.Records;
import com.dondoc.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/record")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService){
        this.recordService = recordService;
    }

    @GetMapping("/categories")
    public List<Categories.Category> getCategories() {
        return recordService.getCategories();
    }

    @GetMapping("/monthly-history")
    public List<Records.MonthlyHistory> getMonthlyHistory() {
        return recordService.getMonthlyHistories();
    }

    @PostMapping
    public void createRecord(@RequestBody Records.Record record){
        recordService.createRecord(record);
    }

    @PostMapping("/categories")
    public void createCategory(@RequestBody Categories.Category category){
        recordService.createCategory(category);
    }

    @PostMapping("/monthly-history")
    public void createMonthlyHistory(@RequestBody Records.MonthlyHistory monthlyHistory){
        recordService.createMonthlyHistory(monthlyHistory);
    }

    @GetMapping
    public ResponseEntity<?> getMonthlyRecords(
            @RequestHeader(value = "userId", required = false) Long userId,
            @RequestParam String yearMonth, @RequestParam(required = false) String type ){
        return ResponseEntity.ok(recordService.getMonthlyRecords(userId, yearMonth, type));
    }

}
