package com.matimbe.ecommerce.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> erros
) {


}
