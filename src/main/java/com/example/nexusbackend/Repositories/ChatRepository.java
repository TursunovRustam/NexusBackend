package com.example.nexusbackend.Repositories;

import com.example.nexusbackend.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    List<Chat> findByUsers_Id(UUID userId);

    @Query("""
                SELECT c
                FROM Chat c
                JOIN c.users u
                WHERE u.id IN (:userId1, :userId2)
                GROUP BY c
                HAVING COUNT(DISTINCT u.id) = 2
            """)
    Optional<Chat> findChatByUsers(
            UUID userId1,
            UUID userId2
    );

}