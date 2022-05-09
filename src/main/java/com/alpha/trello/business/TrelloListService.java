package com.alpha.trello.business;

import com.alpha.trello.dto.MessageResponse;
import com.alpha.trello.dto.TrelloListRequest;
import com.alpha.trello.entity.TrelloList;
import com.alpha.trello.entity.TrelloTable;
import com.alpha.trello.entity.User;
import com.alpha.trello.repository.TrelloListRepository;
import com.alpha.trello.repository.TrelloTableRepository;
import com.alpha.trello.repository.UserRepository;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrelloListService {

    private TrelloListRepository trelloListRepository;
    private TrelloTableRepository tableRepository;

    @Autowired
    public TrelloListService(TrelloListRepository trellolistRepository, TrelloTableRepository tableRepository) {
        this.trelloListRepository = trellolistRepository;
        this.tableRepository = tableRepository;
    }


    public ResponseEntity<?> getList(Long idRequest){
        TrelloTable list = tableRepository.findById(idRequest).get();
        return ResponseEntity.ok().body(list.getTrelloList());
    }

}
