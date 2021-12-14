package todo.app.model;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@MappedEntity
public class Task {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;
    @NotNull
    private String title;
//    @NotNull
//    private String description;
//    @NotNull
//    private LocalDate limitDate;
//    @NotNull
//    private String owner;
//    @NotNull
//    private String tag;
//    public Task() {
//    }
}
