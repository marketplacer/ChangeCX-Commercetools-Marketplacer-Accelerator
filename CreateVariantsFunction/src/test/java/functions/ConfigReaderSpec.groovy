package functions

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


class ConfigReaderSpec extends Specification {
    @Subject
    def configReader = new ConfigReader()

    def "should read clientId from properties file"() {
        expect:
        configReader.getClientId() == "commercetoolsClientId"
    }

    def "should read secret from properties file"() {
        expect:
        configReader.getSecret() == "commercetoolsSecret"
    }

    def "should read projectId from properties file"() {
        expect:
        configReader.getProjectId() == "commercetoolsProjectId"
    }

    @Unroll("should read #propertyName from properties file")
    def "should read specific property from properties file"(String propertyName, String expectedValue) {
        expect:
        configReader.properties.get(propertyName) == expectedValue

        where:
        propertyName | expectedValue
        "clientId"   | "commercetoolsClientId"
        "secret"     | "commercetoolsSecret"
        "projectId"  | "commercetoolsProjectId"
        "mainProductType" | "handbag"
        "rootCategory" | "c10fb35a-cb34-4294-8831-628b4f773dea"
        "childCategory" | "5f20b8cb-b374-4280-8599-6fcd22dcde48"
    }

}
