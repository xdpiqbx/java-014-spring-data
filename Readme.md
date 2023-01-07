# java-014-spring-data

---

[**ResponseEntity to Manipulate the HTTP Response**](https://www.baeldung.com/spring-response-entity)

ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response.

---

<img src="./imgs/003 Spring Data.svg" align="center" alt="Lesson schema"/>

## @Repository

```java
@Entity
public class User {
  @Id
  private String email;
}
```

```java
@Repository
public interface UserRepository extends JpaRepository<User, String> {
  // JpaRepository<@Entity, primary key type>
}
```

## DTO - Data Transfer Object

[Переосмысление DTO в Java](https://habr.com/ru/post/513072/)

<img src="https://blog.scottlogic.com/swaterman/assets/rethinking-the-java-dto/layers.png" align="center" alt="DTO layers" width="600"/>

---

# Summary

1. in 90% CRUD cases use -> `@Repository` `JpaRepository`
```java
  
public void save(User user){
  userRepository.save(user);
}
```

2. if something slows down use `SQL`/`HQL` methods for reading data (`SELECT`) or `Specification API`
```java
  @Query("from User u where lower(u.email) like lower(:query) or lower(u.fullName) like lower(:query)")
  List<User> search(@Param("query") String query);
```

```java
  public List<User> getUserBetween(LocalDate start, LocalDate end){
    return userRepository.findAll((root, cq, cb) -> cb.and(
        cb.greaterThanOrEqualTo(root.get("birthday"), start),
        cb.lessThanOrEqualTo(root.get("birthday"), end)
    ));
  }
//  public List<User> getUserBetween(LocalDate start, LocalDate end){
//    Specification<User> betweenSpec = new Specification<User>() {
//      @Override
//      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        return criteriaBuilder.and(
//            criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), start),
//            criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), end)
//        );
//      }
//    };
//
//    return userRepository.findAll(betweenSpec);
//  }
```

3. for read complex objects use `JdbcTemplate` (smht like `Statement`)
```java
private final NamedParameterJdbcTemplate jdbcTemplate; 
//...
public List<User> search(String query){
    String sql = "SELECT email, full_name, birthday, gender " +
      "FROM \"user\" " +
      "WHERE lower(email) LIKE lower(:query) OR lower(full_name) LIKE lower(:query)";
    List<User> result = jdbcTemplate.query(
              sql,
              Map.of("query", "%" + query + "%"),
              new UserRowMapper()
    );
    return result;
}
```