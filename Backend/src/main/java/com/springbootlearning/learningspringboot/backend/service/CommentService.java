package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.CommentDto;
import com.springbootlearning.learningspringboot.backend.entity.Blog;
import com.springbootlearning.learningspringboot.backend.entity.Comment;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.repository.BlogRepository;
import com.springbootlearning.learningspringboot.backend.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service pour la gestion des Commentaires.
 * 
 * <p>Ce service fournit les opérations CRUD pour les commentaires,
 * incluant la gestion des relations avec les blogs.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    /**
     * Récupère tous les commentaires.
     * 
     * @return une Liste de tous les commentaires
     */
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    /**
     * Récupère un commentaire par son identifiant.
     * 
     * @param id l'identifiant du commentaire
     * @return le commentaire trouvé
     * @throws NotFoundException si le commentaire n'est pas trouvé
     */
    public Comment findCommentById(UUID id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Comment by id " + id + " was not found")
        );
    }

    /**
     * Récupère tous les commentaires d'un blog.
     * 
     * @param blogId l'identifiant du blog
     * @return une Liste des commentaires du blog
     */
    public List<Comment> findCommentsByBlogId(UUID blogId) {
        return commentRepository.findByBlogId(blogId);
    }

    /**
     * Supprime un commentaire par son identifiant.
     * 
     * @param id l'identifiant du commentaire à supprimer
     */
    @Transactional
    public void removeCommentById(UUID id) {
        commentRepository.deleteById(id);
        logger.info("Comment deleted successfully with id: {}", id);
    }

    /**
     * Crée un nouveau commentaire.
     * 
     * @param commentDto les données du commentaire à créer
     * @return le commentaire créé
     * @throws NotFoundException si le blog spécifié n'existe pas
     */
    @Transactional
    public Comment createComment(CommentDto commentDto) {
        Comment comment = new Comment();

        updateCommentFromDto(comment, commentDto);

        Comment savedComment = commentRepository.save(comment);
        logger.info("Comment created successfully with id: {}", savedComment.getId());
        return savedComment;
    }

    /**
     * Met à jour un commentaire existant.
     * 
     * @param id l'identifiant du commentaire à mettre à jour
     * @param commentDto les nouvelles données du commentaire
     * @return le commentaire mis à jour
     * @throws NotFoundException si le commentaire ou le blog n'est pas trouvé
     */
    @Transactional
    public Comment updateComment(UUID id, CommentDto commentDto) {
        Comment comment = findCommentById(id);

        updateCommentFromDto(comment, commentDto);

        Comment updatedComment = commentRepository.save(comment);
        logger.info("Comment updated successfully with id: {}", updatedComment.getId());
        return updatedComment;
    }

    /**
     * Met à jour les propriétés d'un commentaire à partir du DTO.
     * 
     * @param comment le commentaire à mettre à jour
     * @param commentDto les données source
     */
    private void updateCommentFromDto(Comment comment, CommentDto commentDto) {
        comment.setAuthorName(commentDto.getAuthorName());
        comment.setContent(commentDto.getContent());

        if (commentDto.getBlogId() != null) {
            Blog blog = blogRepository.findById(commentDto.getBlogId())
                    .orElseThrow(() -> new NotFoundException("Blog by id " + commentDto.getBlogId() + " was not found"));
            comment.setBlog(blog);
        }
    }
}
