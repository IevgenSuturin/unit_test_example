package ua.skillsup.isut.unit_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExampleServiceTest {
    @Mock
    private TimeService timeService;
    @Mock
    private IdGenerator idGenerator;

    private ExampleService service;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.service = new ExampleService(timeService, idGenerator);
    }

    @DisplayName("Happy path Test")
    @Test
    public void testCase1(){
        //Given
        final long expectedId = 14L;
        final String expectedName = "Nice Name";
        final LocalDate currentDate = LocalDate.now();
        final LocalDate expectedDate = currentDate.minusYears(35);
        ExampleEntity expectedEntity = new ExampleEntity(expectedId, expectedName, expectedDate);

        when(timeService.currentDate()).thenReturn(currentDate);
        when(idGenerator.nextId()).thenReturn(expectedId);

        //When
        ExampleEntity actual = service.createEntity(expectedName, expectedDate);

        //Then
        assertThat(actual).isEqualToComparingFieldByField(expectedEntity);
    }

    @DisplayName("Fail on empty Name")
    @Test
    public void testCase2(){
        //Given
        final LocalDate currentDate = LocalDate.now();
        final long expectedId = 14L;
        final String expectedName = "";
        final LocalDate expectedDate = currentDate.minusYears(35);

        when(timeService.currentDate()).thenReturn(currentDate);
        when(idGenerator.nextId()).thenReturn(expectedId);

        //Then
        assertThrows(IllegalArgumentException.class, ()->service.createEntity(expectedName, expectedDate));
    }

    @DisplayName("Fail on null name ")
    @Test
    public void testCase3() {
        //Given
        final LocalDate currentDate = LocalDate.now();
        final LocalDate expectedDate = currentDate.minusYears(35);

        when(timeService.currentDate()).thenReturn(currentDate);
        when(idGenerator.nextId()).thenReturn(14L);

        //Then
        assertThrows(IllegalArgumentException.class, () -> service.createEntity(null, expectedDate));
    }

    @DisplayName("Fail on null or empty Date of Birthday")
    @Test
    public void testCase4(){
        //Given
        final LocalDate expectedDate = null;
        final String expectedName = "Nice name";
        final long expectedId = 14L;

        when(timeService.currentDate()).thenReturn(expectedDate);
        when(idGenerator.nextId()).thenReturn(expectedId);

        //Then
        assertThrows(IllegalArgumentException.class, ()->service.createEntity(expectedName, expectedDate));
    }

    @DisplayName("Fail on too small age")
    @Test
    public void testCase5(){
        //Given
        final LocalDate currentDate = LocalDate.now();
        final String expectedName = "Nice name";
        final long expectedId = 14L;
        final LocalDate expectedDate = currentDate.minusYears(10);

        when(timeService.currentDate()).thenReturn(expectedDate);
        when(idGenerator.nextId()).thenReturn(expectedId);

        //Then
        assertThrows(IllegalArgumentException.class, ()->service.createEntity(expectedName, expectedDate));
    }
}