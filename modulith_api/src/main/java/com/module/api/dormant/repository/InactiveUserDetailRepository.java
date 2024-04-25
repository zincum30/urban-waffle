package com.module.api.dormant.repository;

import com.module.api.dormant.entity.InactiveUserDetailEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InactiveUserDetailRepository extends JpaRepository<InactiveUserDetailEntity, Long> {

}
