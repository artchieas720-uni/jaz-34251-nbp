package pl.pjatk.jaz34251nbp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.jaz34251nbp.model.QueryHistory;
import pl.pjatk.jaz34251nbp.service.CurrencyService;
import java.time.LocalDate;

@RestController
@RequestMapping("/currency")
@Tag(name = "NBP Currency Controller", description = "Endpoint  sluzace do pobierania kursów walut z api NBP")
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Operation(
            summary = "Pobiera sredni kurs i zapisuje",
            description = "Pobiera dane z api NBP dla przedzialu, wylicza srednia i potem zapisuje"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wyliczono srednia i ja zapisano"),
            @ApiResponse(responseCode = "400", description = "Nieprawidlowe dane"),
            @ApiResponse(responseCode = "404", description = "Brak danych"),
            @ApiResponse(responseCode = "500", description = "Wewnetrzny blad serwera")
    })
    @GetMapping("/{currency}/{startDate}/{endDate}")
    public ResponseEntity<QueryHistory> getCurrencyForDates(
            @Parameter(description = "Podaj 3 literowy kod", example = "USD")
            @PathVariable String currency,

            @Parameter(description = "Data poczatku przedzialu w formacie: YYYY-MM-DD", example = "2026-06-01")
            @PathVariable LocalDate startDate,

            @Parameter(description = "Data koncowa przedzialu w formacie: YYYY-MM-DD", example = "2026-06-10")
            @PathVariable LocalDate endDate) {

        return ResponseEntity.ok(currencyService.calculateAndLogCurrency(currency, startDate, endDate));
    }
}