package com.homework.jinsimver.dormant.repository;

import com.homework.jinsimver.dormant.entity.InactiveUserDetailEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InactiveUserDetailRepository extends JpaRepository<InactiveUserDetailEntity, Long> {

}
