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