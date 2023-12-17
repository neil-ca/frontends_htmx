package com.modernfrontendshtmx;

import com.modernfrontendshtmx.timesheets.project.ProjectService;
import com.modernfrontendshtmx.timesheets.timeregistration.TimeRegistrationService;

import lombok.AllArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
// import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/time")
public class TimeSheetsController {
    private final ProjectService projectService;
    private final TimeRegistrationService timeRegistrationService;

    public TimeSheetsController(ProjectService projectService,
            TimeRegistrationService timeRegistrationService) {
        this.projectService = projectService;
        this.timeRegistrationService = timeRegistrationService;
    }

    @GetMapping
    public String time(Model model, Locale locale) {
        model.addAttribute("projects", projectService.getProjects());
        List<LocalDate> daysOfCurrentWeek = getDaysOfCurrentWeek(locale);
        model.addAttribute("days", daysOfCurrentWeek);
        return "time";
    }

    private static List<LocalDate> getDaysOfCurrentWeek(Locale locale) {
        LocalDate now = LocalDate.now();
        DayOfWeek firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
        LocalDate firstDay = now.with(firstDayOfWeek);
        return Stream.iterate(firstDay, date -> date.plusDays(1)).limit(7).toList();
    }
}
