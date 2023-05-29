package com.examengine.examengine.mapper;

import com.examengine.examengine.GeneratedLink.GeneratedLink;
import com.examengine.examengine.GeneratedLink.GeneratedLinkDto;

import org.springframework.stereotype.Component;

@Component
public class GeneratedLinkMapper {

        public GeneratedLink fromDtoToEntity(GeneratedLinkDto generatedLinkDto) {
            GeneratedLink generatedLink = new GeneratedLink();
            generatedLink.setScheduledTimeFrom(generatedLinkDto.getScheduledTimeFrom());
            generatedLink.setScheduledTimeTo(generatedLinkDto.getScheduledTimeTo());
            generatedLink.setToken(generatedLinkDto.getToken());
            generatedLink.setUrl(generatedLinkDto.getUrl());
            return generatedLink;
        }

        public GeneratedLinkDto fromEntityToDto(GeneratedLink generatedLink) {
            GeneratedLinkDto generatedLinkDto = new GeneratedLinkDto();
            generatedLinkDto.setScheduledTimeFrom(generatedLink.getScheduledTimeFrom());
            generatedLinkDto.setScheduledTimeTo(generatedLink.getScheduledTimeTo());
            generatedLinkDto.setToken(generatedLink.getToken());
            generatedLinkDto.setUrl(generatedLink.getUrl());
            return generatedLinkDto;
        }
}


