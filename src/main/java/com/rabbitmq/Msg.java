package com.rabbitmq;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Msg {

    private Integer id;
    private String desc;

}
