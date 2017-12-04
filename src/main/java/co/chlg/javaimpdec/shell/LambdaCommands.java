package co.chlg.javaimpdec.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class LambdaCommands {
  // TODO: Declare concurrent objects...
  AtomicInteger countVocal = new AtomicInteger(0);
  private static final String CHARS_ALLOW = "aeiouáéíóú";

  @ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
  private long doMultPairs(@ShellOption List<Integer> params) {
    return params.stream()
        .filter(x -> x % 2 == 0)
        .map(Long::valueOf)
        .reduce(1L, (a, b) -> a * b);
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de concurrencia y lambda")
  private int doUpdateVocalCount(@ShellOption List<String> words) {
    Predicate isVocalPredicate =  x -> CHARS_ALLOW.indexOf( (int) x)>=0 ;
    List<Integer> listCharsToInt = new ArrayList();
    words.parallelStream().collect(Collectors.toList()).forEach(item-> listCharsToInt.addAll( item.toLowerCase().chars().boxed().collect(Collectors.toList()).subList(0,item.length()) ) ) ;
    listCharsToInt.stream().filter( isVocalPredicate ).forEach( c-> countVocal.set( countVocal.get() + 1 ));
    return countVocal.intValue();
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de sincronia y lambda")
  private List<Boolean> doAllowEntry(@ShellOption List<String> party) {
    Supplier<Boolean> releaseAndNull = null;
    // TODO: Can implement releaseAndNull supplier to help map easily in the implementation
    return null;
  }

}
