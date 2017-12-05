package co.chlg.javaimpdec.rest;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

  private static final Logger log = Logger.getLogger(TimeController.class);

  @GetMapping("/duration-rand/{quantity}")
  private String getRandomQuantityDuration(@PathVariable("quantity") int quantity) {
    Instant begin = Instant.now();
    log.info(IntStream.generate(RandomUtils::nextInt).limit(quantity).average());
    return Duration.between(begin, Instant.now()).toString();
  }

  @GetMapping("/add-hours-return-day/{hours}")
  private Integer getLocalDayAfterTime(@PathVariable("hours") int hours) {
    return null;
  }

}
