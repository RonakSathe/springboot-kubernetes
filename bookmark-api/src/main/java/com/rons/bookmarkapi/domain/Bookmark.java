package com.rons.bookmarkapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "bookmarks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {

    @Id
    @SequenceGenerator(name = "bm_id_seq_gen",sequenceName = "bm_iq_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bm_id_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String url;
    private Instant createdAt;

}
