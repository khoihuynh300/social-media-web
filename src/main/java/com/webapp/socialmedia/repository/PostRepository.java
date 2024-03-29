package com.webapp.socialmedia.repository;

import com.webapp.socialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {
    Optional<Post> findByIdAndIsDeleted(String postId, Boolean isDeleted);

    List<Post> findByUser_IdAndIsDeletedOrderByCreatedAtDesc(String userId, Boolean isDeleted);

    @Query(value = "select * from db_post where user_id = ?1 and mode != 'PRIVATE' and is_deleted = false order by created_at DESC", nativeQuery = true)
    List<Post> findPostsWithFriends(String userId);

    @Query(value = "select * from db_post where user_id = ?1 and mode = 'PUBLIC' and is_deleted = false order by created_at DESC", nativeQuery = true)
    List<Post> findPostWithPublic(String userId);

    @Query(value = "select * from db_post where user_id = ?1 and mode != 'PRIVATE' and is_deleted = false and datediff(now(), created_at) <= ?2", nativeQuery = true)
    List<Post> findPostsWithFriendsAndDay(String userId, int day);

@Query("SELECT p FROM Post p " +
        "WHERE p.caption LIKE %:keyword% " +
        "AND (p.mode = 'PUBLIC' OR " +
        "(p.mode = 'FRIEND' AND " +
        ":userId IN " +
        "(SELECT r.user.id FROM Relationship r " +
        "WHERE r.relatedUser.id = p.user.id AND r.status = 'FRIEND')))")
    List<Post> searchPost(String keyword, String userId);
}
