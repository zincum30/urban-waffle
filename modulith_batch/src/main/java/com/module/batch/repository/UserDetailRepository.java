package com.module.batch.repository;


import com.module.batch.entity.UserDetailEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long>, UserDetailQueryRepository {


}
