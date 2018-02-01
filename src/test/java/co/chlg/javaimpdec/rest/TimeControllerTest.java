package co.chlg.javaimpdec.rest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.springframework.http.HttpStatus.OK;

import co.chlg.javaimpdec.TestApplicationRunner;
import java.net.URI;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LocalDateTime.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@Import(TestApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeControllerTest {

  private static final Logger LOG = LogManager.getLogger();

  private URI url;
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate testRest;

  @Before
  public void setUp() {
    url = URI.create("http://localhost:" + port + "/time/");
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getThousandRandomDuration() {
    // Given...
    URI uri = url.resolve("duration-rand/" + (200 * 1000 * 1000));
    // When...
    ResponseEntity<String> response = testRest.getForEntity(uri, String.class);
    // Then...
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getBody(), instanceOf(String.class));
    LOG.info(response.getBody());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getDayAfter10Hours() throws Exception {
    // Given...
    //mockStatic(LocalDateTime.class);
    //given(LocalDateTime.now()).willReturn(LocalDateTime.of(2017, 12, 6, 19, 0));
    URI uri = url.resolve("add-hours-return-day/" + 10);
    // When...
    //whenNew(LocalDateTime.class).withAnyArguments().thenReturn(LocalDateTime.of(2017, 12, 6, 19, 0));
    ResponseEntity<Integer> response = testRest.getForEntity(uri, Integer.class);
    // Then...
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getBody(), is(7));
    LOG.info(response.getBody());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getFirstWedOfDic2017() {
    // Given...
    URI uri = url
        .resolve("get-first/" + DayOfWeek.WEDNESDAY.name() + "/from/" + 2017 + "/month/" + 12);
    // When...
    ResponseEntity<Integer> response = testRest.getForEntity(uri, Integer.class);
    // Then...
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getBody(), is(6));
    LOG.info(response.getBody());
  }

}

