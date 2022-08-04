package ru.kolesnik.sheetposting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolesnik.sheetposting.exception.MessageNotFound;
import ru.kolesnik.sheetposting.payload.MessageRequest;
import ru.kolesnik.sheetposting.repository.MessageRepository;
import ru.kolesnik.sheetposting.repository.entity.Message;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;

    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable long id) {
        return messageRepository.findById(id).orElseThrow(MessageNotFound::new);
    }

    @PostMapping
    public Message addMessage(@RequestBody MessageRequest messageRequest) {
        Message newMessage = new Message(messageRequest.getText());
        messageRepository.save(newMessage);
        return newMessage;
    }

    @PutMapping("/{id}")
    public Message updateMessage(@RequestBody MessageRequest messageRequest, @PathVariable long id) {
        Message messageToUpdate = messageRepository.findById(id).orElseThrow(MessageNotFound::new);
        messageToUpdate.setText(messageRequest.getText());
        messageRepository.save(messageToUpdate);
        return messageToUpdate;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable long id) {
        Message messageToDelete = messageRepository.findById(id).orElseThrow(MessageNotFound::new);
        messageRepository.delete(messageToDelete);
    }

}
