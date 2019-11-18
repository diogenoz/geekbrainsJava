package com.geek.dz3.services.csvprocessors;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import java.util.UUID;


public class UuidCsvProcessor extends CellProcessorAdaptor {

    public UuidCsvProcessor() {
        super();
    }

    @Override
    public <T> T execute(Object o, CsvContext csvContext) {
        validateInputNotNull(o, csvContext);  // throws an Exception if the input is null
        UUID uuid;
        try {
            uuid = UUID.fromString(o.toString());
        } catch (RuntimeException e) {
            throw new SuperCsvCellProcessorException(
                    String.format("Could not parse '%s' as a UUID", o), csvContext, this);
        }

        return next.execute(uuid, csvContext);

    }
}
