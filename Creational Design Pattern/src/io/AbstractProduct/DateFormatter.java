package io.AbstractProduct;

import java.time.LocalDate;
import java.time.YearMonth;

public interface DateFormatter {
    String formatDate(LocalDate date);
    String formatMonth(YearMonth month);
    String formatAmount(double amount);
}
