package pl.pjatk.jaz34251nbp.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.jaz34251nbp.model.Root;

import java.time.LocalDate;

@Component
public class NbpApiClient {

    private final RestTemplate restTemplate;

    public NbpApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Root callNbpApi(String currency, LocalDate startDate, LocalDate endDate) {
        return restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/"
                        + currency
                        + "/"
                        + startDate
                        + "/"
                        + endDate,
                Root.class);
    }

}
