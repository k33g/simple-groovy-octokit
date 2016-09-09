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
      method: "PUT",
      uri: this.baseUri + args.path,
      headers: this.headers,
      data: JsonOutput.toJson(args.data)
    )
  }

  // args.path, args.data
  def patchData(Map args) { // return http.Response
    return Http.request(
      method: "PATCH",
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
      path: args.path,
      data: [
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
      path: "/user/repos",
      name: args.name,
      description: args.description,
      private: false
    )
  }

  def createPrivateRepository(Map args) {
    return this.createRepository(
      path: "/user/repos",
      name: args.name,
      description: args.description,
      private: true
    )
  }

  def createPublicOrganizationRepository(Map args) {
    return this.createRepository(
      path: "/orgs/${args.organization}/repos",
      name: args.name,
      description: args.description,
      private: false
    )
  }

  def createPrivateOrganizationRepository(Map args) {
    return this.createRepository(
      path: "/orgs/${args.organization}/repos",
      name: args.name,
      description: args.description,
      private: true
    )
  }

  // === Organizations ===

  //{login, admin, profile_name}
  def createOrganization(Map args) {
    def resp = this.postData(
      path: "/admin/organizations",
      data: [
        login: args.login,
        admin: args.admin,
        profile_name: args.profile_name
      ]
    )
    if(resp.code==201) {
      return new JsonSlurper().parseText(resp.text)
    } else {
      println JsonOutput.toJson(resp)
      return null
    }
  }

  // === Teams ===

  // {org, name, description, repo_names, privacy, permission}
  def createTeam(Map args) {
    def resp = this.postData(
      path: "/orgs/${args.org}/teams",
      data: [
        name: args.name,
        description: args.description,
        repo_names: args.repo_names,
        privacy: args.privacy, // secret or closed
        permission: args.permission // pull, push, admin
      ]
    )
    if(resp.code==201) {
      return new JsonSlurper().parseText(resp.text)
    } else {
      println JsonOutput.toJson(resp)
      return null
    }
  }

  // {org}
  def fetchTeams(Map args) {
    return new JsonSlurper().parseText(
      this.getData(path:"/orgs/${args.org}/teams").text
    )
  }

  // {org, name}
  def getTeamByName(Map args) {
    return this.fetchTeams(org: args.org).find {
      return it.name = args.name
    }
  }

  // {teamId, userName, role}
  def addTeamMembership(Map args) {
    def resp = this.putData(
      path: "/teams/${args.teamId}/memberships/${args.userName}",
      data: [
        role: args.role // member, maintener
      ]
    )
    if(resp.code==200) {
      return new JsonSlurper().parseText(resp.text)
    } else {
      println JsonOutput.toJson(resp)
      return null
    }
  }

  // === Impersonation ===

  def getAuthorizationsForImpersonation(Map args) {
    def resp = this.postData(
      path: "/admin/users/${args.handle}/authorizations",
      data: [
        scopes: ["user"]
      ]
    )
    if(resp.code==201 || resp.code==200) {
      return new JsonSlurper().parseText(resp.text)
    } else {
      println JsonOutput.toJson(resp)
      return null
    }
  }

  // {data}
  def changeUserData(Map args) {
    def resp = this.patchData(
      path: "/user",
      data: args.data
    )
    return new JsonSlurper().parseText(resp.text)
  }


  // === Labels ===

  // {name, color, owner, repository}
  def createLabel(Map args) {
    def resp = this.postData(
      path: "/repos/${args.owner}/${args.repository}/labels",
      data: [
        name: args.name,
        color: args.color
      ]
    )
    if(resp.code==201) {
      return new JsonSlurper().parseText(resp.text)
    } else {
      println JsonOutput.toJson(resp)
      return null
    }
  }


  // === Milestones ===

  // {title, state, description, due_on, owner, repository}
  def createMilestone(Map args) {
    def resp = this.postData(
      path: "/repos/${args.owner}/${args.repository}/milestones",
      data: [
        title: args.title,
        state: args.state,
        description: args.description,
        due_on: args.due_on
      ]
    )
    if(resp.code==201) {
      return new JsonSlurper().parseText(resp.text)
    } else {
      println JsonOutput.toJson(resp)
      return null
    }
  }



}
