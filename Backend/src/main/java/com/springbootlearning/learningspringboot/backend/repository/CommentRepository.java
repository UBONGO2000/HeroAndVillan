package com.springbootlearning.learningspringboot.backend.repository;

import com.springbootlearning.learningspringboot.backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository JPA pour l'entité Comment.
 * 
 * <p>Fournit les opérations CRUD de base pour les commentaires.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    /**
     * Trouve tous les commentaires d'un article de blog.
     * 
     * @param blogId l'identifiant de l'article de blog
     * @return la liste des commentaires de cet article
     */
    List<Comment> findByBlogId(UUID blogId);

    /**
     * Trouve tous les commentaires d'un auteur donné.
     * 
     * @param authorName le nom de l'auteur
     * @return la liste des commentaires de cet auteur
     */
    List<Comment> findByAuthorNameContainingIgnoreCase(String authorName);

    /**
     * Compte le nombre de commentaires d'un article de blog.
     * 
     * @param blogId l'identifiant de l'article de blog
     * @return le nombre de commentaires
     */
    long countByBlogId(UUID blogId);
}
