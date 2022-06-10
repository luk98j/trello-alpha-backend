package com.alpha.trello.business;

import com.alpha.trello.dto.*;
import com.alpha.trello.entity.TrelloTable;
import com.alpha.trello.entity.User;
import com.alpha.trello.repository.TrelloTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alpha.trello.repository.UserRepository;

import java.util.*;

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
        List<User> userList = userRepository.findAllByTrelloTablesId(id);
        TrelloTableResponse trelloTableResponse = new TrelloTableResponse(table.getId(), table.getTitle(), userList.get(0).getUsername());
        return ResponseEntity.ok().body(trelloTableResponse);
    }

    public ResponseEntity<?> postSharedTable(TrelloSharedRequest trelloSharedRequest){
        Optional<User> optionalUser = userRepository.findByUsername(trelloSharedRequest.getUserName());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            Optional<TrelloTable> optionalTrelloTable = trelloTableRepository.findById(trelloSharedRequest.getTableId());
            if(optionalTrelloTable.isPresent()){
                Set<TrelloTable> setList = user.getTrelloSharedTables();
                setList.add(optionalTrelloTable.get());
                user.setTrelloSharedTables(setList);
                userRepository.save(user);
                return ResponseEntity.ok().body("Successful sharing");
            } else {
                return ResponseEntity.badRequest().body("Table not found");
            }
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    public ResponseEntity<?> getSharedTables(String userNameRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(userNameRequest);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return ResponseEntity.ok().body(user.getTrelloSharedTables());
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }


    public ResponseEntity<?> getUserSharedTable(Long id) {
         Optional<TrelloTable> optionalTrelloTable = trelloTableRepository.findById(id);
         if(optionalTrelloTable.isPresent()){
             return ResponseEntity.ok().body(userRepository.findAllByTrelloSharedTablesId(id));
         } else {
             return ResponseEntity.badRequest().body("Table not found");
         }
    }

    public ResponseEntity<?> deleteUserFromTable(TrelloSharedRequest trelloSharedRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(trelloSharedRequest.getUserName());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            Optional<TrelloTable> optionalTrelloTable = trelloTableRepository.findById(trelloSharedRequest.getTableId());
            if(optionalTrelloTable.isPresent()){
                Set<TrelloTable> setList = user.getTrelloSharedTables();
                setList.remove(optionalTrelloTable.get());
                user.setTrelloSharedTables(setList);
                userRepository.save(user);
                return ResponseEntity.ok().body("Successful deletion of user");
            } else {
                return ResponseEntity.badRequest().body("Table not found");
            }
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
