import octokit.*
import groovy.json.*

def gitHubCli = new GitHubClient(
  baseUri:"http://github.at.home/api/v3",
  token:System.getenv("TOKEN_GHE_27_K33G")
)


def impersonationTokenForDallas = gitHubCli.getAuthorizationsForImpersonation(handle:"dallas").token

def dallas = new GitHubClient(
  baseUri:"http://github.at.home/api/v3",
  token:impersonationTokenForDallas
)

dallas.changeUserData(
  data:[
    name: "Dallas",
    bio: "Captain (ID# 032/V4-07C)",
    location: "Universe",
    blog: "http://alienanthology.wikia.com/wiki/Dallas"
  ]
)

def impersonationTokenForRipley = gitHubCli.getAuthorizationsForImpersonation(handle:"ripley").token

def ripley = new GitHubClient(
  baseUri:"http://github.at.home/api/v3",
  token:impersonationTokenForRipley
)

ripley.changeUserData(
  data:[
    name: "Ellen Ripley",
    bio: "Warrant Officer (ID# 759/L2-01N)",
    location: "Universe",
    blog: "http://alienanthology.wikia.com/wiki/Ellen_Ripley"
  ]
)
