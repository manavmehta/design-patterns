package ExpressionEvaluator.entities;

public class Expression implements ArithmeticExpression{
    ArithmeticExpression left;
    ArithmeticExpression right;
    Operation operation;

    @Override
    public int evaluate() {
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
                    throw new IllegalArgumentException("Division by zero");
                }
            default:
                return 0;
        }
    }
}
