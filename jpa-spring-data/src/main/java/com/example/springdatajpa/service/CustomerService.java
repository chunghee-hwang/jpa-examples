package com.example.springdatajpa.service;

import com.example.springdatajpa.entity.Customer;
import com.example.springdatajpa.repository.CustomerRepository;
import com.example.springdatajpa.repository.spec.UserNameSpecification;
import com.example.springdatajpa.repository.spec.UserSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private CustomerRepository repository;
    public void findAllCustomer() {
        // 방법 1
//        UserNameSpecification spec = new UserNameSpecification("홍길동");

        // 방법 2(추천)
        Specification<Customer> spec = UserSpecs.nameLike("홍길동");
        // 스펙 끼리 조합도 가능
//        spec.and(spec2).or(spec3);
        this.repository.findAll(spec);
    }
}
