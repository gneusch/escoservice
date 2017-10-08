package hu.neusch.io

import java.io.File

import org.semanticweb.owlapi.apibinding.OWLManager
import org.semanticweb.owlapi.model.{OWLOntology, OWLOntologyManager}
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory

object OntologyLoader {

  def loadOntologyFromFilePath(filePath: String): OWLOntology = {
    val ontologyManager: OWLOntologyManager = OWLManager.createOWLOntologyManager()
    ontologyManager.loadOntologyFromOntologyDocument(new File(filePath))
  }

}
