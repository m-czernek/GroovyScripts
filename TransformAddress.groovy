@Grapes([
    @Grab(
        group="org.codehaus.groovy.modules.http-builder",
        module="http-builder",
        version="0.7"
      )
  ])
//import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient

def slurper = new ConfigSlurper().parse(new File("config.groovy").toURL())
def API_KEY = slurper.key

def address = System.console().readLine 'What address do you wish to search for?>'
def url = 'https://maps.googleapis.com'

def googleApi = new RESTClient(url)

def queryString='/maps/api/geocode/json'
def httpResponse = googleApi.get(
  path:queryString,
  query:[
    'address':address,
    'key': API_KEY
  ])
println httpResponse.data.results.geometry.location.lat
println httpResponse.data.results.geometry.location.lng
