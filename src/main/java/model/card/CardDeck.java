package model.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public class CardDeck {

    private Set<Card> cards;

    public CardDeck(List<Card> cards) {
        this(new HashSet<>(cards));
    }

    public CardDeck(Set<Card> cards) {
        if (cards.size() != 52) {
            throw new IllegalArgumentException("카드는 총 52개여야 합니다.");
        }
        this.cards = cards;
    }

    public static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            createSameShapeCards(cardShape, cards);
        }
        return cards;
    }

    private static void createSameShapeCards(CardShape cardShape, List<Card> cards) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public List<Card> selectRandomCards(CardSize size) {
        return Stream.generate(this::selectRandomCard).limit(size.getSize()).toList();
    }

    private Card selectRandomCard() {
        validateCardDeckNotEmpty();
        int removeIndex = new Random().nextInt(cards.size());
        Card card = new ArrayList<>(cards).remove(removeIndex);
        cards.remove(card);
        return card;
    }

    private void validateCardDeckNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 뽑을 카드가 없습니다.");
        }
    }
}
