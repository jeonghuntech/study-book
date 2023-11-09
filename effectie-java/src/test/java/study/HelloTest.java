package study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HelloTest {

    @Test
    public void test(){
        assertThat("1").isEqualTo("1");
    }
}
