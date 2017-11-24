package co.chlg.javaimpdec.shell;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.apache.commons.lang3.StringUtils.substringAfter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CycleCommands {

  @ShellMethod(group = "cycle", value = "Ejercicio de ciclos implicitos")
  private Set<String> doFlatMap(@ShellOption String... params) {
    return Arrays.stream(params)
        .map(item -> item.split("\\."))
        .flatMap(Arrays::stream) // Is the same as `x -> Arrays.stream(x)`
        //.toArray(String[]::new); // Is the same as `size -> new String[size]`
        .collect(toSet());
  }

  @ShellMethod(group = "cycle", value = "Ejercicio de comparación")
  private String[] doNameScores(@ShellOption String nameA, @ShellOption String nameB,
      @ShellOption List<Long> scoresA, @ShellOption List<Long> scoresB) {
    /*
    List<String> res = new LinkedList<>();
    Iterator<Long> iterB = scoresB.iterator();
    scoresA.forEach(a -> res.add(a > iterB.next() ? nameA : nameB));
    return res.toArray(new String[res.size()]);
    */
    return IntStream.range(0, scoresA.size())
        .mapToObj(i -> scoresA.get(i) > scoresB.get(i) ? nameA : nameB)
        .toArray(String[]::new);
  }

  @ShellMethod(group = "cycle", value = "Ejercicio de ordenamiento")
  private List<String> doSortLastnames(@ShellOption List<String> people) {
    return people.stream()
        .sorted(comparing(x -> substringAfter(x, "-")))
        .collect(toList());
  }

}
