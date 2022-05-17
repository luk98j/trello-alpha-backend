package com.alpha.trello.business;

import com.alpha.trello.dto.MessageResponse;
import com.alpha.trello.dto.TrelloTodoRequest;
import com.alpha.trello.entity.TrelloCard;
import com.alpha.trello.entity.TrelloTodo;
import com.alpha.trello.repository.TrelloCardRepository;
import com.alpha.trello.repository.TrelloTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrelloTodoService {
    private TrelloCardRepository trelloCardRepository;
    private TrelloTodoRepository trelloTodoRepository;

    @Autowired
    public TrelloTodoService(TrelloCardRepository trelloCardRepository, TrelloTodoRepository trelloTodoRepository) {
        this.trelloTodoRepository = trelloTodoRepository;
        this.trelloCardRepository = trelloCardRepository;
    }


    public ResponseEntity<?> getAllTodos(Long id) {
        TrelloCard trelloCard = trelloCardRepository.getById(id);
        return ResponseEntity.ok().body(trelloCard.getTrelloTodoSet());
    }

    public ResponseEntity<?> addTodo(TrelloTodoRequest trelloTodoRequest) {
        if(trelloTodoRequest.getCardId() != null){
            TrelloTodo trelloTodo = new TrelloTodo();
            trelloTodo.setName(trelloTodoRequest.getName());

            TrelloCard trelloCard = trelloCardRepository.getById(trelloTodoRequest.getCardId());
            Set<TrelloTodo> trelloTodoSet = new HashSet<>();
            trelloTodoSet.addAll(trelloCard.getTrelloTodoSet());
            trelloTodoSet.add(trelloTodo);
            trelloCard.setTrelloTodoSet(trelloTodoSet);
            trelloTodoRepository.save(trelloTodo);
            trelloCardRepository.save(trelloCard);
            return ResponseEntity.ok().body(new MessageResponse("Todo created"));

        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: card not exists!"));
        }
    }
}