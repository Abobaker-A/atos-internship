package com.examengine.examengine.GeneratedLink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GeneratedLinkDto {
    private Date scheduledTimeFrom;
    private Date scheduledTimeTo;
    private String token;
    private String url;
}
