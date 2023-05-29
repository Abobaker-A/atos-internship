package com.examengine.examengine.GeneratedLink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeneratedLink {
    private Date scheduledTimeFrom;
    private Date scheduledTimeTo;
    private String token;
    private String url;
}
