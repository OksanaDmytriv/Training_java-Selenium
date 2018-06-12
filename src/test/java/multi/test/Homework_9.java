package multi.test;

import org.junit.Test;

public class Homework_9 extends TestBase {

    @Test
    public void ninthTest() {
        app.addProductToCard(3);
        app.removeProductFromCart(3);
        app.checkCartEmpty();
    }
}

