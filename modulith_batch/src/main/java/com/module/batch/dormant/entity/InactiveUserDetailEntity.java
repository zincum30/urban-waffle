package com.module.batch.dormant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inactive_user_detail")
public class InactiveUserDetailEntity {

    @Id
    @Column(name = "user_detail_id", updatable = false)
    private Long userDetailId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "joined_date")
    private LocalDateTime joinedDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


}
