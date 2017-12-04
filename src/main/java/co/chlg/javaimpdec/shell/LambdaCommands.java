package co.chlg.javaimpdec.shell;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class LambdaCommands {

  private final AtomicInteger count = new AtomicInteger();
  private final Semaphore pass = new Semaphore(2);

  @ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
  private long doMultPairs(@ShellOption List<Integer> params) {
    return params.stream()
        .filter(x -> x % 2 == 0)
        .map(Long::valueOf)
        .reduce(1L, (a, b) -> a * b);
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de concurrencia y lambda")
  private int doUpdateVocalCount(@ShellOption List<String> words) {
    Predicate<Character> isVocal = ch -> Arrays
        .asList('a', 'e', 'i', 'o', 'u', 'á', 'é', 'í', 'ó', 'ú').contains(ch);
    // TODO: Can use string.chars().mapToObj with monad method and isVocal to filter in the implementation
    return count.addAndGet(
        (int) words.stream()
            .flatMapToInt(String::chars)
            .mapToObj(x -> (char) x)
            .filter(isVocal)
            .count());
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de sincronia y lambda")
  private List<Boolean> doAllowEntry(@ShellOption List<String> party) {
    Supplier<Boolean> releaseAndNull = () -> {
      pass.release();
      return null;
    };
    // TODO: Can implement releaseAndNull supplier to help map easily in the implementation
    return party.stream()
        .map(x -> x.equals("...") ? releaseAndNull.get() : pass.tryAcquire())
        .collect(toList());
  }

}
