package co.chlg.javaimpdec.rest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import co.chlg.javaimpdec.TestApplicationRunner;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(TestApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonadControllerTest {

  private static final Logger log = Logger.getLogger(MonadControllerTest.class);

  private URI url;
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate testRest;

  @Before
  public void setUp() {
    url = URI.create("http://localhost:" + port + "/monad/");
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getCountsFromMillionsRandom() {
    // Given...
    final String LUCKY_NUM = "13";
    URI uri = url.resolve("map-count/" + LUCKY_NUM + "/size/" + 2 + "/from/" + (10000));
    // When...
    @SuppressWarnings("rawtypes")
	ResponseEntity<Map> response = testRest.getForEntity(uri, Map.class);
    // Then...
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getHeaders().getContentType().toString(),
        startsWith(APPLICATION_JSON_VALUE));
    assertThat(response.getBody(), notNullValue());
    assertThat((Set<String>) response.getBody().keySet(), hasItem(LUCKY_NUM));
    assertTrue((int) response.getBody().get(LUCKY_NUM) > 75);
    log.info(response.getBody());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getFibonacci20() {
    // Given...
    final String NUM = "21";
    URI uri = url.resolve("map-fibonacci/" + NUM);
    // When...
    Fibonacci fibonacci = new Fibonacci();
    
    @SuppressWarnings("rawtypes")
	ResponseEntity<Map> response = testRest.getForEntity(uri, Map.class);
    // Then...
    
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getHeaders().getContentType().toString(),
        startsWith(APPLICATION_JSON_VALUE));
    assertThat(response.getBody(), notNullValue());
    assertThat((Set<String>) response.getBody().keySet(), hasItem(NUM));
    assertThat(response.getBody().get(NUM), is(10946)); // Fibonacci number in 20th position!
  }

 /* @Test
  @SuppressWarnings("unchecked")
  public void getAgeFromNamesLength() {
    // Given...
    final String NAME = "Maria Gutierrez";
    URI uri = url.resolve("map-age/".concat(NAME.replace(" ", "%20")));
    // When...
    ResponseEntity<Map> response = testRest.getForEntity(uri, Map.class);
    // Then...
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getHeaders().getContentType().toString(),
        startsWith(APPLICATION_JSON_VALUE));
    assertThat(response.getBody(), notNullValue());
    assertThat((Set<String>) response.getBody().keySet(), hasItem(NAME));
    assertThat(response.getBody().get(NAME), is(59)); // letter count of each name!
  }*/

}
