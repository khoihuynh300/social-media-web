package com.webapp.socialmedia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "db_post_media")
public class PostMedia {
    @Id
    @OneToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media media;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
