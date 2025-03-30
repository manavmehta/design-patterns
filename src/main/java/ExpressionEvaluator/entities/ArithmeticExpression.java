package ExpressionEvaluator.entities;

import ExpressionEvaluator.exceptions.DivisionByZeroException;

public interface ArithmeticExpression {
    int evaluate() throws DivisionByZeroException;
}
