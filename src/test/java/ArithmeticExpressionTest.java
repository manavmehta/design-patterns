import ExpressionEvaluator.entities.Expression;
import ExpressionEvaluator.entities.Number;
import ExpressionEvaluator.entities.Operation;
import ExpressionEvaluator.exceptions.DivisionByZeroException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArithmeticExpressionTest {
    @Test
    void HappyFlowTest () throws Exception {
        var level1 = new Expression(new Number(5), new Number(3), Operation.ADD);
        var level2 =    new Expression(new Number(7), level1, Operation.MULTIPLY);
        var level3 =    new Expression(level2, new Number(4), Operation.DIVIDE);
        var level4 =    new Expression(level3, new Number(1), Operation.SUBTRACT);
        assertEquals(((7*(5+3))/4)-1, level4.evaluate());
    }

    @Test
    void DivByZero () throws Exception {
        var level1 = new Expression(new Number(5), new Number(3), Operation.ADD);
        var level2 =    new Expression(new Number(7), level1, Operation.MULTIPLY);
        var level3 =    new Expression(level2, new Number(0), Operation.DIVIDE);
        var level4 =    new Expression(level3, new Number(1), Operation.SUBTRACT);
        assertThrows(DivisionByZeroException.class, level4::evaluate);
    }
}
