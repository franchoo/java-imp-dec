package co.chlg.javaimpdec.shell;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class LambdaCommands {

  Semaphore semaphore = new Semaphore(2);
  // TODO: Declare concurrent objects...

  @ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
  private long doMultPairs(@ShellOption List<Integer> params) {
    return params.stream()
        .filter(x -> x % 2 == 0)
        .map(Long::valueOf)
        .reduce(1L, (a, b) -> a * b);
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de concurrencia y lambda")
  private int doUpdateVocalCount(@ShellOption List<String> words) {
    Predicate<Character> isVocal = null;
    // TODO: Can use string.chars().mapToObj with monad method and isVocal to filter in the implementation
    return 0;
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de sincronia y lambda")
  private List<Boolean> doAllowEntry(@ShellOption List<String> party) {
    Supplier<Boolean> releaseAndNull = () -> {
      semaphore.release();
      return null;
    };

    return party.stream()
      .map(x->"...".equals(x)
        ? releaseAndNull.get()
        : semaphore.tryAcquire())
    .collect(Collectors.toList());

  }

}
