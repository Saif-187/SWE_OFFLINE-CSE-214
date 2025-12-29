package io.AbstractProduct;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public interface FileWriter {
    void writeHeader(BufferedWriter writer) throws IOException;
    void writeMonthlySummary(BufferedWriter writer, service.Summarizer summarizer) throws IOException;
    void writeCategoryBreakdown(BufferedWriter writer, service.Summarizer summarizer) throws IOException;
    void writeGrandTotal(BufferedWriter writer, service.Summarizer summarizer) throws IOException;
    void writeRecentEntries(BufferedWriter writer, List<model.Expense> expenses) throws IOException;
    String createBar(double value, double maxValue);
}
