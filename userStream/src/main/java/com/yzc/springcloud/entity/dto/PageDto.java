package com.yzc.springcloud.entity.dto;

import lombok.Data;

@Data
public class PageDto {
  private   Integer current=1;
  private   Integer size=20;
}
