package javatown.DTO;

import javatown.modele.Administrator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class AdminFormDTO extends AbstractUserForm implements IFormDTO<Administrator> {
    public AdminFormDTO(String id, String firstName, String lastName, String password) {
        super(id, firstName, lastName, password);
    }

    public AdminFormDTO(Administrator admin) {
        this(
            Long.toString(admin.getId()),
            admin.getFirstName(),
            admin.getLastName(),
            admin.getPassword()
        );
    }

    @Override
    public Administrator toModel() {
        return new Administrator(firstName, lastName, password);
    }
}
