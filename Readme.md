# java-014-spring-data

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