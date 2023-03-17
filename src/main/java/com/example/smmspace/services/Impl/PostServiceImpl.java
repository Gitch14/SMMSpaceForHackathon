package com.example.smmspace.services.Impl;

import com.example.smmspace.models.Image;
import com.example.smmspace.models.Post;
import com.example.smmspace.models.User;
import com.example.smmspace.repositories.ImageRepository;
import com.example.smmspace.repositories.PostRepository;
import com.example.smmspace.repositories.UserRepository;
import com.example.smmspace.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static Logger logger = Logger.getLogger(PostServiceImpl.class.getName());
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private static final String UPLOAD_DIR = "src/main/resources/uploads";
    private static final String UPLOAD_DIR_TO_DB = "../../uploads";
    private static final String ABSOLUTE_UPLOAD_DIR = "D:/SMMSpace-master/";


    public ResponseEntity<Post> createPost(Principal principal, Post post, @RequestParam("images") MultipartFile[] images) throws IOException {
        post.setUser(getUserByPrincipal(principal));
        postRepository.save(post);
        List<Image> postImages = new ArrayList<>();
        Boolean isPreview = true;
        for (MultipartFile imageFile : images) {

            String uploadPath = UPLOAD_DIR + "/" + post.getId();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(imageFile.getOriginalFilename());
            String filepath = ABSOLUTE_UPLOAD_DIR + uploadPath + "/" + filename;
            File dest = new File(filepath);
            imageFile.transferTo(dest);
            logger.info("Images was saving on server");

            Image image = new Image();
            image.setOriginalFileName(imageFile.getOriginalFilename());
            image.setContentType(imageFile.getContentType());
            image.setSize(imageFile.getSize());
            image.setName(filename);
            image.setPath(UPLOAD_DIR_TO_DB+ "/" +post.getId());
            image.setPost(post);
            if (post.getPreviewImageId() == null) {
                image.setPreviewImage(isPreview);
                post.setPreviewImageId(image.getId());
                isPreview = false;
            }
            postImages.add(imageRepository.save(image));
            logger.info("Images was saving in database");
        }
        post.setImages(postImages);
        Post savedPost = postRepository.save(post);
        logger.info("Post was saving");
        return ResponseEntity.ok(savedPost);
    }


    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }


    public void deleteProduct(Long id) {
        Post item = postRepository.findById(id).orElseThrow();
        postRepository.delete(item);
    }

    public Post getProductById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
