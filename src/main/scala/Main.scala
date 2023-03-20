//> using scala "3"
//> using lib "com.github.tototoshi::scala-csv:1.3.10"
//> using lib "com.github.losizm::t2:2.0.0"

import com.github.tototoshi.csv._
import scala.io.Source

@main def main: Unit =
  def desugarReader(el: Seq[String]): (String, String, String) = {
    println(el)
    println("Hola")
    val id = el(0)
    val severity = el(9)
    val desc = el(10)

    (id, severity, desc)
  }

  def evidencePrinter(id: String, sev: String, desc: String): Unit = {
    val table = t2
      .TableBuilder()
      .add("ID", "Severity", "Description")
      .add(id, sev, desc)
      .build()

    val writer = t2.TableWriter(
      "ansiColorEnabled" -> "true",
      "tableBorderColor" -> "cyan",
      "tableHeaderColor" -> "black,greenBackground",
      "bodyRuleColor" -> "green",
      "rowHeaderEnabled" -> "true",
      "rowHeaderColor" -> "bold,cyan",
      "columnRightAlign" -> "0,5" // Right align first and last columns
    )

    writer.write(System.out, table)

  }
  val reader = CSVReader
    .open(Source.fromFile("src/main/resources/data.csv"))
    .iterator
    .drop(1)

  val foo = reader.take(3).foreach(desugarReader)
  // evidencePrinter(els._1, els._2, els._3)
