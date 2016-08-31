import octokit.*
import groovy.json.*

def gitHubCli = new GitHubClient(
  baseUri:"http://github.at.home/api/v3",
  token:System.getenv("TOKEN_GHE_27_K33G")
)

def idOfTeam = gitHubCli.getTeamByName(org:"WeylandYutani", name:"NostromoCrew").id

gitHubCli.addTeamMembership(
  teamId: idOfTeam,
  userName: "k33g",
  role: "maintener"
)

gitHubCli.addTeamMembership(
  teamId: idOfTeam,
  userName: "dallas",
  role: "maintener"
)

gitHubCli.addTeamMembership(
  teamId: idOfTeam,
  userName: "kane",
  role: "maintener"
)

gitHubCli.addTeamMembership(
  teamId: idOfTeam,
  userName: "ripley",
  role: "maintener"
)

gitHubCli.addTeamMembership(
  teamId: idOfTeam,
  userName: "ash",
  role: "maintener"
)

gitHubCli.addTeamMembership(
  teamId: idOfTeam,
  userName: "lambert",
  role: "maintener"
)

gitHubCli.addTeamMembership(
  teamId: idOfTeam,
  userName: "parker",
  role: "maintener"
)

gitHubCli.addTeamMembership(
  teamId: idOfTeam,
  userName: "brett",
  role: "maintener"
)
