package pl.pjatk.jaz34251nbp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class QueryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;
    private LocalDate startDate;
    private LocalDate endDate;
    private double calculatedRate;
    private LocalDateTime requestDateTime;

    public QueryHistory() {}

    public QueryHistory(String currency, LocalDate startDate, LocalDate endDate, double calculatedRate, LocalDateTime requestDateTime) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.calculatedRate = calculatedRate;
        this.requestDateTime = requestDateTime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public double getCalculatedRate() { return calculatedRate; }
    public void setCalculatedRate(double calculatedRate) { this.calculatedRate = calculatedRate; }

    public LocalDateTime getRequestDateTime() { return requestDateTime; }
    public void setRequestDateTime(LocalDateTime requestDateTime) { this.requestDateTime = requestDateTime; }
}