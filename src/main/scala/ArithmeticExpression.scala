enum ArithmeticExpression:
  case Num(x: Int)
  case Minus(x: ArithmeticExpression)
  case Plus(x: ArithmeticExpression, y: ArithmeticExpression)
  case Mult(x: ArithmeticExpression, y: ArithmeticExpression)
  case Div(x: ArithmeticExpression, y: ArithmeticExpression)

object ArithmeticExpression:
  def evaluate(expression: ArithmeticExpression): Double =
    expression match
      case Num(x: Int) => x.toDouble
      case Minus(x: ArithmeticExpression) => -evaluate(x)
      case Plus(x: ArithmeticExpression, y: ArithmeticExpression) => evaluate(x) + evaluate(y)
      case Mult(x: ArithmeticExpression, y: ArithmeticExpression) => evaluate(x) * evaluate(y)
      case Div(x: ArithmeticExpression, y: ArithmeticExpression) => evaluate(x) / evaluate(y)

  def pretty(expression: ArithmeticExpression): String =
    expression match
      case Num(x: Int) =>
        s"$x"
      case Minus(x: ArithmeticExpression) =>
        val res = evaluate(x)
        s"-$res"
      case Plus(x, y) =>
        val a = pretty(x)
        val b = pretty(y)
        val res = evaluate(x) + evaluate(y)
        //s"$a + $b = $res"
        s"($a + $b)"
      case Mult(x, y) =>
        val a = pretty(x)
        val b = pretty(y)
        //val res = a * b
        val res = evaluate(x) * evaluate(y)
        //s"$a * $b = $res"
        s"($a * $b)"
      case Div(x, y) =>
        val a = pretty(x)
        val b = pretty(y)
        //val res = a / b
        val res = evaluate(x) / evaluate(y)
        //s"$a / $b = $res"
        s"($a / $b) = $res"

  @main def hello(): Unit ={

    println(pretty(Div(
      (Minus(Plus(Num(2), Num(2)))),
      Plus(
      Mult(Num(3), Num(3))
      , Num(9)))))

    println(pretty(Plus(Div(Num(4), Num(3)), Num(4))))
    println(pretty(Minus(Num(5))))
    println(pretty(Div(Mult(Num(2), Num(4)), Plus(Num(5), Minus(Num(6))))))
  }