package com.module.batch.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInactiveUserDetailEntity is a Querydsl query type for InactiveUserDetailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInactiveUserDetailEntity extends EntityPathBase<InactiveUserDetailEntity> {

    private static final long serialVersionUID = 668997612L;

    public static final QInactiveUserDetailEntity inactiveUserDetailEntity = new QInactiveUserDetailEntity("inactiveUserDetailEntity");

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> joinedDate = createDateTime("joinedDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> userDetailId = createNumber("userDetailId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QInactiveUserDetailEntity(String variable) {
        super(InactiveUserDetailEntity.class, forVariable(variable));
    }

    public QInactiveUserDetailEntity(Path<? extends InactiveUserDetailEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInactiveUserDetailEntity(PathMetadata metadata) {
        super(InactiveUserDetailEntity.class, metadata);
    }

}

