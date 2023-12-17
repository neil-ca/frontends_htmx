package com.modernfrontendshtmx.timesheets.timeregistration;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.time.Duration;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class TimeRegistration {
  private int projectId;
  private LocalDate date;
  private Duration duration;
}
