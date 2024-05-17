package com.sparta.schedule.dto;

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
    // 현재 날짜/시간 출력
    LocalDateTime now = LocalDateTime.now();
    // 포맷팅
    private String currentDateTime = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));

}
