object ArithmeticExpression {

  enum ArithmeticExpression:
    case Num(x: Int)
    case Minus(x: ArithmeticExpression)
    case Plus(x: ArithmeticExpression, y: ArithmeticExpression)
    case Mult(x: ArithmeticExpression, y: ArithmeticExpression)
    case Div(x: ArithmeticExpression, y: ArithmeticExpression)


}
