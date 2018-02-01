package co.chlg.javaimpdec.shell;

import static co.chlg.javaimpdec.DeclarativeUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import co.chlg.javaimpdec.TestApplicationRunner;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(TestApplicationRunner.class)
@SpringBootTest
public class LambdaCommandsTest {

  @SuppressWarnings("unused")
  private static final Logger LOG = LogManager.getLogger();

  @Autowired
  private Shell shell;

  @Test
  public void filterPairsAndMultiply() {
    // Given...
    // String [] input = { 1, 2, 3, 4, 5, ..., 30};
    Stream<Integer> input = IntStream.rangeClosed(1, 30).boxed();
    // When...
    Object result = shell.evaluate(inputFrom("do-mult-pairs", input));
    // Then...
    assertThat(result, is(42849873690624000L));
  }

  @Test
  public void incrementVocalCounter() {
    // Given...
    final String METHOD = "do-update-vocal-count";
    // When...
    Object result1 = shell.evaluate(inputFrom(METHOD, Stream.of("Lorena", "María")));
    Object result2 = shell.evaluate(inputFrom(METHOD, Stream.of("Miguel", "Ramón", "Floréncia")));
    Object result3 = shell.evaluate(inputFrom(METHOD, Stream.of("Fábio", "Consúelo")));
    // Then...
    assertThat(result1, is(6));  // starts with 6 vocals
    assertThat(result2, is(15)); // adds 9 plus 6 vocals
    assertThat(result3, is(22)); // adds 7 plus 9 & 6 vocals
  }

  @Test
  public void onlyTwoEnter() {
    // Given...
    Stream<String> party1 = Stream.of("me", "you", "he", "...", "it", "she", "this", "...", "that");
    Stream<String> party2 = Stream.of("nope", "...", "...", "yes", "go", "...", "also", "wrong");
    // When...
    Object result1 = shell.evaluate(inputFrom("do-allow-entry", party1));
    Object result2 = shell.evaluate(inputFrom("do-allow-entry", party2));
    // Then...
    assertThat(result1, instanceOf(List.class));
    assertThat(result2, instanceOf(List.class));
    assertEquals(Arrays.asList(true, true, false, null, true, false, false, null, true), result1);
    assertEquals(Arrays.asList(false, null, null, true, true, null, true, false), result2);
  }

}
