package ExpressionEvaluator.entities;

import ExpressionEvaluator.exceptions.DivisionByZeroException;

public class Expression implements ArithmeticExpression{
    ArithmeticExpression left;
    ArithmeticExpression right;
    Operation operation;

    public Expression(ArithmeticExpression left, ArithmeticExpression right, Operation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public int evaluate() throws DivisionByZeroException {
        switch (operation) {
            case ADD:
                return left.evaluate() + right.evaluate();
            case SUBTRACT:
                return left.evaluate() - right.evaluate();
            case MULTIPLY:
                return left.evaluate() * right.evaluate();
            case DIVIDE:
                if (right.evaluate() != 0){
                    return left.evaluate() / right.evaluate();
                } else {
                    throw new DivisionByZeroException();
                }
            default:
                return 0;
        }
    }
}
