package hu.neusch.esco

import hu.neusch.esco.EscoClasses.EscoClass
import hu.neusch.io.OntologyLoader
import hu.neusch.ontology.OntologyObjectHandler
import org.semanticweb.owlapi.model.{OWLClass, OWLOntology}

class EscoOntologyObjectHandler(escoOntologyFile: String = "/home/answeris42/esco/v1.0.1/esco_v1.0.1.ttl") extends OntologyObjectHandler {

  val escoOntology: OWLOntology = OntologyLoader.loadOntologyFromFilePath(escoOntologyFile)

  def getEscoClasses: List[OWLClass] = getOntologyClasses(escoOntology)

  def getIndividualsOfEscoClass(escoClass: EscoClass) = ???

}
