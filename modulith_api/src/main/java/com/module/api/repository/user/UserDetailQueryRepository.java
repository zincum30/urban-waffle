package com.module.api.repository.user;

import com.module.api.dto.request.InactiveUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


public interface UserDetailQueryRepository {


    Slice<InactiveUserDto> findInactiveUsers(Integer cursorId);
}
