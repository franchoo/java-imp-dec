package co.chlg.javaimpdec.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.singletonMap;
import static java.util.stream.Stream.generate;
import static org.apache.commons.lang3.RandomUtils.nextInt;

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

//  @GetMapping("/map-fibonacci/{num}")
//  private Map<Integer, Integer> getMappingNumFib(@PathVariable("num") int num) {
//    return IntStream.range(0, ++num)
//            .boxed()
//            .collect(Collectors.toMap (i -> i, x ->  Fibonacci(x) ));
//  }

//    private int Fibonacci(int n) {
//        if (n == 0 || n == 1)
//            return n;
//        else
//            return Fibonacci(n - 1) + Fibonacci(n - 2);
//    }

    @GetMapping("/map-fibonacci/{num}")
    private Map<Integer, Integer> getMappingNumFib(@PathVariable("num") int num) {
        UnaryOperator<Factorial> funcion = factorialUnaryOperator -> new Factorial(factorialUnaryOperator.siguiente, factorialUnaryOperator.actual + factorialUnaryOperator.siguiente);
        return IntStream.rangeClosed(0, num)
                .boxed()
                .collect(Collectors.toMap(clave -> clave, valor -> {
                    return Stream.iterate(new Factorial(0, 1), funcion)
                            .map(factorial -> factorial.actual)
                            .limit(++valor)
                            .reduce(0,(primero,segundo) -> segundo);
                }));
    }

    public class Factorial {
        public final Integer actual;
        public final Integer siguiente;

        private Factorial(Integer actual, Integer siguiente) {
            this.actual = actual;
            this.siguiente = siguiente;
        }
    }



    @GetMapping("/map-age/{fullName}")
    private Map<String, Integer> getMappingNameAge(@PathVariable("fullName") String fullName) {
        // TODO: sequential & reduce will be useful
        return null;
    }

}
