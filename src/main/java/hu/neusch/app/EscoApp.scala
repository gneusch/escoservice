package hu.neusch.app

import hu.neusch.esco.{EscoClass, EscoOntologyObjectHandler}
import hu.neusch.ontology.OntologyObjectHandler
import hu.neusch.utils.io.{FileHandler, OntologyLoader}
import org.semanticweb.owlapi.model.OWLOntology

object EscoApp extends App {


  //Example usage
  //val escoHandler = new EscoOntologyObjectHandler()

  //Get all esco classes
  //escoHandler.getEscoClasses.foreach( println(_) )

  //Get all esco individuals with label
  //escoHandler.getIndividualsOfEscoClass(EscoClass.Skill).foreach( indiv =>
  //  println ( escoHandler.getLabelForIndividual(indiv, "en")() )
  //)
  BasicEscoStats.instanceFrequenciesToFile("/home/answeris42/esco/", "basicClassStat")


}

object BasicEscoStats extends  FileHandler {

  val escoHandler = new EscoOntologyObjectHandler()

  def instanceFrequenciesToFile(path: String, file: String): Unit = {
    writeCharsToFile(
      path,
      file,
      s" | class | Number of individuals | ${System.lineSeparator()} "
    )
    EscoClass.values.foreach( escoClass =>
      writeCharsToFile(
        path,
        file,
        s" | ${escoClass.toString} | ${escoHandler.getIndividualsOfEscoClass(escoClass).length} | ${System.lineSeparator()} "
      )
    )
  }
}