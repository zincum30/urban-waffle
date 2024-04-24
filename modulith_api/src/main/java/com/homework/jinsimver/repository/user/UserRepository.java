package com.homework.jinsimver.repository.user;

import com.homework.jinsimver.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByNickname(String nickname);

    UserEntity getById(Long userId);


}
