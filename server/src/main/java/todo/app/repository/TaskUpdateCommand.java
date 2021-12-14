package todo.app.repository;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
@Getter
public class TaskUpdateCommand {
    @NotNull
    private final Long id;

    @NotBlank
    private final String title;

    public TaskUpdateCommand(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
