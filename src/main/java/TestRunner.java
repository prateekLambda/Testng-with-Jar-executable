
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestRunner {


    public static void main(String[] args) throws Exception {

        TestNG runner= new TestNG();

            List<String> suites = new ArrayList<>();
            suites.add("path of testng xml suite file");
        runner.setTestSuites(suites);
        runner.run();
    }
}
