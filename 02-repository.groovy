import octokit.*
import groovy.json.*

def gitHubCli = new GitHubClient(
    baseUri:"http://github.at.home/api/v3",
    token:System.getenv("TOKEN_GHE_27_K33G")
  )

def toolsRepo = gitHubCli.createPublicRepository(name:"tools", description:"my little tools repo")
println JsonOutput.toJson(toolsRepo)

gitHubCli.createPrivateRepository(name:"privateTools", description:"my little private tools repo")

gitHubCli.createPublicOrganizationRepository(
  name:"tools",
  description:"my little tools repo of ACME org.", 
  organization:"ACME"
)
