# ChangeCX-Commercetools-Marketplacer-Accelerator


## Accelerator Description

Marketplacer offers a webhook feature that enables connection to any external endpoint, allowing for the real-time sharing of information such as products, product variant(adverts), and seller updates. By utilizing this mechanism, Marketplacer can integrate with commercetools to create and promote products on a live commercetools frontend. This integration ensures that all updates are immediately reflected on the commercetools platform, aligning with the principles of the MACH alliance.

## Benefits

Real-time integration of information between Marketplacer and commercetools, ensuring that all updates are immediately reflected on the commercetools platform.

The ability to easily connect to any external endpoint using the webhook feature, allowing for the seamless sharing of information.

The ability to create and promote products on a live commercetools frontend, increasing visibility and sales potential.

Alignment with the principles of the MACH alliance, ensuring a high level of interoperability and flexibility.

Improve the efficiency of your workflow, by having a single source of truth for products, sellers and advert.

Automate the process of creating, updating and deleting products, variants and sellers on commercetools, reducing manual errors and saving time.

## High Level design

The integration between Marketplacer and commercetools can be represented by a high-level design diagram, which illustrates the flow of information between the two systems. The diagram includes the following components:

- Marketplacer: This component serves as the central hub for managing advertisements, product variants, and seller information.

- Webhook: A mechanism for connecting to external endpoints, allowing for the real-time sharing of information between Marketplacer and commercetools.

- Commercetools: The platform for creating and promoting products on a live frontend.

- Event: Triggers the flow of information between Marketplacer and commercetools, such as the creation or update of a product variant.

- JSON Payload: This can be Product, variant, invoice, seller information, etc. The data that is shared between Marketplacer and commercetools to ensure consistency and accuracy.

- Function: The endpoint that receives a JSON object containing the information from Marketplacer through the webhook. This endpoint can be implemented using different technologies such as Google Cloud Function, AWS Lambda, an on-premises web service or using a pipeline workflow.

![image](https://user-images.githubusercontent.com/118915499/225122330-71cde472-fae5-46af-ae2a-afeaaead75fd.png)

The webhooks are configured using GraphQL queries to gather the necessary information from Marketplacer, this information is then sent to the Google Cloud Function endpoint in the form of a JSON object. This allows for the automatic creation, update, and deletion of products, variants and sellers on commercetools, reducing the need for manual input and increasing efficiency. The endpoint will use the commercetools Java API to communicate with commercetools and execute the required actions. The choice of the endpoint technology will depend on the specific needs and constraints of the organization.

## Screenshots

The integration between Marketplacer and commercetools involves configuring webhooks, GraphQL queries, and endpoint functions to ensure real-time sharing of information. To help visualize the different components of the integration, screenshots of the configuration and usage of these tools are provided below.

### Webhook Configuration

The screenshot shows the webhook configuration page in Marketplacer, where the URL of the endpoint function is entered and the events that will trigger the webhook are selected.

![image](https://user-images.githubusercontent.com/118915499/222575430-f18aec9e-cf35-4f84-aa87-e9589fb82e24.png)

### GraphQL Configuration

The screenshot shows the GraphQL configuration page in Marketplacer, where the necessary information to be gathered from Marketplacer and sent to the endpoint is specified.

![image](https://user-images.githubusercontent.com/118915499/222575492-17fbbe9d-efad-47bf-85bf-d74dfdc9c117.png)

The screenshot shows the GraphQL configuration page in Marketplacer, where the necessary information to be gathered from Marketplacer and sent to the endpoint is specified.

### GCP Function 

The screenshot shows the GCP Function in the Google Cloud Console, where the function code and triggers can be viewed and edited.

![image](https://user-images.githubusercontent.com/118915499/222575581-9e2c4ed8-3291-4a29-b0ad-d70c2bf2854a.png)

### Marketplacer Product Update

The screenshot shows the Marketplacer web application, where a product was updated and saved.
![image](https://user-images.githubusercontent.com/118915499/222575625-5e5cbb4c-479b-4b79-a123-e6584b474b94.png)

### Commercetools Merchant Center 

The screenshot shows the Commercetools merchant center, where the product updated arrived as un published.
![image](https://user-images.githubusercontent.com/118915499/222575689-83124961-0b35-4249-a448-4a641ddd142c.png)

### Commercetools Front End 

The screenshot shows the front-end application that uses the commercetools to present the product just updated:
![image](https://user-images.githubusercontent.com/118915499/222575739-1b5d304f-b028-4d87-ab57-24557d4276e5.png)

## Conclusion

The integration between Marketplacer and commercetools provides a powerful solution for real-time sharing of information between the two systems. The webhook mechanism, GraphQL queries, and endpoint functions work together to ensure that product, variant, and seller information is seamlessly shared, resulting in real-time updates and increased visibility for products on the commercetools platform. Additionally, the integration allows to show the products, variants, sellers and ads on a front-end application. This integration provides a flexible and scalable solution that can be adapted to suit the specific needs and constraints of an organization. By implementing this integration, organizations can improve the efficiency and accuracy of their product management and promotion efforts.

