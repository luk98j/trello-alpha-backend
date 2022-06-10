package com.alpha.trello.business;

import com.alpha.trello.dto.MessageResponse;
import com.alpha.trello.dto.TrelloTodoTaskRequest;
import com.alpha.trello.entity.TrelloTodo;
import com.alpha.trello.entity.TrelloTodoTask;
import com.alpha.trello.repository.TrelloTodoRepository;
import com.alpha.trello.repository.TrelloTodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrelloTodoTaskService {
    private TrelloTodoRepository trelloTodoRepository;
    private TrelloTodoTaskRepository trelloTodoTaskRepository;

    @Autowired
    public TrelloTodoTaskService(TrelloTodoRepository trelloTodoRepository, TrelloTodoTaskRepository trelloTodoTaskRepository) {
        this.trelloTodoTaskRepository = trelloTodoTaskRepository;
        this.trelloTodoRepository = trelloTodoRepository;
    }


    public ResponseEntity<?> getAllTodoTasks(Long id) {
        TrelloTodo trelloTodo = trelloTodoRepository.getById(id);
        return ResponseEntity.ok().body(trelloTodo.getTrelloTodoTaskSet());
    }

    public ResponseEntity<?> addTodoTask(TrelloTodoTaskRequest trelloTodoTaskRequest) {
        if(trelloTodoTaskRequest.getTodoTaskId() != null){
            TrelloTodoTask trelloTodoTask = new TrelloTodoTask();
            trelloTodoTask.setName(trelloTodoTaskRequest.getName());

            TrelloTodo trelloTodo = trelloTodoRepository.getById(trelloTodoTaskRequest.getTodoTaskId());
            Set<TrelloTodoTask> trelloTodoTaskSet = new HashSet<>();
            trelloTodoTaskSet.addAll(trelloTodo.getTrelloTodoTaskSet());
            trelloTodoTaskSet.add(trelloTodoTask);
            trelloTodo.setTrelloTodoTaskSet(trelloTodoTaskSet);
            trelloTodoTaskRepository.save(trelloTodoTask);
            trelloTodoRepository.save(trelloTodo);
            return ResponseEntity.ok().body(new MessageResponse("TodoTask created"));

        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Todo not exists!"));
        }
    }
}