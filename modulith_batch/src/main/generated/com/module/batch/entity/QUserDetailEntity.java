package com.module.batch.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserDetailEntity is a Querydsl query type for UserDetailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserDetailEntity extends EntityPathBase<UserDetailEntity> {

    private static final long serialVersionUID = -481743007L;

    public static final QUserDetailEntity userDetailEntity = new QUserDetailEntity("userDetailEntity");

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> joinedDate = createDateTime("joinedDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> userDetailId = createNumber("userDetailId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserDetailEntity(String variable) {
        super(UserDetailEntity.class, forVariable(variable));
    }

    public QUserDetailEntity(Path<? extends UserDetailEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserDetailEntity(PathMetadata metadata) {
        super(UserDetailEntity.class, metadata);
    }

}

