package com.github.springtestdbunit.sample.service.csv;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.CsvDataSetLoader;
import com.github.springtestdbunit.sample.entity.Person;
import com.github.springtestdbunit.sample.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-context.xml"})
@DbUnitConfiguration(dataSetLoader= CsvDataSetLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class PersonServiceCsvTest {
    @Autowired
    private PersonService personService;

    @Test
    @DatabaseSetup("../")
    public void testFind() throws Exception {
        List<Person> personList = this.personService.find("hil");
        assertEquals(1, personList.size());
        assertEquals("Phillip", personList.get(0).getFirstName());
    }
}
