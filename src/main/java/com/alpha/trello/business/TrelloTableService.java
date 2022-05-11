package com.alpha.trello.business;

import com.alpha.trello.dto.MessageResponse;
import com.alpha.trello.dto.TrelloTableRequest;
import com.alpha.trello.dto.UserNameRequest;
import com.alpha.trello.entity.TrelloTable;
import com.alpha.trello.entity.User;
import com.alpha.trello.repository.TrelloTableRepository;
import com.alpha.trello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrelloTableService {

    private TrelloTableRepository trelloTableRepository;
    private UserRepository userRepository;

    @Autowired
    public TrelloTableService(TrelloTableRepository trelloTableRepository, UserRepository userRepository) {
        this.trelloTableRepository = trelloTableRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> createTable(TrelloTableRequest trelloTableRequest){
        if(trelloTableRequest.getUserName() != null && userRepository.existsByUsername(trelloTableRequest.getUserName())){
            User user = userRepository.findByUsername(trelloTableRequest.getUserName()).get();
            TrelloTable table = TrelloTable.builder().title(trelloTableRequest.getTitle()).build();
            Set<TrelloTable> trelloTables = new HashSet<>();
            trelloTables.addAll(user.getTrelloTables());
            trelloTables.add(table);
            user.setTrelloTables(trelloTables);
            trelloTableRepository.save(table);
            userRepository.save(user);
            return ResponseEntity.ok().body(new MessageResponse("Table created"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User not exists!"));
        }
    }

    public ResponseEntity<?> getAllTable(String userNameRequest){
        User user = userRepository.findByUsername(userNameRequest).get();
        return ResponseEntity.ok().body(user.getTrelloTables());
    }

    public ResponseEntity<?> getInfoAboutTable(Long id){
        TrelloTable table = trelloTableRepository.findById(id).get();
        return ResponseEntity.ok().body(table);
    }

}
