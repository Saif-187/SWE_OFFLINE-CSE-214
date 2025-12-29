package io;

import io.AbstractCreator.ReportCreator;
import io.AbstractProduct.FileWriter;
import io.Creator.TxtCreator;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import model.Expense;
import service.ExpenseRepository;
import service.Summarizer;

/**
 * Writes plain-text expense reports with ASCII formatting.
 */

public class TxtReportWriter implements Report {
    ReportCreator creator=new TxtCreator();
    FileWriter writer= creator.createDefaultFileWriter();
    @Override
    public void writeReport(String filePath, ExpenseRepository repository) throws IOException {
        List<Expense> allExpenses = repository.findAll();
        Summarizer summarizer = new Summarizer(allExpenses);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new java.io.FileWriter(filePath))) {
            writer.writeHeader(bufferedWriter);
            writer.writeMonthlySummary(bufferedWriter, summarizer);
            writer.writeCategoryBreakdown(bufferedWriter, summarizer);
            writer.writeGrandTotal(bufferedWriter, summarizer);
            writer.writeRecentEntries(bufferedWriter, allExpenses);
        }

        System.out.println("Text report written to: " + filePath);
    }

}



/*public class TxtReportWriter extends Report { 
    
    public TxtReportWriter() {
        super();  
    }
    @Override
    protected void writeHeader(BufferedWriter writer) throws IOException {
        writer.write("=====================================\n");
        writer.write("       BUDGETBUDDY EXPENSE REPORT    \n");
        writer.write("=====================================\n\n");
    }

    @Override
    protected void writeFooter(BufferedWriter writer) throws IOException {
        // Text reports don't need a footer
    }

    @Override
    protected void writeMonthlySummarySection(BufferedWriter writer, Map<YearMonth, Double> monthlyTotals) throws IOException {
        writer.write("MONTHLY SUMMARY\n");
        writer.write(TextUtils.separator(60) + "\n");

        for (Map.Entry<YearMonth, Double> entry : monthlyTotals.entrySet()) {
            String monthStr = formatMonth(entry.getKey());
            String amountStr = formatAmount(entry.getValue());
            writer.write(String.format("%-10s : %12s\n", monthStr, amountStr));
        }
        writer.write("\n");
    }

    @Override
    protected void writeCategoryBreakdownSection(BufferedWriter writer, Map<String, Double> categoryTotals, double maxAmount) throws IOException {
        writer.write("CATEGORY BREAKDOWN (All Time)\n");
        writer.write(TextUtils.separator(60) + "\n");

        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();
            String amountStr = formatAmount(amount);
            String bar = createBar(amount, maxAmount);
            writer.write(String.format("%-15s %12s  %s\n", category, amountStr, bar));
        }
        writer.write("\n");
    }

    @Override
    protected void writeGrandTotalSection(BufferedWriter writer, double grandTotal) throws IOException {
        writer.write(TextUtils.separator(60) + "\n");
        writer.write(String.format("GRAND TOTAL: %s\n", formatAmount(grandTotal)));
        writer.write(TextUtils.separator(60) + "\n");
    }

    @Override
    protected void writeRecentEntriesSection(BufferedWriter writer, List<Expense> expenses) throws IOException {
        writer.write("\nRECENT ENTRIES (Last 10)\n");
        writer.write(TextUtils.separator(60) + "\n");

        int count = 0;
        for (int i = expenses.size() - 1; i >= 0 && count < 10; i--, count++) {
            Expense exp = expenses.get(i);
            String dateStr = formatDate(exp.getDate());
            writer.write(String.format("%s  %-12s %10s  %s\n",
                    dateStr,
                    exp.getCategory(),
                    formatAmount(exp.getAmount()),
                    exp.getNotes()));
        }
    }

    @Override
    protected String getReportType() {
        return "Text";
    }
    
}*/