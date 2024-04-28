package com.module.api.entity.comment;

import com.module.api.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@Entity
@Builder
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "detail")
    private String detail;

    @Column(name = "`delete`")
    private boolean delete;

    @Column(name =  "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;


//    @Builder
//    public CommentEntity(Long postId, Long userId, Long parentId, String detail, boolean delete) {
//        this.postId = postId;
//        this.userId = userId;
//        this.parentId = parentId;
//        this.detail = detail;
//        this.delete = delete;
//    }

    public String updateDetail(String detail) {
        return detail;
    }
}
