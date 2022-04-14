package javatown.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Employee extends AbstractUser{
    public Employee(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }
}
