package com.homework.jinsimver.repository.user;

import com.homework.jinsimver.entity.user.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long>, UserDetailQueryRepository {

    Optional<UserDetailEntity> findUserIdByEmail(String email);

    Optional<UserDetailEntity> findByEmail(String email);

}
