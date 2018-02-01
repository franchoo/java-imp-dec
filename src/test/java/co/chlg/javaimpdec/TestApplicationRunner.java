package co.chlg.javaimpdec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * Helper configuration class, used in spring shell tests.
 */
@TestConfiguration
public class TestApplicationRunner implements ApplicationRunner {

  private static final Logger LOG = LogManager.getLogger();

  public TestApplicationRunner() {
    LOG.info("Test Application Runner started!");
  }

  @Override
  public void run(ApplicationArguments args) {
    LOG.info("About to do nothing!");
  }

}
