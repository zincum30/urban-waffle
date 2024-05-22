package com.module.api.repository.user;

import com.module.api.entity.user.UserDetailEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long> {

    Optional<UserDetailEntity> findUserIdByEmail(String email);

    Optional<UserDetailEntity> findByEmail(String email);

    Optional<UserDetailEntity> findByUserId(Long userId);

    boolean existsByEmail(String email);

    UserDetailEntity getReferenceByEmail(String email);
}
