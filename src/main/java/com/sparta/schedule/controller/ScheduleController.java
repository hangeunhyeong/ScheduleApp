package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ScheduleResponseDto findSelectedSchedules(@PathVariable Long id) {
        if (scheduleList.containsKey(id)) {
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(scheduleList.get(id));
            return scheduleResponseDto;

        } else {
            throw new IllegalArgumentException("Schedule not found");
        }

    }

    /**************일정 목록 조회***************/
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> readAllSchedules() {
        Stack<ScheduleResponseDto> scheduleResponseDtoStack = new Stack<>();
        System.out.println(scheduleList.keySet());
        for (int index = scheduleList.size() - 1; index >= 0; index--) {
            scheduleResponseDtoStack.push(new ScheduleResponseDto(scheduleList.get(Long.valueOf(index))));
        }
        List<ScheduleResponseDto> scheduleResponseDtoList = new ArrayList<>(scheduleResponseDtoStack);

        return scheduleResponseDtoList;
    }

    /**************선택한 일정 수정***************/
    @PutMapping("/schedules/{id}")
    public ScheduleResponseDto editSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        verify(id,scheduleRequestDto);

        Schedule schedule = scheduleList.get(id);
        schedule.editSchedule(scheduleRequestDto);
        scheduleList.put(id, schedule); // DB에 변경사항 저장
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;


    }

    public void verify(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleList.get(id);
        boolean existId = schedule != null;//ID 존재여부
        if (!existId) {
            throw new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다");
        }


        String actualPassword = schedule.getPassword();
        String requestPassword = scheduleRequestDto.getPassword();

        boolean checkActualUser = requestPassword.equals(actualPassword); //비밀번호 외출여부

        if (!checkActualUser) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }
}
