package com.sparta.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {

    private String title;
    private String contents;
    private String manager;
    private String password;
    private LocalDate currentDateTime=  LocalDate.now();



}
