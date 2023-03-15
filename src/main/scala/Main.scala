//> using scala "3"
//> using lib "com.github.tototoshi::scala-csv:1.3.10"
import com.github.tototoshi.csv._
import scala.io.Source

@main def hello: Unit =
  val reader = CSVReader.open(Source.fromFile("src/main/resources/data.csv"))
  println( reader.readNext() )

def msg = "I was compiled by Scala 3. :)"
