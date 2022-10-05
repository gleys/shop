package sample.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
public class Image extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "image_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @Lob
    @Column(nullable = false)
    //private Blob image
    private byte[] image;

}
