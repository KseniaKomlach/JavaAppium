package suites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ChangeAppConditionTests.class,
        ArticleTests.class,
        MyListsTests.class,
        SearchTests.class
})

public class TestSuiteiOS{
}
