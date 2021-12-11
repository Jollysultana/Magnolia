package parameters;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
    @DataProvider(name = "SingleValue")
    public Object[][] storeSingleValue() {
        return new Object[][]{
                {"Rifat"},
                {"Mohammad"},
                {"Ashraf"}
        };
    }

    @DataProvider(name = "MultipleValues")
    public Object[][] storeMultipleValue(){
        return new Object[][]{
                {"Rifat", "Florida",33018},
                {"Farid","New York",12456},
                {"Ashraf", "Texas",45789}
        };
    }

    @DataProvider(name = "RealAprRates")
    public Object[][] storeRealAprRatesData(){
        return new Object[][]{
                {"200000","15000","3","3.130%"}
        };

    }



    @Test(dataProvider = "SingleValue")
    public void readSingleValue(String name){
        System.out.println("[Single Column Value] Name is:" + name);
    }

    @Test(dataProvider =  "MultipleValues")
    public void readMultipleValues(String name,String state,int zipcode){
        System.out.println("[Multiple Column Value] Name is:" + name);
        System.out.println("[Multiple Column Value] State is:" + state);
        System.out.println("[Multiple Column Value] Zip Code is:" + zipcode);
    }
}
