import octokit.*
import groovy.json.*

def gitHubCli = new GitHubClient(
  baseUri:"http://github.at.home/api/v3",
  token:System.getenv("TOKEN_GHE_27_K33G")
)

// you need some users and at least an organization

gitHubCli.createTeam(
  org: "WeylandYutani",
  name: "NostromoCrew",
  description: "Nostromo crew",
  repo_names:[
    "WeylandYutani/mission-00", // the full name of the repository
    "WeylandYutani/mission-01",
    "WeylandYutani/mission-02"
  ],
  privacy: "closed",
  permission:"admin"
)

println gitHubCli.fetchTeams(org:"WeylandYutani")

println gitHubCli.getTeamByName(org:"WeylandYutani", name:"NostromoCrew").description
