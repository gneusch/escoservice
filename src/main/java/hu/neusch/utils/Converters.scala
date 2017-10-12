package hu.neusch.utils

import java.util.{List => JList}
import java.util.stream.{Collectors, Stream => JStream}
import scala.collection.JavaConverters._

object Converters {

  /**
    * Converts java.util.stream.Stream of T into scala.List of T
    * @param stream Java Stream of T
    * @tparam T type T
    * @return Scala List of T
    */
  def convertJavaStreamToScalaList[T](stream: JStream[T]): List[T] ={
    var javaList: JList[T] = stream.collect(Collectors.toList[T]())
    javaList.asScala.toList
  }

}
