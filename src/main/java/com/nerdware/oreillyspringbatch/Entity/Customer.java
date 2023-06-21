package com.nerdware.oreillyspringbatch.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String lastName;
        private String birthdate;

        public Customer(long id, String firstName, String lastName, String birthdate) {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.birthdate = birthdate;
        }

}
