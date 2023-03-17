package com.example.smmspace.services;


import com.example.smmspace.models.Post;
import com.example.smmspace.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface PostService {
    public ResponseEntity<Post> createPost(Principal principal, Post post, @RequestParam("images") MultipartFile[] images) throws IOException;
    public User getUserByPrincipal(Principal principal);
    public void deleteProduct(Long id);
    public Post getProductById(Long id);
    }
