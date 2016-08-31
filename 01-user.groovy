import http.*
import octokit.*
import groovy.json.*

def gitHubCli = new GitHubClient(
    baseUri:"http://github.at.home/api/v3",
    token:System.getenv("TOKEN_GHE_27_K33G")
  )

def searchUsers = gitHubCli.searchUsers(handle:"k33")

println "${searchUsers.total_count} user(s) found"

searchUsers.items.each {
  println "${it.login} ${it.html_url}"
}

def k33g = gitHubCli.fetchUser(handle:"k33g")

println "k33g: ${k33g.login} ${k33g.avatar_url}"
