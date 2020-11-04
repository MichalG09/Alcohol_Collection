package pl.mgrzech.alcohols_collection.sortTypeTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.repositories.SortTypeRepository;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class SortTypeRepositoryTest {

    @Autowired
    private SortTypeRepository sortTypeRepository;

    private final SortType sortTypeToTest = new SortType(null, "test", "test1");

    @Test
    public void shouldCorrectSaveSortType(){
        sortTypeRepository.save(sortTypeToTest);
        assertNotNull(sortTypeToTest.getId());
    }

    @Test
    public void shouldReturnSortTypeByName(){
        sortTypeRepository.save(sortTypeToTest);
        Optional<SortType> sortType = sortTypeRepository.findByValue(sortTypeToTest.getValue());
        assertTrue(sortType.isPresent());
        assertEquals(sortTypeToTest.getValueTranslate(), sortType.get().getValueTranslate());
    }

    @Test
    public void shouldReturnNullSortTypeByName(){
        Optional<SortType> sortType = sortTypeRepository.findByValue(sortTypeToTest.getValue());
        assertFalse(sortType.isPresent());
    }
}
