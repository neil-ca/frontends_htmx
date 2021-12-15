package todo.app.model;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@MappedEntity
public class Task {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private int status;
    @NotNull
    private String limit_date;
    private String owner;
    private String tag;

    public Task() {
    }

    public Task(Long id, String title, String description, int status, String limit_date, String owner, String tag) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.limit_date = limit_date;
        this.owner = owner;
        this.tag = tag;
    }
}
