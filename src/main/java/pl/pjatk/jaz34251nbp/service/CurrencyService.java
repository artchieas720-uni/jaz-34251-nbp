package pl.pjatk.jaz34251nbp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import pl.pjatk.jaz34251nbp.api.NbpApiClient;
import pl.pjatk.jaz34251nbp.model.QueryHistory;
import pl.pjatk.jaz34251nbp.repository.QueryHistoryRepo;
import pl.pjatk.jaz34251nbp.model.Rate;
import pl.pjatk.jaz34251nbp.model.Root;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyService {

    private final NbpApiClient nbpApiClient;
    private final QueryHistoryRepo repo;

    public CurrencyService(NbpApiClient nbpApiClient, QueryHistoryRepo repository) {
        this.nbpApiClient = nbpApiClient;
        this.repo = repository;
    }

    public QueryHistory calculateAndLogCurrency(String currency, LocalDate startDate, LocalDate endDate) {
        try {
            Root root = nbpApiClient.callNbpApi(currency, startDate, endDate);
            if (root.getRates().isEmpty() || root == null || root.getRates() == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak danych dla tego okresu");
            }

            List<Rate> rates = root.getRates();
            double suma = 0;
            for (Rate rate : rates) {
                suma += rate.getMid();
            }
            double avgRate = suma / rates.size();

            QueryHistory history = new QueryHistory(
                    currency,
                    startDate,
                    endDate,
                    avgRate,
                    LocalDateTime.now()
            );

            return repo.save(history);

        } catch (RestClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()), "Blad NBP: " + e.getStatusText());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Wystapil blad serwera", e);
        }
    }
}