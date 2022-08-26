package com.duberlyguarnizo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "politicians_manager")
public class PoliticiansManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "politician_id")
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column
    private String country;
    @Column
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoliticiansManager that = (PoliticiansManager) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
