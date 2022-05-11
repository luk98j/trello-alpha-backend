package com.alpha.trello.business;

import com.alpha.trello.dto.MessageResponse;
import com.alpha.trello.dto.TrelloListRequest;
import com.alpha.trello.entity.TrelloList;
import com.alpha.trello.entity.TrelloTable;
import com.alpha.trello.repository.TrelloListRepository;
import com.alpha.trello.repository.TrelloTableRepository;
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


    public ResponseEntity<?> addList(TrelloListRequest request) {
        if(request.getTableId() != null && request.getTitle()!=null){
            TrelloTable table = tableRepository.findById(request.getTableId()).get();
            TrelloList trelloList = new TrelloList(request.getTitle());
            Set<TrelloList> trelloLists = new HashSet<>();
            trelloLists.addAll(table.getTrelloList());
            trelloLists.add(trelloList);
            table.setTrelloList(trelloLists);
            trelloListRepository.save(trelloList);
            tableRepository.save(table);
            return ResponseEntity.ok().body(new MessageResponse("List created"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Table not exists!"));
        }
    }
}
