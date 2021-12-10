package todo.app.model;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Task {
    private int taskId;
    private String title;
    private String description;
    private LocalDate limitDate;
    private String responsibleName;
    private String tag;
    public Task() {
    }
}
