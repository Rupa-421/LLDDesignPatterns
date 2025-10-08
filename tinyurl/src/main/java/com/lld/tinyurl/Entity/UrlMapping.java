package com.lld.tinyurl.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false , unique = true)
    private String shortCode;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

}
