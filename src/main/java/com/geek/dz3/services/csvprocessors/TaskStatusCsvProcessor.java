package com.geek.dz3.services.csvprocessors;

import com.geek.dz3.entities.Task;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class TaskStatusCsvProcessor extends CellProcessorAdaptor {
    public TaskStatusCsvProcessor() {
        super();
    }

    @Override
    public <T> T execute(Object o, CsvContext csvContext) {
        validateInputNotNull(o, csvContext);

        for (Task.TaskStatus taskStatus : Task.TaskStatus.values()) {
            if (taskStatus.toString().equalsIgnoreCase(o.toString())) {
                return next.execute(taskStatus, csvContext);
            }
        }

        throw new SuperCsvCellProcessorException(
                String.format("Could not parse '%s' as a TaskStatus", o), csvContext, this);
    }
}
