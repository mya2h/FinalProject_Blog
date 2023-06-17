package CS544.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean reacted;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @Valid
    private User reactor;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reaction_post")
    @Valid
    private Post post;

    @PastOrPresent
    private final LocalDate date = LocalDate.now();
}
