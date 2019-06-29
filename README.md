# spring-boot-issue-16893-demo

[#16893](https://github.com/spring-projects/spring-boot/issues/16893)

## Test Problem

Modify **application.yml**, Comment the following code:
```yaml
  properties:
      hibernate:
        dialect: com.example.demo.hibernate.dialect.MariaDB103Dialect
```