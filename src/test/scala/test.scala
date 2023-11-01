import org.scalatest.funsuite.AnyFunSuite

class test extends AnyFunSuite {

  test("evaluate: Num(5) == 5") {
    assert(ArithmeticExpression.evaluate(ArithmeticExpression.Num(5)) === 5.0)
  }
  test("evaluate: Div(Mult(Num(2), Num(4)), Plus(Num(5), Minus(Num(6)))) == -8.0"){
    assert(ArithmeticExpression.evaluate(ArithmeticExpression.Div(ArithmeticExpression.Mult(ArithmeticExpression.Num(2), ArithmeticExpression.Num(4)),
      ArithmeticExpression.Plus(
      ArithmeticExpression.Num(5), ArithmeticExpression.Minus(ArithmeticExpression.Num(6))
    ))) === -8.0)
  }

  test("pretty: Num(5) == 5") {
    assert(ArithmeticExpression.pretty(ArithmeticExpression.Num(5)) === "5")
  }
  test("pretty: Div(Mult(Num(2), Num(4)), Plus(Num(5), Minus(Num(6)))) == ((2 * 4) / (5 + -6.0)) = -8.0"){
    assert(ArithmeticExpression.pretty(ArithmeticExpression.Div(ArithmeticExpression.Mult(ArithmeticExpression.Num(2), ArithmeticExpression.Num(4)),
      ArithmeticExpression.Plus(
        ArithmeticExpression.Num(5), ArithmeticExpression.Minus(ArithmeticExpression.Num(6))
      ))) === "((2 * 4) / (5 + -6.0)) = -8.0")
  }
}
