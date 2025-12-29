package io.AbstractCreator;

import io.AbstractProduct.DateFormatter;
import io.AbstractProduct.FileWriter;

public interface ReportCreator {
    FileWriter createFileWriter(DateFormatter formatter);
    DateFormatter createDateFormatter();
    default FileWriter createDefaultFileWriter() {
        DateFormatter formatter = createDateFormatter();
        return createFileWriter(formatter);
    }
}
