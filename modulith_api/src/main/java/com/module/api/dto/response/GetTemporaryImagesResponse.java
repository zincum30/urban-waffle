package com.module.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTemporaryImagesResponse {

    private String originalName;
    private String url;
}
