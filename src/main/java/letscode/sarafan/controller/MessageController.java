/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letscode.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDateTime;
import java.util.List;
import letscode.sarafan.domain.Message;
import letscode.sarafan.domain.Views;
import letscode.sarafan.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alexandr
 */
@RestController
@RequestMapping("message")
public class MessageController {
    
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }
    
    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepo.findAll();
    }
    
    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }
    
    @PostMapping
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }
    
    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDB, 
            @RequestBody Message message){
        BeanUtils.copyProperties(message, messageFromDB,"id");
        
        return messageRepo.save(messageFromDB);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);
    }
}
