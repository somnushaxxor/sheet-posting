package ru.kolesnik.sheetposting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolesnik.sheetposting.exception.PostNotFound;
import ru.kolesnik.sheetposting.payload.PostRequest;
import ru.kolesnik.sheetposting.repository.PostRepository;
import ru.kolesnik.sheetposting.repository.entity.Post;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping
    public List<Post> getAllMessages() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getMessage(@PathVariable long id) {
        return postRepository.findById(id).orElseThrow(PostNotFound::new);
    }

    @PostMapping
    public Post addMessage(@RequestBody PostRequest postRequest) {
        Post newPost = new Post(postRequest.getText());
        postRepository.save(newPost);
        return newPost;
    }

    @PutMapping("/{id}")
    public Post updateMessage(@RequestBody PostRequest postRequest, @PathVariable long id) {
        Post postToUpdate = postRepository.findById(id).orElseThrow(PostNotFound::new);
        postToUpdate.setText(postRequest.getText());
        postRepository.save(postToUpdate);
        return postToUpdate;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable long id) {
        Post postToDelete = postRepository.findById(id).orElseThrow(PostNotFound::new);
        postRepository.delete(postToDelete);
    }

}
