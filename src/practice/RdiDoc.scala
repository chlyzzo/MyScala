package practice

class RdiDoc (val doc: Map[String, Any]){
  def getInt(key: String) = try {
    doc(key).asInstanceOf[Number].intValue
  } catch {
    case _: Exception => doc(key).toString.toInt
  }

  def optInt(key: String, default: Int = 0) = try {
    getInt(key)
  } catch {
    case _: Exception => default
  }

  def getDouble(key: String) = doc(key).asInstanceOf[Number].doubleValue

  def optDouble(key: String, default: Double = 0.0) = try {
    getDouble(key)
  } catch {
    case _: Exception => default
  }
}