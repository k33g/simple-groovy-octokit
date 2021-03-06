package http

class Http {

  static isOk (code) {
    return  java.net.HttpURLConnection.HTTP_OK == code ||
            java.net.HttpURLConnection.HTTP_CREATED == code ||
            java.net.HttpURLConnection.HTTP_ACCEPTED == code
  }

  static request (method, uri, headers, data) {
    def obj = new java.net.URL(uri)
    def connection = obj.openConnection()

    if(method == "PATCH") { // see http://stackoverflow.com/questions/25163131/httpurlconnection-invalid-http-method-patch
      connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
      connection.setRequestMethod("POST");
    } else {
      connection.setRequestMethod(method)
    }


    headers.each {
      connection.setRequestProperty(it.property, it.value)
    }



    if(data != null && (method == "POST" || method == "PUT" || method == "PATCH")) {
      connection.setDoOutput(true)
      def dataOutputStream = new DataOutputStream(connection.getOutputStream())
      dataOutputStream.writeBytes(data)
      dataOutputStream.flush()
      dataOutputStream.close()
    }

    def responseCode = connection.getResponseCode()
    def responseMessage = connection.getResponseMessage()

    println "LOG> Http.request > responseCode: " + responseCode
    println "LOG> Http.request > responseMessage: " + responseMessage

    if (isOk(responseCode)) {
      def responseText = new Scanner(connection.getInputStream(),"UTF-8")
        .useDelimiter("\\A")
        .next() // String responseText
      println "LOG> Http.request > responseText: " + responseText
      return new Response(
        code: responseCode,
        message: responseMessage,
        text: responseText
      )
    } else {
      return new Response(
        code: responseCode,
        message: responseMessage,
        text: null
      )
    }
  }

  static request (Map args) {
    return request(args.method, args.uri, args.headers, args.data)
  }

}
