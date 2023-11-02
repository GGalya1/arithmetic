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
      case Div(x: ArithmeticExpression, y: ArithmeticExpression) =>  evaluate(x)./(evaluate(y))
      case Pow(x: ArithmeticExpression, y: ArithmeticExpression) =>
        if(evaluate(y) > 0) evaluate(Pow(Num(evaluate(x).toInt), Num(evaluate(y).toInt - 1))) * evaluate(x)
        else if(evaluate(y) == 0 && evaluate(x) != 0) 1.toDouble
        else evaluate(Pow(Num(evaluate(x).toInt), Num(evaluate(y).toInt+1))) * (1/ evaluate(x))
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
        s"($a + $b)"
      case Mult(x, y) =>
        val a = pretty(x)
        val b = pretty(y)
        s"($a * $b)"
      case Div(x, y) =>
        val a = pretty(x)
        val b = pretty(y)
        s"($a / $b)"
      case Pow(x, y) =>
        s"((${pretty(x)}) ^ (${pretty(y)}))"

  def evaluate(expressions: List[ArithmeticExpression]): List[Double] =
    expressions.map(x => evaluate(x))

  def showResults(expressions: List[ArithmeticExpression]): String =
    var res = s""
    val newline = sys.props("line.separator")
    for(elem <- expressions){
      res = res.concat(pretty(elem))
      res = res.concat(s" = ${evaluate(elem)}")
      res += newline
    }
    res