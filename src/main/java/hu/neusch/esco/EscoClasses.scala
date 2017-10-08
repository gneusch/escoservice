package hu.neusch.esco

object EscoClasses extends Enumeration{

  type EscoClass = Value
  val Structure, NodeLiteral, AssociationObject, Label, Concept, Regulation, MemberConcept, LabelRole, ConceptScheme, Occupation, Skill = Value

  def isPillar(escoClass: EscoClass): Boolean = escoClass == Concept || escoClass == Occupation || escoClass == Skill

}
