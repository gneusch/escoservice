package hu.neusch.ontology

import java.util.stream.Collectors

import org.semanticweb.owlapi.model._
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory

import scala.collection.JavaConverters._

trait OntologyObjectHandler {

  //Reasoner
  def getOWLReasoner(ontology: OWLOntology): OWLReasoner = {
    val reasonerFactory = new StructuralReasonerFactory();
    reasonerFactory.createReasoner(ontology);
  }

  //Class
  def getOntologyClasses(owlOntology: OWLOntology): List[OWLClass] = {
    val ontologyClassJavaStream: java.util.stream.Stream[OWLClass] = owlOntology.classesInSignature()
    val ontologyClassJavaList: java.util.List[OWLClass] = ontologyClassJavaStream.collect(Collectors.toList())
    ontologyClassJavaList.asScala.toList
  }

  def getIRIListOfClasses(owlClassList: List[OWLClass]): List[IRI] = owlClassList.map(_.getIRI)

  def getIRIOfClass(owlClass: OWLClass): IRI = owlClass.getIRI

  def getClassStringFromIRI( classIRI: IRI): Option[String] = {
    if(classIRI.getRemainder.isPresent) Some(classIRI.getRemainder.get())
    else None
  }

  //Individual
  def getNamedIndividualsFromClass(oWLClass: OWLClass)(oWLReasoner: OWLReasoner): List[OWLNamedIndividual] = {
    val ontologyIndividualsJavaStream: java.util.stream.Stream[OWLNamedIndividual] = oWLReasoner.getInstances(oWLClass).entities()
    val ontologyIndividualsJavaList: java.util.List[OWLNamedIndividual] = ontologyIndividualsJavaStream.collect(Collectors.toList())
    ontologyIndividualsJavaList.asScala.toList
  }

}
