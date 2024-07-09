package com.trvankiet.app.repository;

import com.trvankiet.app.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findAllByReceiverId(String receiverId);

    Page<ChatMessage> findAllByReceiverIdAndIsDeleted(String receiverId, Boolean isDeleted, Pageable pageable);
    Page<ChatMessage> findAllByReceiverId(String receiverId, Pageable pageable);
    
    Page<ChatMessage> findAllByReceiverIdAndSenderIdAndIsDeleted(String receiverId, String senderId, Boolean isDeleted, Pageable pageable);
    Page<ChatMessage> findAllByReceiverIdAndSenderId(String receiverId, String senderId, Pageable pageable);

    // findAll ReceiverId or SenderId is id1 and ReceiverId or SenderId is id2, sortBy createdAt DESC
    @Query(value = "{$or: [{'receiver_id': ?0, 'sender_id': ?1}, {'receiver_id': ?1, 'sender_id': ?0}]}",
            sort = "{'created_at': -1}")
    Page<ChatMessage> findAllByReceiverIdOrSenderId(String id1, String id2, Pageable pageable);

    // find All by ReeceiverId is id1 or SenderId is id1

    @Query(value = "{$or: [{'receiver_id': ?0}, {'sender_id': ?0}], 'is_deleted': false}",
            sort = "{'created_at': -1}")
    Page<ChatMessage> findAllByReceiverIdOrSenderId(String id1, Pageable pageable);

    Page<ChatMessage> findAllByChatRoomId(String chatRoomId, Pageable pageable);

    Optional<ChatMessage> findByContentAndSenderIdAndReceiverIdAndChatRoomIdAndCreatedAt(String content, String senderId, String receiverId, String chatRoomId, Date createdAt);
    
    Optional<ChatMessage> findBySenderIdAndContentAndCreatedAt(String senderId, String content, Date createdAt);
}
