package hu.neusch.ontology

import hu.neusch.utils.Converters
import org.semanticweb.owlapi.model._
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory

trait OntologyObjectHandler {

  //Reasoner
  def getOWLReasoner(ontology: OWLOntology): OWLReasoner = {
    val reasonerFactory = new StructuralReasonerFactory()
    reasonerFactory.createReasoner(ontology)
  }

  //Class
  def getOntologyClasses(owlOntology: OWLOntology): List[OWLClass] = {
    Converters.convertJavaStreamToScalaList(owlOntology.classesInSignature())
  }

  def getIRIListOfClasses(owlClassList: List[OWLClass]): List[IRI] = owlClassList.map(_.getIRI)

  def getIRIOfClass(owlClass: OWLClass): IRI = owlClass.getIRI

  def getClassStringFromIRI( classIRI: IRI): Option[String] = {
    if(classIRI.getRemainder.isPresent) Some(classIRI.getRemainder.get())
    else None
  }

  //Individual
  def getNamedIndividualsFromClass(oWLClass: OWLClass)(oWLReasoner: OWLReasoner): List[OWLNamedIndividual] = {
    Converters.convertJavaStreamToScalaList(oWLReasoner.getInstances(oWLClass).entities())
  }

  def getAnnotationAssertionAxiomsForIndividual(oWLNamedIndividual: OWLNamedIndividual)(oWLOntology: OWLOntology): List[OWLAnnotationAssertionAxiom] = {
    Converters.convertJavaStreamToScalaList(oWLOntology.annotationAssertionAxioms(oWLNamedIndividual.getIRI))
  }

}
