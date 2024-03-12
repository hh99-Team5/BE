package com.hanghae.study.domain.article.entity;

import com.hanghae.study.domain.member.entity.Member;
import com.hanghae.study.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "article_tbl")
@SQLDelete(sql = "UPDATE article_tbl SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@DynamicUpdate
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Article(String title, String contents, boolean deleted, Member member) {
        this.title = title;
        this.contents = contents;
        this.deleted = deleted;
        this.member = member;
    }

    public void update(String title, String contents) {
        if (title != null) {
            this.title = title;
        }
        if (contents != null) {
            this.contents = contents;
        }
    }
}
