package com.lab.mqttconfig.dto;

import lombok.*;

@Data
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AddFaceRequest {
    private String customId;
    private String name;
    private int gender; // 0: Male, 1: Female
    private int tempCardType; // 0: Permanent, 1: Temporary
    private int personType; // 0: White list, 1: Black list
    private int cardType; // 0: ID card
    private String pic; // Base64 encoded image
}
