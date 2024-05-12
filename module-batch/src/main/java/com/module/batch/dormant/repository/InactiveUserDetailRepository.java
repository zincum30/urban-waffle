package com.module.batch.dormant.repository;

import com.module.batch.dormant.entity.InactiveUserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InactiveUserDetailRepository extends JpaRepository<InactiveUserDetailEntity, Long> {

}
