package org.example.price.domain.model;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Instrument {

    private final String name;

    public static Instrument forName(@NonNull String name) {
        return new Instrument(name.toUpperCase());
    }

}
