package com.gbook.api.test;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@QuarkusTest
public class AccountsIntegrationRouteTest {

    @Inject
    CamelContext camelContext;

    @Inject
    ProducerTemplate producerTemplate;
/*
    @Test
    public void testClientIntegrationRoute() throws Exception {
        AdviceWith.adviceWith(camelContext, "db-to-json-clients", a -> {
            a.replaceFromWith("direct:start-test");
        });

        MockEndpoint mockResult = camelContext.getEndpoint("mock:result", MockEndpoint.class);
        mockResult.expectedMessageCount(1);
        mockResult.message(0).body().contains("\"name\":\"Cliente de Prueba\"");
        mockResult.message(0).body().contains("\"vatNumber\":\"B12345678\"");
        mockResult.message(0).body().contains("\"branchId\":1");

        producerTemplate.sendBody("direct:start-test", createMockSqlResult());

        mockResult.assertIsSatisfied();

        System.out.println("Test de la ruta de integración completado con éxito!");
        System.out.println("JSON recibido en el mock: " + mockResult.getReceivedExchanges().get(0).getIn().getBody(String.class));
    }


    private List<Map<String, Object>> createMockSqlResult() {
        List<Map<String, Object>> rows = new ArrayList<>();
        Map<String, Object> row1 = new HashMap<>();
        row1.put("name", "Cliente de Prueba");
        row1.put("address1", "Calle Falsa 123");
        row1.put("address2", "");
        row1.put("city", "Ciudad Test");
        row1.put("region", "Provincia Test");
        row1.put("postcode", "08001");
        row1.put("countryId", "001"); // Como String, igual que vendría de la BD
        row1.put("email", "test@cliente.com");
        row1.put("phone", "930000000");
        row1.put("phone2", "600000000");
        row1.put("fax", "930000001");
        row1.put("website", "www.test.com");
        row1.put("vatNumber", "B12345678");
        row1.put("comment", "Comentario de prueba");
        row1.put("salesRepId1", "001");
        row1.put("typeId", 1); // Como número, ya que es decimal
        row1.put("segmentId", "002");
        row1.put("branchId", "001");
        row1.put("rateId", "003");
        rows.add(row1);
        return rows;
    }
 */
}