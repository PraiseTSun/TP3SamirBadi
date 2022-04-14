package javatown.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractUserForm {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String password;

    public AbstractUserForm(String id, String firstName, String lastName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
