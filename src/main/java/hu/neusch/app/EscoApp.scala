package hu.neusch.app

import hu.neusch.esco.EscoOntologyObjectHandler
import hu.neusch.io.OntologyLoader
import hu.neusch.ontology.OntologyObjectHandler
import org.semanticweb.owlapi.model.OWLOntology

object EscoApp extends App with OntologyObjectHandler {

  val escoHandler = new EscoOntologyObjectHandler()

  escoHandler.getEscoClasses.foreach( println(_) )

}
