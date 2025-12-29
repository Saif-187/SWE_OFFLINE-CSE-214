package io.Creator;
import io.AbstractCreator.ReportCreator;
import io.AbstractProduct.DateFormatter;
import io.AbstractProduct.FileWriter;
import io.Products.SimpleDateFormatter;
import io.Products.TxtWriter;
public class TxtCreator implements ReportCreator{
    @Override
    public FileWriter createFileWriter(DateFormatter formatter) {
        return new TxtWriter(formatter);
    }
    @Override
    public DateFormatter createDateFormatter() {
        return new SimpleDateFormatter();
    }
    
}
