package com.example.smmspace.controllers;

import com.example.smmspace.models.Post;
import com.example.smmspace.models.User;
import com.example.smmspace.repositories.PostRepository;
import com.example.smmspace.services.Impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostServiceImpl postServiceImpl;
    private final PostRepository postRepository;

    @GetMapping("/")
    public String products(@RequestParam(name = "searchWord", required = false) String text, Principal principal, Model model) {

        if (text != null) {
            List<Post> titleSearch = postRepository.findByTitle(text);
            List<Post> typeSearch = postRepository.findByType(text);
            if (titleSearch.size() == 0 && typeSearch.size() == 0){
                model.addAttribute("posts", Collections.emptyList());
            } else {

                List<Post> filteredCourses = new ArrayList<>();
                filteredCourses.addAll(titleSearch);
                filteredCourses.addAll(typeSearch);
                model.addAttribute("posts", filteredCourses);
            }
        } else {
            model.addAttribute("posts", postRepository.findAll());
        }
        model.addAttribute("searchWord", text);
        model.addAttribute("user", postServiceImpl.getUserByPrincipal(principal));

        return "posts";
    }

    @GetMapping("/post/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal, HttpServletRequest request) {
        Post post = postServiceImpl.getProductById(id);
        model.addAttribute("user", postServiceImpl.getUserByPrincipal(principal));
        model.addAttribute("post", post);
        model.addAttribute("images", post.getImages());
        model.addAttribute("authorPost", post.getUser());
        String contextPath = request.getContextPath();
        model.addAttribute("requestContext", contextPath);
        return "posts-info";
    }


    @PostMapping("/post/create")
    public String createProduct(@RequestParam("file1") MultipartFile[] file,Post post, Principal principal) throws IOException {
        postServiceImpl.createPost(principal, post, file);
        return "redirect:/my/posts";
    }

    @PostMapping("/post/delete/{id}")
    public String itemDelete(@PathVariable(value = "id") long id){
        postServiceImpl.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/my/posts")
    public String userProducts(Principal principal, Model model) {
        User user = postServiceImpl.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        return "my-posts";
    }

    @GetMapping("/create")
    public String userCreateProducts(Principal principal, Model model) {
        User user = postServiceImpl.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        return "create";
    }

    @GetMapping("/about")
    public String about(Model model,Principal principal) {
        User user = postServiceImpl.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "about";
    }
}
