enum ArithmeticExpression:
  case Num(x: Int)
  case Minus(x: ArithmeticExpression)
  case Plus(x: ArithmeticExpression, y: ArithmeticExpression)
  case Mult(x: ArithmeticExpression, y: ArithmeticExpression)
  case Div(x: ArithmeticExpression, y: ArithmeticExpression)
  case Pow(x: ArithmeticExpression, y: ArithmeticExpression)

object ArithmeticExpression:
  def evaluate(expression: ArithmeticExpression): Double =
    expression match
      case Num(x: Int) => x.toDouble
      case Minus(x: ArithmeticExpression) => -evaluate(x)
      case Plus(x: ArithmeticExpression, y: ArithmeticExpression) => evaluate(x) + evaluate(y)
      case Mult(x: ArithmeticExpression, y: ArithmeticExpression) => evaluate(x) * evaluate(y)
      case Div(x: ArithmeticExpression, y: ArithmeticExpression) => evaluate(x) / evaluate(y)
        //TODO: das ist aktuellen Branch
      case Pow(x: ArithmeticExpression, y: ArithmeticExpression) =>
        if(evaluate(y) > 0) evaluate(Pow(Num(evaluate(x).toInt), Num(evaluate(y).toInt - 1))) * evaluate(x)
        else if(evaluate(y) == 0 && evaluate(x) != 0) 1.toDouble
        else evaluate(Pow(Num(evaluate(x).toInt), Num(evaluate(y).toInt+1))) * (1/ evaluate(x))

      /*case Pow(x: ArithmeticExpression, y: ArithmeticExpression) =>
        if(evaluate(y) == 1.0) 1.0
        else if(evaluate(y) == 2.0) 2.0
        else 3.0*/

      /*
      //Alternativ
      case Pow(_, y: ArithmeticExpression) if evaluate(y) > 0 => evaluate(x) * evaluate(Pow(evaluate(x), evaluate(y)-1))
      case Pow(_, y: ArithmeticExpression) if evaluate(y) == 0 => 1.0
      case Pow(_, y: ArithmeticExpression) if evaluate(y) < 0 => 1.0 / evaluate(Pow(evaluate(x), -evaluate(y)))*/



      /*//Bei meiner Bekanntin funktioniert diese Losung schon
      case Pow(base: ArithmeticExpression, exponent: ArithmeticExpression) => {
        if ((evaluate(exponent) == 0) && (evaluate(base) != 0)) 1.0
        else if (evaluate(exponent) > 0) evaluate(base) * evaluate(Pow(Num(evaluate(base).toInt), Num(evaluate(exponent).toInt - 1)))
        else (1 / evaluate(base)) * evaluate(Pow(Num(evaluate(base).toInt), Num(evaluate(exponent).toInt + 1)))
      }*/


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
        s"($a / $b)"
      case Pow(x, y) =>
        s"((${pretty(x)}) ^ (${pretty(y)}))"


  @main def hello(): Unit ={
    //println("Hello World!")
    println(evaluate(Pow(Plus(Num(1), Num(2)), Div(Mult(Num(2), Num(2)), Num(2)))))
    println(pretty(Pow(Plus(Num(1), Num(2)), Div(Mult(Num(2), Num(2)), Num(2)))))
  }