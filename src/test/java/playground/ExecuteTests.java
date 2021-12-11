package playground;

import org.testng.annotations.Test;
import parameters.DataProviderClass;

public class ExecuteTests {
    @Test(dataProvider = "MultipleValues",dataProviderClass = DataProviderClass.class)
    private void run(String name,String state,int zipcode){
        System.out.println("[Multiple Column Value] Name is:" + name);
        System.out.println("[Multiple Column Value] State is:" + state);
        System.out.println("[Multiple Column Value] Zip Code is:" + zipcode);
    }
}
