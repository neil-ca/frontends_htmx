package todo.app.repository;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.jdbc.annotation.JdbcRepository;

import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import todo.app.model.Task;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

//import static io.micronaut.data.model.query.builder.sql.Dialect.POSTGRES;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TaskRepository extends PageableRepository<Task, Long> {
    Task save(Task task);

//    @Transactional
//    default Task saveWithException(@NotNull @NotBlank String title) {
//        save(title);
//        throw new DataAccessException("test exception");
//    }
    int update(@NotNull @NotNull @Id Long id, String title, String description, Integer status,
               String limit_date, String owner, String tag);

}
