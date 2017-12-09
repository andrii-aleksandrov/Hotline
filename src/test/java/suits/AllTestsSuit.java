package suits;

import basketTests.BasketAdd;
import loginTests.InvalidLogin;
import loginTests.ValidLogin;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        ValidLogin.class,
        InvalidLogin.class,
        BasketAdd.class

})

public class AllTestsSuit {
}
