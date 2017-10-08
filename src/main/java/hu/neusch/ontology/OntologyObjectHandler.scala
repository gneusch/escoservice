package hu.neusch.ontology

import java.util.stream.Collectors

import org.semanticweb.owlapi.model.{IRI, OWLClass, OWLOntology}
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory

import scala.collection.JavaConverters._

trait OntologyObjectHandler {

  def getOntologyClasses(owlOntology: OWLOntology): List[OWLClass] = {
    val o: java.util.stream.Stream[OWLClass] = owlOntology.classesInSignature()
    val ontologyClassList: java.util.List[OWLClass] = o.collect(Collectors.toList())
    ontologyClassList.asScala.toList
  }

  def getIRIListOfClasses(owlClassList: List[OWLClass]): List[IRI] = owlClassList.map(_.getIRI)

  def getIRIOfClass(owlClass: OWLClass): IRI = owlClass.getIRI

  def getClassStringFromIRI( classIRI: IRI): Option[String] = {
    if(classIRI.getRemainder.isPresent) Some(classIRI.getRemainder.get())
    else None
  }

  def getOWLReasoner(ontology: OWLOntology): OWLReasoner = {
    val reasonerFactory = new StructuralReasonerFactory();
    reasonerFactory.createReasoner(ontology);
  }

}
