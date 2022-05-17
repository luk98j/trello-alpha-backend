package com.alpha.trello.business;

import com.alpha.trello.dto.MessageResponse;
import com.alpha.trello.dto.TrelloCardRequest;
import com.alpha.trello.entity.TrelloCard;
import com.alpha.trello.entity.TrelloList;
import com.alpha.trello.repository.TrelloCardRepository;
import com.alpha.trello.repository.TrelloListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrelloCardService {
    private TrelloListRepository trelloListRepository;
    private TrelloCardRepository trelloCardRepository;

    @Autowired
    public TrelloCardService(TrelloListRepository trelloListRepository, TrelloCardRepository trelloCardRepository) {
        this.trelloListRepository = trelloListRepository;
        this.trelloCardRepository = trelloCardRepository;
    }


    public ResponseEntity<?> addCard(TrelloCardRequest trelloCardRequest) {
        if(trelloCardRequest.getListId() != null && trelloCardRequest.getTitle() != null){
            TrelloCard trelloCard = new TrelloCard();
            trelloCard.setTitle(trelloCardRequest.getTitle());
            if(trelloCardRequest.getDescription()!=null){
                trelloCard.setDescription(trelloCardRequest.getDescription());
            }
            TrelloList trelloList = trelloListRepository.getById(trelloCardRequest.getListId());
            Set<TrelloCard> trelloCardSet = new HashSet<>();
            trelloCardSet.addAll(trelloList.getTrelloCardSet());
            trelloCardSet.add(trelloCard);
            trelloList.setTrelloCardSet(trelloCardSet);
            trelloCardRepository.save(trelloCard);
            trelloListRepository.save(trelloList);
            return ResponseEntity.ok().body(new MessageResponse("Card created"));

        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: List not exists!"));
        }
    }

    public ResponseEntity<?> getAllCards(Long id) {
        TrelloList trelloList = trelloListRepository.getById(id);
        return ResponseEntity.ok().body(trelloList.getTrelloCardSet());
    }

    public ResponseEntity<?> editCard(TrelloCardRequest trelloCardRequest) {
        if(trelloCardRequest.getCardId() != null){
            TrelloCard trelloCard = trelloCardRepository.getById(trelloCardRequest.getCardId());
            trelloCard.setTitle(trelloCardRequest.getTitle());
            if(trelloCardRequest.getDescription()!=null){
                trelloCard.setDescription(trelloCardRequest.getDescription());
            }
            trelloCardRepository.save(trelloCard);
            return ResponseEntity.ok().body(new MessageResponse("Card edited"));

        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: KABOOM!"));
        }
    }
}