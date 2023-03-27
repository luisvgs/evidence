//> using scala "3"
//> using lib "com.github.tototoshi::scala-csv:1.3.10"
//> using lib "com.github.losizm::t2:2.0.0"
//> using lib "com.lihaoyi::fansi:0.4.0"

import com.github.tototoshi.csv._
import scala.io.Source
import scala.collection.mutable.ListBuffer

@main def main: Unit =
  var evidences: ListBuffer[Evidence] = new ListBuffer[Evidence]()
  def evidencePrinter(evs: ListBuffer[Evidence]): Unit = {
    val color_printer = (s: String) => s.toUpperCase() match {
      case "BAJA" => Console.GREEN + s.toUpperCase()
      case "ALTA" => Console.RED + s.toUpperCase()
      case "MEDIA" => Console.YELLOW + s.toUpperCase()
    }

    var table: t2.TableBuilder = t2
      .TableBuilder()
      .add("ID", "Platform", "Severity", "Description")
    evs.foreach(e => table.add(e._1, e._2, color_printer(e._3), e._4))

    var writer = t2.TableWriter(
      "ansiColorEnabled" -> "true",
      "tableBorderColor" -> "cyan",
      "tableHeaderColor" -> "black,greenBackground",
      "bodyRuleColor" -> "green",
      "rowHeaderEnabled" -> "true",
      "rowHeaderColor" -> "bold,cyan",
      "cellPadSize" -> "1",
      "cellSpaceSize" -> "1",
      "maxValueSize" -> "100"
    )

    writer.write(System.out, table.build())
  }

  def desugarToEvidence(el: Seq[String]): Evidence = {
    val id = el(0)
    val platform = el(3)
    val severity = el(9)
    val desc = el(10)

    val ev = Evidence(id, platform, severity, desc)

    evidences += ev
    ev
  }

  // Drop the header for now
  val reader = CSVReader
    .open(Source.fromFile("src/main/resources/data.csv"))
    .iterator
    .drop(1)

  val foo = reader.filter(x => x.contains("WEB")).foreach(desugarToEvidence)
  evidencePrinter(evidences)
