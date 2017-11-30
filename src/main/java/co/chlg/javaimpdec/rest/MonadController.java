package co.chlg.javaimpdec.rest;

import static java.util.Collections.singletonMap;
import static java.util.stream.Stream.generate;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monad")
public class MonadController {

  @GetMapping("/map-count/{luckyNum}/size/{exp}/from/{qty}")
  private Map<Integer, Integer> getMappingNumRandomsCount(@PathVariable("luckyNum") int luckyNum,
      @PathVariable("exp") int exp, @PathVariable("qty") int qty) {
    return singletonMap(luckyNum,
        (int) generate(() -> nextInt(0, (int) Math.pow(10, exp))).limit(qty)
            .filter(x -> x == luckyNum)
            .count());
  }

  @GetMapping("/map-fibonacci/{num}")
  private Map<Integer, Integer> getMappingNumFib(@PathVariable("num") int num) {
    // TODO: Pair, skip, findFirst will help you get the job done
	  Fibonacci fibonacci = new Fibonacci(); 
	 return singletonMap(num, fibonacci.fibonacci(num).intValue());
  }

  @GetMapping("/map-age/{fullName}")
  private Map<String, Integer> getMappingNameAge(@PathVariable("fullName") String fullName) {
    // TODO: sequential & reduce will be useful
    return null;
  }

}
