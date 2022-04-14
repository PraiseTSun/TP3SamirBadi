package javatown.DTO;

import javatown.modele.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class EmployeeFormDTO extends AbstractUserForm implements IFormDTO<Employee>{
    public EmployeeFormDTO(String id, String firstName, String lastName, String password) {
        super(id, firstName, lastName, password);
    }

    public EmployeeFormDTO(Employee employee){
        this(
            Long.toString(employee.getId()),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getPassword()
        );
    }

    @Override
    public Employee toModel() {
        return new Employee(firstName, lastName, password);
    }
}
