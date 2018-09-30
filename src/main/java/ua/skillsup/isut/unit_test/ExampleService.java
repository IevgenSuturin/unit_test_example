package ua.skillsup.isut.unit_test;

import java.time.LocalDate;
import java.util.Objects;

public class ExampleService {
    private final TimeService timeService;
    private final IdGenerator generator;

    public ExampleService(TimeService timeService, IdGenerator generator) {
        this.timeService = timeService;
        this.generator = generator;
    }

    /**
     * Generate an netity with given parameter, assign random ID to generated entity.
     * @param name future entity name, mandatory, shoudn't be empty
     * @param dateBirth date of birth, entity should be created only for persons older than 30 years
     * @return generated entity
     */
    public ExampleEntity createEntity(String name, LocalDate dateBirth) {
        validateIsEmpty(name, "Name is mandatory!");
        validateDateBirth(dateBirth, 30, "Mandatory requirement for 30 years old");
        return new ExampleEntity(generator.nextId(), name, dateBirth);
    }

    private static void validateIsEmpty(String value, String errorMessage) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateDateBirth(LocalDate dateBirth, int yearsRestricted, String message) {
        if (Objects.isNull(dateBirth) || dateBirth.isAfter(timeService.currentDate().minusYears(yearsRestricted))) {
            throw new IllegalArgumentException(message);
        }
    }

}
