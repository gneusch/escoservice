package hu.neusch.esco

import hu.neusch.esco.EscoClass.EscoClass
import hu.neusch.ontology.OntologyObjectHandler
import hu.neusch.utils.io.OntologyLoader
import org.semanticweb.owlapi.model.{OWLClass, OWLLiteral, OWLNamedIndividual, OWLOntology}

class EscoOntologyObjectHandler(escoOntologyFile: String = "/home/answeris42/esco/v1.0.1/esco_v1.0.1.ttl") extends OntologyObjectHandler {

  val escoOntology: OWLOntology = OntologyLoader.loadOntologyFromFilePath(escoOntologyFile)

  def getEscoClasses: List[OWLClass] = getOntologyClasses(escoOntology)

  def getIndividualsOfEscoClass(escoClass: EscoClass): List[OWLNamedIndividual] = {
    val owlClass: OWLClass = getEscoClasses.find(
      owlCls => getClassStringFromIRI(owlCls.getIRI).getOrElse(throw new IllegalArgumentException(s"No class string found for $owlCls")).toLowerCase == escoClass.toString.toLowerCase
    ).getOrElse(throw new NoSuchElementException(s"No class: ${escoClass.toString} was found in the ESCO ontology"))
    getNamedIndividualsFromClass(owlClass)(getOWLReasoner(escoOntology))
  }

  def getLabelForIndividual(oWLNamedIndividual: OWLNamedIndividual, lang: String)(oWLOntology: OWLOntology = escoOntology): String = {
    val antotationList = getAnnotationAssertionAxiomsForIndividual(oWLNamedIndividual)(oWLOntology).filter(
      axiom => axiom.getAnnotation.getProperty.getIRI.getIRIString == "http://www.w3.org/2004/02/skos/core#prefLabel"
    )
    //TODO refactor this to OntologyObjectHandler
    val labelLiteralInLang = antotationList.map( annotation => annotation.getAnnotation.getValue match {
        case label: OWLLiteral => Some(label)
        case _ => None
      }
    ).flatten.find( label => label.getLang == lang).getOrElse(throw new NoSuchElementException(s"No label found for $oWLNamedIndividual with language code $lang"))
    labelLiteralInLang.getLiteral
  }

}
