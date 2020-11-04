package pl.mgrzech.alcohols_collection.sortTypeTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.mgrzech.alcohols_collection.entities.SortType;
import pl.mgrzech.alcohols_collection.repositories.SortTypeRepository;
import pl.mgrzech.alcohols_collection.sortType.FindSortType;

import java.util.*;
import java.util.stream.Collectors;

public class FindSortTypeTest {

    @InjectMocks
    private FindSortType findSortType;

    @Mock
    private SortTypeRepository sortTypeRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnSortedListSortTypes(){
        Optional<SortType> sortTypeToTest = Optional.of(new SortType(1, "test", "test1"));
        List<SortType> list = new ArrayList<>();
        list.add(new SortType(1, "ctest", "test11"));
        list.add(new SortType(2, "btest2", "test22"));
        list.add(new SortType(3, "atest3", "test33"));
        List<SortType> sortedList = list.stream().sorted(Comparator.comparing(SortType::getValue)).collect(Collectors.toList());

        Mockito.when(sortTypeRepository.findAll()).thenReturn(list);

        Assert.assertEquals(sortedList, findSortType.allSortedByValueTypesSort());
    }
}
