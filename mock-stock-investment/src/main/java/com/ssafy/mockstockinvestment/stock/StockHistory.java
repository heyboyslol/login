package com.ssafy.mockstockinvestment.stock;

import com.ssafy.mockstockinvestment.user.domain.Student;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "stock_histories") //DB 테이블
//@Data
// getter, setter, 기본 생성자, toString() 메서드
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StockHistory {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="stock_history_id")
    private Long stockHistoryId;

    @Column(name="act", length = 4) //action
    @NotNull
    @Enumerated(EnumType.STRING)
    private StockDealActionEnum act;

    @Column(name="quantity") //수량
    @NotNull
    private Integer quantity;

    @Column(name="created_at") //일자
    @NotNull
    private Timestamp createdAt;

    @Column(name="era") //시대정보
    @NotNull
    private Integer era;

    @ManyToOne
    @JoinColumn(name="user_id_fk",nullable = false) //행위자
    @NotNull
    private Student userIdFK;

    @ManyToOne
    @JoinColumn(name="stock_id_fk",nullable = false) //주식 정보
    @NotNull
    private Stock stockIdFK;
}


