package com.example.demo.domain.primary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class PrimaryUser implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Setter
    @Column(nullable = false, length = 5)
    private String name;
    @Setter
    @Column(nullable = false)
    private Integer age;

    public PrimaryUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
