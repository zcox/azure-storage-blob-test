package test

import com.azure.storage.blob._
import com.azure.storage.common._

object Main extends App {

  def envVar(name: String): String = 
    sys.env.getOrElse(name, throw new NullPointerException(s"$name is not set"))

  val azureAccountName = envVar("AZURE_ACCOUNT_NAME")
  val azureAccountKey = envVar("AZURE_ACCOUNT_KEY")

  val client = new BlobServiceClientBuilder()
    .endpoint(s"https://${azureAccountName}.blob.core.windows.net")
    .credential(new StorageSharedKeyCredential(azureAccountName, azureAccountKey))
    .buildClient()

  println("Listing blob containers...")
  val i = client.listBlobContainers().iterator()
  while (i.hasNext()) {
    val c = i.next()
    println(c.getName)
  }

  println("Done")

}
