package springmvc;

import java.net.URI;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

	@Test
	public void t1() {
		String url = "http://127.0.0.1:8080/springmvc/param/handle5.do";
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("name", "p");
		form.add("password", "p");
		RestTemplate rest = new RestTemplate();
		URI uri = rest.postForLocation(url, form);
		String string = rest.postForObject(url, null, String.class);
		System.out.println(string);

	}
}
