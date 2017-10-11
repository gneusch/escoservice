package hu.neusch.utils.io

import java.io.File

import org.semanticweb.owlapi.apibinding.OWLManager
import org.semanticweb.owlapi.model.{OWLOntology, OWLOntologyManager}

object OntologyLoader {

  def loadOntologyFromFilePath(filePath: String): OWLOntology = {
    val ontologyManager: OWLOntologyManager = OWLManager.createOWLOntologyManager()
    ontologyManager.loadOntologyFromOntologyDocument(new File(filePath))
  }

}
