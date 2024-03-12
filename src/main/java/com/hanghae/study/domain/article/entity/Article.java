package com.hanghae.study.domain.article.entity;

import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "article_tbl")
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "article")
    private List<ArticleLike> likes;

    @Builder
    public Article(String title, String contents, Member member) {
        this.title = title;
        this.contents = contents;
        this.member = member;
    }


    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Long getLikesCount() {
        return likes != null ? (long) likes.size() : 0L;
    }
}
