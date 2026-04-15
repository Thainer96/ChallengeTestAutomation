package runner;

import com.intuit.karate.junit5.Karate;

public class UserRunner {

    @Karate.Test
    Karate testUserCrud() {
        return Karate.run("classpath:features/user")
                .tags("@regression")
                .relativeTo(getClass());
    }
}
