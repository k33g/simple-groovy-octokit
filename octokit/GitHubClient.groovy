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
    this.headers = [
      new Header(property:"Content-Type", value:"application/json"),
      new Header(property:"User-Agent", value:"GitHubGroovy/1.0.0"),
      new Header(property:"Accept", value:"application/vnd.github.v3.full+json"),
      new Header(property:"Authorization", value: this.credentials)
    ]
  }
  // === Helpers ===

  // TODO: test Response.code and add something more functional + try ...
  /*
    eg:
    if repository already exists -> responseCode 422, else 201
  */

  // args.path
  def getData(Map args) { // return http.Response
    return Http.request(
      method: "GET",
      uri: this.baseUri + args.path,
      headers: this.headers,
      data: null
    )
  }

  // args.path, args.data
  def postData(Map args) { // return http.Response
    return Http.request(
      method: "POST",
      uri: this.baseUri + args.path,
      headers: this.headers,
      data: JsonOutput.toJson(args.data)
    )
  }

  // args.path, args.data
  def putData(Map args) { // return http.Response
    return Http.request(
      method: "POST",
      uri: this.baseUri + args.path,
      headers: this.headers,
      data: JsonOutput.toJson(args.data)
    )
  }

  // === Users ===

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

  // === Repositories ===

  // {name, description}

  def createRepository(Map args) {
    def resp = this.postData(
      path:args.path,
      data:[
        name: args.name,
        description: args.description,
        private: args.private,
        has_issue: true,
        has_wiki: true,
        auto_init: true
      ]
    )
    if(resp.code==201) {
      return new JsonSlurper().parseText(resp.text)
    } else {
      println JsonOutput.toJson(resp)
      return null
    }
  }

  def createPublicRepository(Map args) {
    return this.createRepository(
      path:"/user/repos",
      name: args.name,
      description: args.description,
      private: false
    )
  }

  def createPrivateRepository(Map args) {
    return this.createRepository(
      path:"/user/repos",
      name: args.name,
      description: args.description,
      private: true
    )
  }

  def createPublicOrganizationRepository(Map args) {
    return this.createRepository(
      path:"/orgs/${args.organization}/repos",
      name: args.name,
      description: args.description,
      private: false
    )
  }

  def createPrivateOrganizationRepository(Map args) {
    return this.createRepository(
      path:"/orgs/${args.organization}/repos",
      name: args.name,
      description: args.description,
      private: true
    )
  }


}
