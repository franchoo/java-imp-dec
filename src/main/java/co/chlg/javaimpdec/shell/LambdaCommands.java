package co.chlg.javaimpdec.shell;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class LambdaCommands {
	// TODO: Declare concurrent objects...
	private AtomicInteger total = new AtomicInteger();

	@ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
	private long doMultPairs(@ShellOption List<Integer> params) {
		return params.stream().filter(x -> x % 2 == 0).map(Long::valueOf).reduce(1L, (a, b) -> a * b);
	}

	@ShellMethod(group = "lambda", value = "Ejercicio de concurrencia y lambda")
	private int doUpdateVocalCount(@ShellOption List<String> words) {
		// Predicate<String> isVocal = null;
		// TODO: Can use string.chars().mapToObj with monad method and isVocal to filter in the implementation
		int result = words.stream()
						   .mapToInt(word -> word.replaceAll("(?i)[^aeiouáéíóú]", "").length())
						   .sum();

		return total.addAndGet(result);
	}

	@ShellMethod(group = "lambda", value = "Ejercicio de sincronia y lambda")
	private List<Boolean> doAllowEntry(@ShellOption List<String> party) {
		Supplier<Boolean> releaseAndNull = null;
		// TODO: Can implement releaseAndNull supplier to help map easily in the
		// implementation
		return null;
	}

}
