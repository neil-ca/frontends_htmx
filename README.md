## Todo Application

### Testing API
```sh
# Create a task
curl -X POST http://localhost:8080/tasks \ 
    -H 'Content-Type: application/json' -d \
    '{"title":"task1","description":"blalbla",
    "status":1, "limit_date":"2001.07.04",
    "owner":"Neil", "tag":"dev"}'

# Update task
curl -X POST http://localhost:8080/tasks \ 
    -H 'Content-Type: application/json' -d \
    '{"id":1,"title":"task2","description":"blalbla",
    "status":1, "limit_date":"2001.07.04",
    "owner":"Neil", "tag":"dev"}'

# get all tasks
curl http://localhost:8080/tasks/list

# get task by id
curl http://localhost:8080/tasks/1

curl -X DELETE http://localhost:8080/tasks/1
```

Todo: adding jwt secure and change tha date



