package com.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1174962717L;

    public static final QUser user = new QUser("user");

    public final com.core.entity.QBaseEntity _super = new com.core.entity.QBaseEntity(this);

    public final StringPath certificate = createString("certificate");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath nameSpell = createString("nameSpell");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<Role, QRole> role = this.<Role, QRole>createList("role", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath salt = createString("salt");

    public final StringPath userName = createString("userName");

    public final StringPath userType = createString("userType");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

