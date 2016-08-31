import octokit.*
import groovy.json.*

def gitHubCli = new GitHubClient(
  baseUri:"http://github.at.home/api/v3",
  token:System.getenv("TOKEN_GHE_27_K33G")
)

gitHubCli.createOrganization(
  login: "WeylandYutani",
  admin: "k33g",
  profile_name: "Weyland-Yutani Corporation"
)

gitHubCli.createPublicOrganizationRepository(
  name:"mission-00",
  description:"mission-00.",
  organization:"WeylandYutani"
)

gitHubCli.createPublicOrganizationRepository(
  name:"mission-01",
  description:"mission-01.",
  organization:"WeylandYutani"
)

gitHubCli.createPublicOrganizationRepository(
  name:"mission-02",
  description:"mission-02.",
  organization:"WeylandYutani"
)
