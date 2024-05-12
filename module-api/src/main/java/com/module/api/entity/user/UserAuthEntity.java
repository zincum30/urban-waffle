package com.module.api.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_auth")
@Getter
@NoArgsConstructor
public class UserAuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_auth_id")
    private Long userAuthId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "password")
    private String password;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Builder
    public UserAuthEntity(Long userId, String password, LocalDateTime updatedDate) {
        this.userId = userId;
        this.password = password;
        this.updatedDate = updatedDate;
    }

    public boolean match(String password) {
        return this.password.equals(password);
    }

}
