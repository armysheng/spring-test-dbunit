package com.github.springtestdbunit.dataset;

import com.github.springtestdbunit.testutils.ExtendedTestContextManager;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.TestContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CsvDataSetLoaderTest {
    private TestContext testContext;

    private CsvDataSetLoader loader;
    @Before
    public void setup() throws Exception {
        this.loader = new CsvDataSetLoader();
        ExtendedTestContextManager manager = new ExtendedTestContextManager(getClass());
        this.testContext = manager.accessTestContext();
    }

    @Test
    public void shouldLoadFromRelativeFile() throws Exception {
        IDataSet dataset = this.loader.loadDataSet(this.testContext.getTestClass(), "./");
        assertEquals("TEST", dataset.getTableNames()[0]);
        assertEquals("name2", dataset.getTable("TEST").getValue(1, "name"));

    }

    @Test
    public void shouldReturnNullOnMissingFile() throws Exception {
        IDataSet dataset = this.loader.loadDataSet(this.testContext.getTestClass(), "doesnotexist.txt");
        assertNull(dataset);
    }
}
