package co.chlg.javaimpdec.rest;

import static java.util.Arrays.stream;
import static java.util.Collections.singletonMap;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.generate;
import static java.util.stream.Stream.iterate;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
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
  private Map<Integer, BigInteger> getMappingNumFib(@PathVariable("num") int num) {
    // validations for inputs < 0 and Large outputs are added
    return singletonMap(num,(num <= 0)? BigInteger.valueOf(-1): Stream.iterate(Pair.of(BigInteger.valueOf(1), BigInteger.valueOf(1)),
            n ->  Pair.of(n.getRight(), n.getLeft().add( n.getRight()))).limit(num)
            .skip(num-1).findFirst().get().getValue());
  }

  @GetMapping("/map-age/{fullName}")
  private Map<String, Integer> getMappingNameAge(@PathVariable("fullName") String fullName) {
    return singletonMap(fullName,Stream.of(fullName.split(" ")).sequential().map(x->String.valueOf(x.length()))
            .reduce(String::concat).map(Integer::parseInt).get());
  }

}
