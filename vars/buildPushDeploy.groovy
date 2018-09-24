import io.equalexperts.Utils

def call(Map config) {
  def utils = new Utils()
  if (utils.isOnAzure()) {
    config.registryAddress = config.registryAddresses['acr']
    acrBuildPush(config)
    config.gitVersion = env.GIT_VERSION
    deploy(config)
  } else {
    config.registryAddress = config.registryAddresses['ecr']
    ecrBuildPushDeploy(config)
  }
}
