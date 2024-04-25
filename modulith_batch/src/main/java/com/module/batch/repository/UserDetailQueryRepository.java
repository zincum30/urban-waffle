package com.module.batch.repository;


import com.module.batch.dto.InactiveUserDto;
import org.springframework.data.domain.Slice;


public interface UserDetailQueryRepository {


    Slice<InactiveUserDto> findInactiveUsers(Long cursorId);
}
