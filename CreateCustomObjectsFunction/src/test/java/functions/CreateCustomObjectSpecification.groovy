package functions

import com.commercetools.api.models.custom_object.CustomObject
import spock.lang.Specification


class CreateCustomObjectSpecification extends Specification {

    CreateCustomObject testObject = new CreateCustomObject()

    MarketplacerRequest request =  new MarketplacerRequest()
    Payload payload = new Payload()
    Data data = new Data()
    Node node = new Node()
    Random random = new Random()
    Address address = new Address()

    def setup() {
        request.setPayload(payload)
        payload.setData(data)
        data.setNode(node)
        node.setBusinessName("Sample BN")
        node.setAddress(address)
        address.setFullAddress("123s Front St, Memphis TN, 31220")
        node.setTypename("spockTest")

    }

    def "should create a custom object" () {
        given:
        def nodeId = "id" +random.nextInt()
        node.setId(nodeId)

        when:
        CustomObject prod = testObject.createCustomObject(request)

        then:
        prod.getKey() == nodeId
    }


    def "should update a custom object" () {
        given:
        def nodeId = "id-552916757"
        def businessName = "Sample BN" +random.nextInt()
        node.setId(nodeId)
        node.setBusinessName(businessName)

        when:
        CustomObject customObject = testObject.createCustomObject(request)

        then:
        customObject.getKey() == nodeId
        customObject.getValue().toString().contains(businessName)
    }


}