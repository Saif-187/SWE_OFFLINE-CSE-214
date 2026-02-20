package io.Creator;

import io.AbstractCreator.ReportCreator;
import io.Products.HtmlWriter;
import io.Products.SimpleDateFormatter;
import io.AbstractProduct.DateFormatter;
import io.AbstractProduct.FileWriter;


public class HtmlCreator implements ReportCreator{
    @Override
    public FileWriter createFileWriter(DateFormatter formatter) {
        return new HtmlWriter(formatter);
    }
    @Override
    public DateFormatter createDateFormatter() {
        return new SimpleDateFormatter();
    }
}
