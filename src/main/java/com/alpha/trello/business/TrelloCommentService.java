package com.alpha.trello.business;

import com.alpha.trello.dto.MessageResponse;
import com.alpha.trello.dto.TrelloCommentRequest;
import com.alpha.trello.entity.TrelloCard;
import com.alpha.trello.entity.TrelloComment;
import com.alpha.trello.repository.TrelloCardRepository;
import com.alpha.trello.repository.TrelloCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrelloCommentService {
    private TrelloCardRepository trelloCardRepository;
    private TrelloCommentRepository trelloCommentRepository;

    @Autowired
    public TrelloCommentService(TrelloCardRepository trelloCardRepository, TrelloCommentRepository trelloCommentRepository) {
        this.trelloCommentRepository = trelloCommentRepository;
        this.trelloCardRepository = trelloCardRepository;
    }


    public ResponseEntity<?> getAllComments(Long id) {
        TrelloCard trelloCard = trelloCardRepository.getById(id);
        return ResponseEntity.ok().body(trelloCard.getTrelloCommentSet());
    }

    public ResponseEntity<?> addComment(TrelloCommentRequest trelloCommentRequest) {
        if(trelloCommentRequest.getCardId() != null){
            TrelloComment trelloComment = new TrelloComment();
            trelloComment.setComment(trelloCommentRequest.getComment());
            trelloComment.setUser_id(trelloCommentRequest.getUserId());

            TrelloCard trelloCard = trelloCardRepository.getById(trelloCommentRequest.getCardId());
            Set<TrelloComment> trelloCommentSet = new HashSet<>();
            trelloCommentSet.addAll(trelloCard.getTrelloCommentSet());
            trelloCommentSet.add(trelloComment);
            trelloCard.setTrelloCommentSet(trelloCommentSet);
            trelloCommentRepository.save(trelloComment);
            trelloCardRepository.save(trelloCard);
            return ResponseEntity.ok().body(new MessageResponse("comment created"));

        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: card not exists!"));
        }
    }
}