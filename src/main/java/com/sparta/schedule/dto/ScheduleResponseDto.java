package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private LocalDate currentDateTime;

    public ScheduleResponseDto(Schedule schedule) {
        this.id=schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        this.currentDateTime = schedule.getCurrentDateTime();
    }
}
