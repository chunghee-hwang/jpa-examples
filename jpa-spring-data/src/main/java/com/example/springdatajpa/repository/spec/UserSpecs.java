package com.example.springdatajpa.repository.spec;

import com.example.springdatajpa.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

// 람다를 이용한 간단한 Spec 생성 방법
public class UserSpecs {
    public static Specification<Customer> nameLike(String name) {
        return ((root, query, cb) -> cb.like(root.get("name"), "%" + name + "%"));
    }
}
