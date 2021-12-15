package todo.app.repository;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Introspected
@Getter
public class TaskUpdateCommand {
    @NotNull
    private final Long id;
    private final String title;
    private final String description;
    private final Integer status;
    private final String limit_date;
    private final String owner;
    private final String tag;

    public TaskUpdateCommand(Long id, String title, String description, Integer status, String limit_date, String owner, String tag) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.limit_date = limit_date;
        this.owner = owner;
        this.tag = tag;
    }
}
