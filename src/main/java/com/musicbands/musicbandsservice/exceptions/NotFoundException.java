package com.musicbands.musicbandsservice.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends Error {
    private final Long entityID;
    private final String entityName;
    private final Integer code = 404;

    public NotFoundException(Long entityID, String entityName) {
        this.entityID = entityID;
        this.entityName = entityName;
    }

    public String getMessage() {
        return String.format("Объект \"%s\" с идентификатором %d не найден.", entityName, entityID);
    }
}

