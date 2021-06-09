package test

import com.azure.storage.blob._
import com.azure.storage.common._
import java.util.concurrent._
import scala.concurrent._

object Main extends App {

  def envVar(name: String): String = 
    sys.env.getOrElse(name, throw new NullPointerException(s"$name is not set"))

  val azureAccountName = envVar("AZURE_ACCOUNT_NAME")
  val azureAccountKey = envVar("AZURE_ACCOUNT_KEY")

  def createBlobServiceClient(e: ExecutorService): BlobServiceClient =
    e.submit(new Callable[BlobServiceClient] () {
      override def call(): BlobServiceClient = {
        println(s"Creating BlobServiceClient on ${Thread.currentThread.getName}")
        new BlobServiceClientBuilder()
          .endpoint(s"https://${azureAccountName}.blob.core.windows.net")
          .credential(new StorageSharedKeyCredential(azureAccountName, azureAccountKey))
          .buildClient()
      }
    }).get()

  //this only works with 'fork := true' in build.sbt
  //otherwise it throws: java.lang.IllegalArgumentException: Can not instantiate Stax reader for XML source type class org.codehaus.stax2.io.Stax2ByteArraySource (unrecognized type)
  val client = createBlobServiceClient(ExecutionContext.global.asInstanceOf[ExecutorService])

  //this always works
  // val client = createBlobServiceClient(ExecutionContext.fromExecutorService(null))

  //this always works
  // val client = new BlobServiceClientBuilder()
  //   .endpoint(s"https://${azureAccountName}.blob.core.windows.net")
  //   .credential(new StorageSharedKeyCredential(azureAccountName, azureAccountKey))
  //   .buildClient()

  println("Listing blob containers...")
  val i = client.listBlobContainers().iterator()
  while (i.hasNext()) {
    val c = i.next()
    println(c.getName)
  }

  println("Done")

}
