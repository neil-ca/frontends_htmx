package com.modernfrontendshtmx.timesheets.timeregistration;

import java.time.Duration;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class TimeRegistration {
    private int projectId;
    private LocalDate date;
    private Duration duration;
}
