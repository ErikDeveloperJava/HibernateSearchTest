package hibernate.search.test.model;

import lombok.*;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@ToString(exclude = "postList")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Field
    private String name;

    @Field
    private String surname;

    private int age;

    @OneToMany(mappedBy = "user")
    @IndexedEmbedded(depth = 2)
    @ContainedIn
    private List<Post> postList;

}
