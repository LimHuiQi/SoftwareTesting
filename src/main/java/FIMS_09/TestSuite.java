package FIMS_09;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        FIMS_09_TC1_Lim.class,
        FIMS_09_TC2_Pravin.class,
        FIMS_09_TC3_1_Khong.class,
        FIMS_09_TC3_2_Khong.class,
        FIMS_09_TC4_Go.class,
        //FIMS_09_TC5_Ridzwan.class,
})
public class TestSuite {

}

