package io;

import java.io.IOException;
import service.ExpenseRepository;

/**
 * Abstract base class for expense report writers.
 * Contains common report generation logic and formatting.
 */

public interface Report {
    void writeReport(String filePath, ExpenseRepository repository) throws IOException;

}




/*public abstract class Report {
    protected final DateTimeFormatter dateFormatter;
    protected final DateTimeFormatter monthFormatter;

    public Report() {
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
    }

    /**
     * Writes a complete expense report to a file.
     * This method contains the common report structure.
     */
    /*public void writeReport(String filePath, ExpenseRepository repository) throws IOException {
        List<Expense> allExpenses = repository.findAll();
        Summarizer summarizer = new Summarizer(allExpenses);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writeHeader(writer);
            writeMonthlySummary(writer, summarizer);
            writeCategoryBreakdown(writer, summarizer);
            writeGrandTotal(writer, summarizer);
            writeRecentEntries(writer, allExpenses);
            writeFooter(writer);
        }

        System.out.println(getReportType() + " report written to: " + filePath);
    }

    // Abstract methods for format-specific implementation
    protected abstract void writeHeader(BufferedWriter writer) throws IOException;
    protected abstract void writeFooter(BufferedWriter writer) throws IOException;
    protected abstract void writeMonthlySummarySection(BufferedWriter writer, Map<YearMonth, Double> monthlyTotals) throws IOException;
    protected abstract void writeCategoryBreakdownSection(BufferedWriter writer, Map<String, Double> categoryTotals, double maxAmount) throws IOException;
    protected abstract void writeGrandTotalSection(BufferedWriter writer, double grandTotal) throws IOException;
    protected abstract void writeRecentEntriesSection(BufferedWriter writer, List<Expense> expenses) throws IOException;
    protected abstract String getReportType();

    // Common implementation methods
    private void writeMonthlySummary(BufferedWriter writer, Summarizer summarizer) throws IOException {
        Map<YearMonth, Double> monthlyTotals = summarizer.monthlyTotals();
        writeMonthlySummarySection(writer, monthlyTotals);
    }

    private void writeCategoryBreakdown(BufferedWriter writer, Summarizer summarizer) throws IOException {
        Map<String, Double> categoryTotals = summarizer.categoryTotals(null);
        double maxAmount = categoryTotals.values().stream()
                .max(Double::compareTo)
                .orElse(1.0);
        writeCategoryBreakdownSection(writer, categoryTotals, maxAmount);
    }

    private void writeGrandTotal(BufferedWriter writer, Summarizer summarizer) throws IOException {
        double grandTotal = summarizer.grandTotal();
        writeGrandTotalSection(writer, grandTotal);
    }

    private void writeRecentEntries(BufferedWriter writer, List<Expense> expenses) throws IOException {
        writeRecentEntriesSection(writer, expenses);
    }

    // Common formatting utility methods
    protected String formatDate(LocalDate date) {
        return date.format(dateFormatter);
    }

    protected String formatMonth(YearMonth month) {
        return month.format(monthFormatter);
    }

    protected String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }

    protected String createBar(double value, double maxValue) {
        return TextUtils.createBar(value, maxValue, 30);
    }
}*/
