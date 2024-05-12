package com.module.batch.repository;

import com.module.batch.entity.InactiveUserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InactiveUserDetailRepository extends JpaRepository<InactiveUserDetailEntity, Long> {

}
