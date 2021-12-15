package todo.app.controller;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import todo.app.model.Task;
import todo.app.repository.TaskRepository;
import todo.app.repository.TaskUpdateCommand;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller("/tasks")
public class TaskController {
    public final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Get("/{id}")
    public Optional<Task> show(Long id) {
        return taskRepository.findById(id);
    }

    @Put
    public HttpResponse update(@Body @Valid TaskUpdateCommand command) {
        taskRepository.update(command.getId(), command.getTitle(), command.getDescription(), command.getStatus(),
                command.getLimit_date(), command.getOwner(), command.getTag());
        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }

    @Get(value="/list")
    public List<Task> list(@Valid Pageable pageable) {
        return taskRepository.findAll(pageable).getContent();
    }
    @Post
    public HttpResponse<Task> save(@Body Task task) {
        Task newTask = taskRepository.save(task);
        return  HttpResponse
                .created(newTask)
                .headers(headers -> headers.location(location(task.getId())));
    }
//    @Post("/ex")
//    public HttpResponse<Task> saveExceptions(@Body @NotBlank String title) {
//        try {
//            Task task = taskRepository.saveWithException(title);
//            return HttpResponse
//                    .created(task)
//                    .headers(headers -> headers.location(location(task.getId())));
//        } catch(DataAccessException e) {
//            return HttpResponse.noContent();
//        }
//    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    protected URI location(Long id) {
        return URI.create("/tasks/" + id);
    }

    protected URI location(Task task) {
        return location(task.getId());
    }
}
