import http.*
import groovy.json.*

def searchUsers = new JsonSlurper().parseText(
  Http.request("GET", "https://api.github.com/search/users?q=k33g", //   Http.request("GET", "http://github.at.home/api/v3/search/users?q=k33g",
    [
      new Header(property:"Content-Type", value:"application/json"),
      new Header(property:"User-Agent", value:"GitHubGolo/1.0.0"),
      new Header(property:"Accept", value:"application/vnd.github.v3.full+json")
    ]
    , null
  ).text)

println "${searchUsers.total_count} user(s) found"

searchUsers.items.each {
  println "${it.login} ${it.html_url}"
}
