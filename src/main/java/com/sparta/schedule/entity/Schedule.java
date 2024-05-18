package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    private LocalDate currentDateTime;

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.title=scheduleRequestDto.getTitle();
        this.contents=scheduleRequestDto.getContents();
        this.manager=scheduleRequestDto.getManager();
        this.password=scheduleRequestDto.getPassword();
        this.currentDateTime=scheduleRequestDto.getCurrentDateTime();
    }
}
