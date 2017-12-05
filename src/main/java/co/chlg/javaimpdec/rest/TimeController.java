package co.chlg.javaimpdec.rest;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

  @GetMapping("/duration-rand/{quantity}")
  private String getRandomQuantityDuration(@PathVariable("quantity") int quantity) {
    Instant begin = Instant.now();
    IntStream.generate(RandomUtils::nextInt).limit(quantity).count();
    return Duration.between(begin, Instant.now()).toString();
  }

}
