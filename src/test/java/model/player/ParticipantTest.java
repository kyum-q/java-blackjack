package model.player;

import model.GameMoney;
import model.Outcome;
import model.card.Card;
import model.card.Cards;
import model.card.Denomination;
import model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParticipantTest {

    @DisplayName("카드의 합이 21이하일 때는 참을 반환한다.")
    @Test
    void isHitTrue() {
        Participant participant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.KING))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));

        assertFalse(participant.isNotHit());
    }

    @DisplayName("카드의 합이 21초과일 때는 거짓을 반환한다.")
    @Test
    void isHitFalse() {
        Participant participant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.KING))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));
        participant.addCard(Card.of(Suit.CLOVER, Denomination.THREE));

        assertTrue(participant.isNotHit());
    }
}
