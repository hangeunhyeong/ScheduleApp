package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    LocalDateTime now = LocalDateTime.now();
    private String currentDateTime = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.title=scheduleRequestDto.getTitle();
        this.contents=scheduleRequestDto.getContents();
        this.manager=scheduleRequestDto.getManager();
        this.password=scheduleRequestDto.getPassword();
        this.currentDateTime=scheduleRequestDto.getCurrentDateTime();
    }
}
