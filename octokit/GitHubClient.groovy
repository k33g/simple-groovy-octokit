package octokit

import http.*
import groovy.json.*

class GitHubClient {
  def baseUri
  def credentials
  def headers

  GitHubClient(Map args) {
    this.baseUri = args.baseUri
    this.credentials = args.token != null && args.token.length() > 0 ? "token" + ' ' + args.token : null
    println "${this.credentials}"
    this.headers = [
      new Header(property:"Content-Type", value:"application/json"),
      new Header(property:"User-Agent", value:"GitHubGroovy/1.0.0"),
      new Header(property:"Accept", value:"application/vnd.github.v3.full+json"),
      new Header(property:"Authorization", value: this.credentials)
    ]
  }

  def getData(Map args) { // return http.Response
    return Http.request(
      method: "GET",
      uri: this.baseUri + args.path,
      headers: this.headers,
      data: null
    )
  }

  def fetchUser(Map args) { // args.handle
    return new JsonSlurper().parseText(
      this.getData(path:"/users/${args.handle}").text
    )
  }

  def searchUsers(Map args) { // args.handle
    return new JsonSlurper().parseText(
      this.getData(path:"/search/users?q=${args.handle}").text
    )
  }

}
