package hu.neusch.esco

import hu.neusch.esco.EscoClasses.EscoClass
import hu.neusch.ontology.OntologyObjectHandler
import hu.neusch.utils.io.OntologyLoader
import org.semanticweb.owlapi.model.{OWLClass, OWLNamedIndividual, OWLOntology}

class EscoOntologyObjectHandler(escoOntologyFile: String = "/home/answeris42/esco/v1.0.1/esco_v1.0.1.ttl") extends OntologyObjectHandler {

  val escoOntology: OWLOntology = OntologyLoader.loadOntologyFromFilePath(escoOntologyFile)

  def getEscoClasses: List[OWLClass] = getOntologyClasses(escoOntology)

  def getIndividualsOfEscoClass(escoClass: EscoClass): List[OWLNamedIndividual] = {
    val oWLClass: OWLClass = getEscoClasses.find(
      owlClass => getClassStringFromIRI(owlClass.getIRI).getOrElse(throw new IllegalArgumentException(s"No class string found for")).toLowerCase == escoClass.toString.toLowerCase
    ).getOrElse(throw new NoSuchElementException(s"No class: ${escoClass.toString} was found in the ESCO ontology"))
    getNamedIndividualsFromClass(oWLClass)(getOWLReasoner(escoOntology))
  }

}
