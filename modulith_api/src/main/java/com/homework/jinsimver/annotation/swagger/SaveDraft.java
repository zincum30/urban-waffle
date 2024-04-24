package com.homework.jinsimver.annotation.swagger;

import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "포스트 임시저장", description = "포스트 작성 버튼 클릭 시 post-id를 생성하여 저장한다.")
public @interface SaveDraft {
}
