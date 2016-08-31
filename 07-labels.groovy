import octokit.*
import groovy.json.*

def gitHubCli = new GitHubClient(
  baseUri:"http://github.at.home/api/v3",
  token:System.getenv("TOKEN_GHE_27_K33G")
)

// point

gitHubCli.createLabel(
  name: "point: 1",
  color: "bfdadc",
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'point: 2',
  color: 'd4c5f9',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'point: 3',
  color: 'c5def5',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'point: 5',
  color: '1d76db',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'point: 8',
  color: '006b75',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'point: 13',
  color: '0e8a16',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'point: 21',
  color: '5319e7',
  owner: "WeylandYutani",
  repository: "mission-00"
)

// priority

gitHubCli.createLabel(
  name: 'priority: high',
  color: 'd93f0b',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'priority: highest',
  color: 'b60205',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'priority: low',
  color: 'fbca04',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'priority: lowest',
  color: 'fef2c0',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'priority: medium',
  color: 'f9d0c4',
  owner: "WeylandYutani",
  repository: "mission-00"
)

// type

gitHubCli.createLabel(
  name: 'type: bug',
  color: 'd93f0b',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'type: chore',
  color: 'fbca04',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'type: feature',
  color: '1d76db',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'type: infrastructure',
  color: '5319e7',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'type: performance',
  color: '006b75',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'type: refactor',
  color: 'c2e0c6',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'type: tests',
  color: 'e99695',
  owner: "WeylandYutani",
  repository: "mission-00"
)

gitHubCli.createLabel(
  name: 'type: implementation',
  color: '000000',
  owner: "WeylandYutani",
  repository: "mission-00"
)
