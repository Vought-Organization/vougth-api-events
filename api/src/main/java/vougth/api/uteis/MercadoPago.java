package vougth.api.uteis;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MercadoPago {

    public static void main(String[] args) throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken("TEST-5235337369802869-100823-4f5a232a4ddc844ad31f3a8a9744e5ba-1213858486");

        // Cria um objeto de preferência
        PreferenceClient client = new PreferenceClient();

    // Cria um item na preferência
        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceItemRequest item =
                PreferenceItemRequest.builder()
                        .title("Meu produto")
                        .quantity(1)
                        .unitPrice(new BigDecimal("100"))
                        .build();
        items.add(item);

        PreferenceRequest request = PreferenceRequest.builder().items(items).build();

        client.create(request);
    }
}
