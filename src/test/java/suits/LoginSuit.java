package suits;

import loginTests.InvalidLogin;
import loginTests.ValidLogin;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {

        ValidLogin.class,
        InvalidLogin.class

})
public class LoginSuit {

}
