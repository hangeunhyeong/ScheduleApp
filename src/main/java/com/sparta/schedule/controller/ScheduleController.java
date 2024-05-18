package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    // DB 를 HashMap 을 이용
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    /**************일정 작성***************/
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        // RequestDto -> Schedule
        Schedule schedule = new Schedule(scheduleRequestDto);

        //고유ID값 생성
        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet()) + 1 : 0;
        schedule.setId(maxId);

        // DB에 등록
        scheduleList.put(schedule.getId(), schedule);

        // Schedule -> ScheduleResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        // 비밀번호가 제외된 일정정보 반환
        return scheduleResponseDto;

    }

    /**************선택한 일정 조회***************/
    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto findSelectedSchedules(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        if (scheduleList.containsKey(id)) {
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(scheduleList.get(id));
            return scheduleResponseDto;

        }else{
            throw new IllegalArgumentException("Schedule not found");
        }

    }
}
