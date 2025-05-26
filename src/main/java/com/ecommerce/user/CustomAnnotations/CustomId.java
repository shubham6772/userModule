package com.ecommerce.user.CustomAnnotations;

import com.ecommerce.user.util.UniqueIDGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
@IdGeneratorType(UniqueIDGenerator.class)
public @interface CustomId {
}
