package com.modernfrontendshtmx;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
  @GetMapping
  public String index(Model model) {
    return "index";
  }

  @GetMapping("/htmx")
  public String
  htmx(@RequestParam(value = "delay", required = false) Integer delayInSeconds,
       Model model, HttpServletRequest request) throws InterruptedException {
    String elementId = request.getHeader("Hx-Trigger");
    System.out.println("elementId = " + elementId);
    if (delayInSeconds != null) {
      Thread.sleep(delayInSeconds * 1000L);
    }

    model.addAttribute("currentTime", LocalTime.now());
    return "htmx :: message";
  }
}
