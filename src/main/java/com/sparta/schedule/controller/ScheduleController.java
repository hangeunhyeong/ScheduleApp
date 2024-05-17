package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    // DB 를 HashMap 을 이용
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        // RequestDto -> Schedule
        Schedule schedule = new Schedule(scheduleRequestDto);

        //고유ID값 생성
        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet())+1 : 0;
        schedule.setId(maxId);

        // DB에 등록
        scheduleList.put(schedule.getId(), schedule);

        // Schedule -> ScheduleResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        // 비밀번호가 제외된 일정정보 반환
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK); //Object to JSON


    }
}
