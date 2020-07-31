package com.dsloveling.shorturl.convert.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dsloveling
 * @version 1.0
 * @date 2020-04-28 19:13
 */
@Entity
@Table(name = "t_shorturl",indexes = {@Index(columnList = "c_key",unique = true),@Index(columnList = "c_short")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "c_key")
    @Size(max = 40)
    private String key;

    @Column(name = "c_short")
    @Size(max = 12)
    private String shortUrl;

    @Column(name = "c_source")
    private String sourceUrl;

    @Column(name = "dt_updatetime")
    private Date updatetime;

}
