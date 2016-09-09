import octokit.*
import groovy.json.*

def gitHubCli = new GitHubClient(
  baseUri:"http://github.at.home/api/v3",
  token:System.getenv("TOKEN_GHE_27_K33G")
)

gitHubCli.createMilestone(
  owner: "WeylandYutani",
  repository: "mission-00",
  title: "Inception",
  state: "open",
  description: "A discover phase, where an initial problem statement and functional requirements are created.",
  due_on: "2016-09-01T09:00:00Z" 
)
