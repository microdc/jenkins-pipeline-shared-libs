def call(Map config) {
  node() {

    timestamps {
      def appName = config.appName
      def environment = config.environment
      def gitVersion = config.gitVersion
      def registryAddress = config.registryAddress
      def isDependencies = config.isDependencies ?: false

      stage('Deploy microservice') {
        if (env.BRANCH_NAME == 'master' && ! config.isDependencies) {
          echo "Deploying ${appName} version ${gitVersion}"
          build(
              job: 'deploy-microservice',
              parameters: [
                  string(name: 'MICROSERVICE', value: appName),
                  string(name: 'VERSION', value: gitVersion),
                  string(name: 'ENV', value: environment),
                  string(name: 'DOCKER_REPO', value: registryAddress),
                  string(name: 'CONFIG_BRANCH', value: env.BRANCH_NAME)
              ]
          )
        }
      }
    }
  }
}
