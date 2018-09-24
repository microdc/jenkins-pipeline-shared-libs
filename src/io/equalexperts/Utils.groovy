package io.equalexperts;

def isOnAzure() {
  def metadataUrl = 'http://169.254.169.254/metadata/instance?api-version=2017-08-01'
  def response = httpRequest(
                      url: metadataUrl,
                      customHeaders: [[name: 'Metadata', value: 'true']],
                      validResponseCodes: "100:499"
                  )
  return response.status == 200
}

return this
