package sample.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Image extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "image_id", columnDefinition = "varbinary(16)")
    private UUID id;

    @Lob
    @Column(nullable = false)
    //private Blob image
    private byte[] image;

    public static Image createImage(byte[] image) {
        Image file = new Image();
        file.image = image;

        return file;
    }

}
