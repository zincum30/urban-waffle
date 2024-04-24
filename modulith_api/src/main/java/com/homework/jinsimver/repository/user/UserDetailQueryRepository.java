package com.homework.jinsimver.repository.user;

import com.homework.jinsimver.dto.request.InactiveUserDto;
import com.homework.jinsimver.entity.user.UserDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;


public interface UserDetailQueryRepository {


    Slice<InactiveUserDto> findInactiveUsers(Integer cursorId);
}
