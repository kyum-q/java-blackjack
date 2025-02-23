package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackStateTest {
    @DisplayName("카드의 합이 21을 넘지 않은 경우, 스탠드 상태를 얻을 수 있다.")
    @Test
    void createBlackJackStateStand() {
        BlackJackState state = BlackJackState.createBlackJackState(20, 2);
        assertThat(state).isEqualTo(BlackJackState.STAND);
    }

    @DisplayName("카드의 총합이 21을 넘는 경우, 버스트 상태를 얻을 수 있다.")
    @Test
    void createBlackJackStateBust() {
        BlackJackState state = BlackJackState.createBlackJackState(22, 2);
        assertThat(state).isEqualTo(BlackJackState.BUST);
    }

    @DisplayName("2장의 합이 21인 경우, 블랙잭 상태를 얻을 수 있다.")
    @Test
    void createBlackJackStateBlackJack() {
        BlackJackState state = BlackJackState.createBlackJackState(21, 2);
        assertThat(state).isEqualTo(BlackJackState.BLACKJACK);
    }

    @DisplayName("스탠드 상태일 경우 이익률은 1배이다.")
    @Test
    void calculateBattingRatioStand() {
        int result = BlackJackState.STAND.calculateBattingRatio(1000);
        assertThat(result).isEqualTo(1000);
    }

    @DisplayName("버스트 상태일 경우 이익률은 -1배이다.")
    @Test
    void calculateBattingRatioBust() {
        int result = BlackJackState.BUST.calculateBattingRatio(1000);
        assertThat(result).isEqualTo(-1000);
    }

    @DisplayName("블랙잭 상태일 경우 이익률은 1.5배이다.")
    @Test
    void calculateBattingRatioBlackJack() {
        int result = BlackJackState.BLACKJACK.calculateBattingRatio(1000);
        assertThat(result).isEqualTo(1500);
    }
}
