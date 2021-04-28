package com.mastery.java.task.validation;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.entity.Gender;
import com.mastery.java.task.exception.NotValidDataException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;

@Service
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Employee employee = (Employee) o;

        if (!validateName(employee) || !validateDepartment(employee) ||
                !validateGender(employee) || !validateAge(employee)) {
            errors.reject("wrongData", "value.incorrect");
            throw new NotValidDataException("Wrong data input!");
        }
    }

    private boolean validateName(Employee employee) {
        return (employee.getFirstName().length() > 0) && (employee.getLastName().length() > 0);
    }

    private boolean validateDepartment(Employee employee) {
        return employee.getDepartmentId() > 0;
    }

    private boolean validateGender(Employee employee) {
        return (employee.getGender().equals(Gender.MALE)) || (employee.getGender().equals(Gender.FEMALE));
    }

    private boolean validateAge(Employee employee) {
        return Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears() > 18;
    }
}

