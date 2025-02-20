package es.grupo18.jobmatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private Long id;
    private Long userId;
    private Long companyId;
    private LocalDateTime createdAt;
    private boolean mutual; // true if company also liked the user
}
