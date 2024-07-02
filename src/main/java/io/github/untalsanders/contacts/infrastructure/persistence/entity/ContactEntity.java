package io.github.untalsanders.contacts.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String firstname;

    private String lastname;

    @Column(nullable = false, unique = true)
    private String phone;

    public ContactEntity(Long id, String firstname, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", phone='" + phone + '\'' +
            '}';
    }
}
