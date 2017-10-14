package hu.neusch.utils.io

import java.io.{BufferedWriter, File, FileWriter}

trait FileHandler {

  def writeCharsToFile(path: String, fileName: String, text: String, append: Boolean = true): Unit = {
    val file = new File(path + fileName)
    val bufferedWriter = new BufferedWriter(new FileWriter(file, append))
    bufferedWriter.write(text)
    bufferedWriter.close()
  }


}
