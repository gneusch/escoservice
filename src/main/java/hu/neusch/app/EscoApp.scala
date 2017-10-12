package hu.neusch.app

import hu.neusch.esco.{EscoClass, EscoOntologyObjectHandler}
import hu.neusch.ontology.OntologyObjectHandler
import hu.neusch.utils.io.OntologyLoader
import org.semanticweb.owlapi.model.OWLOntology

object EscoApp extends App with OntologyObjectHandler {

  val escoHandler = new EscoOntologyObjectHandler()

//  escoHandler.getEscoClasses.foreach( println(_) )
  escoHandler.getIndividualsOfEscoClass(EscoClass.Skill).foreach( indiv =>
    println ( escoHandler.getLabelForIndividual(indiv, "en")() )
  )

}
