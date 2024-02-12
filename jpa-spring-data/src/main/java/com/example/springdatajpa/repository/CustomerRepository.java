package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends Repository<Customer, String> {
    Optional<Customer> findByEmail(String email); // 없으면 empty optional, 조회 결과가 두 개 이상이면 예외 발생!

//    Customer findByEmail(String email); // 없으면 null

    void delete(Customer customer); // 삭제할 대상이 없으면 예외 발생!

    void deleteByEmail(String email);

    // save 쿼리를 실행하면 select 쿼리 후 insert 가 실행됨
    // 왜? customer의 식별자가 string이고 string은 참조값인데, spring-data-jpa에서 새로운 엔티티를 판단하는 기준이
    // 참조값이면 null일 경우에 새로운 값이라고 판단하기 때문.
    // 새로운 값이면 em.persist를 하고, 기존 값이면 em.merge를 호출하는데, em.merge를 하면 select 후 존재하면 저장하는 방식
    // 항상 persist를 하게 하려면 entity 클래스에 Persistable을 상속하여 메소드를 override 해야한다.
    Customer save(Customer customer);

    // 정렬 방법1. 메소드명으로 표현
    List<Customer> findByNameLikeOrderByNameDesc(String keyword);
    List<Customer> findByNameLikeOrderByNameAscEmailDesc(String keyword);

    // 정렬 방법2. Sort 활용
    // Sort.by(Sort.Order.asc("name"), Sort.Order.desc("email"))
    List<Customer> findByNameLike(String keyword, Sort sort);


    // 페이징 방법 1
    // PageRequest.ofSize(10).WithPage(1).withSort(sort)
//    List<Customer> findByNameLike(String keyword, Pageable pageable);

    // 페이징 방법2 => 전체 페이지 개수 등 페이지에 대한 정보를 같이 가져올 수 있음
    Page<Customer> findByNameLike(String keyword, Pageable pageable);

    // @Query: 메서드 명명 규칙이 아닌 JPQL을 직접 사용
    @Query("select c from Customer c where c.createdDate > :since order by c.createdDate desc")
    List<Customer> findRecentCustomers(@Param("since") LocalDateTime since);

    @Query("select c from Customer c where c.createdDate > :since")
    List<Customer> findRecentCustomers2(@Param("since") LocalDateTime since, Sort sort);

    @Query("select c from Customer c where c.createdDate > :since")
    Page<Customer> findRecentCustomers3(@Param("since") LocalDateTime since, Pageable pageable);

    // Specification: CriteriaBuilder를 통해 검색 조건을 생성하는 인터페이스
    // 직접 검색 조건을 생성한다.
    List<Customer> findAll(Specification<Customer> spec);

    List<Customer> findAll(Specification<Customer> spec, Pageable pageable);

    // 전체 개수 세기
    long count();

    long countByNameLike(String keyword);

    long count(Specification<Customer> spec);

    // nativeQuery = true: JPQL이 아닌 SQL query로 사용
    @Query(
            value="select * from customer c where c.created_date >= date_sub(now(), interval 1 day)",
            nativeQuery = true
    )
    List<Customer> findRecentUsers();

    @Query(
            value = "select max(created_date) from customer",
            nativeQuery = true
    )
    LocalDateTime selectLastCreatedDate();
}
