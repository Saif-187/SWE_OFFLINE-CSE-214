package io.Products;

import io.AbstractProduct.DateFormatter;
import java.time.format.DateTimeFormatter;

public class SimpleDateFormatter implements DateFormatter {
    protected final DateTimeFormatter dateFormatter;
    protected final DateTimeFormatter monthFormatter;
    public SimpleDateFormatter() {
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
    }
    @Override
    public String formatDate(java.time.LocalDate date) {
        return date.format(dateFormatter);
    }
    @Override
    public String formatMonth(java.time.YearMonth month) {
        return month.format(monthFormatter);
    }
    @Override
    public String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }
}
