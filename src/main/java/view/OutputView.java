package view;

import model.card.Card;
import model.card.Cards;
import model.card.Denomination;
import model.card.Suit;
import model.player.Name;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String DEALER_NAME = "딜러";
    private static final String DIVIDE_CARD_MESSAGE = DEALER_NAME + "와 %s에게 2장을 나누었습니다.";
    private static final String RECEIVED_CARD_MESSAGE = "%s 카드 : %s";
    private static final String PLAYER_CARD_SUM_MESSAGE = " - 결과: %d";
    private static final String DEALER_ADD_CARD_MESSAGE = DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_PROMPT_MESSAGE = "## 최종 수익";
    private static final String GAME_RESULT_REVENUE_MESSAGE = "%s : %d";

    public void printPlayerNames(List<Name> names) {
        List<String> stringNames = names.stream()
                .map(Name::getName)
                .toList();
        System.out.println(System.lineSeparator() + DIVIDE_CARD_MESSAGE.formatted(String.join(", ", stringNames)));
    }

    public void printPlayerCards(Cards dealerCards, Map<Name, Cards> usersNameAndCards) {
        System.out.println(cardsToString(DEALER_NAME, dealerCards, 1));

        for (Entry<Name, Cards> entry : usersNameAndCards.entrySet()) {
            System.out.println(cardsToString(entry));
        }
        System.out.println();
    }

    public void printPlayerCardMessage(Name name, Cards cards) {
        System.out.println(cardsToString(name.getName(), cards, cards.cards().size()));
    }

    public void printBlackJackScore(Cards dealerCards, Map<Name, Cards> usersNameAndCards) {
        String dealerCardsString = cardsToString(DEALER_NAME, dealerCards, dealerCards.cards().size());
        String dealerScore = PLAYER_CARD_SUM_MESSAGE.formatted(dealerCards.calculateScore());
        System.out.println(System.lineSeparator() + dealerCardsString + dealerScore);
        for (Entry<Name, Cards> participant : usersNameAndCards.entrySet()) {
            System.out.println(cardsToString(participant) + PLAYER_CARD_SUM_MESSAGE.formatted(
                    participant.getValue().calculateScore()));
        }
    }

    public void printParticipantsRevenue(int dealerRevenue, Map<Name, Integer> participantsRevenue) {
        System.out.println(System.lineSeparator() + GAME_RESULT_PROMPT_MESSAGE);

        System.out.println(GAME_RESULT_REVENUE_MESSAGE.formatted(DEALER_NAME, dealerRevenue));

        for (Entry<Name, Integer> participant : participantsRevenue.entrySet()) {
            System.out.println(GAME_RESULT_REVENUE_MESSAGE.formatted(participant.getKey().getName(), participant.getValue()));
        }
    }

    private String cardsToString(Entry<Name, Cards> userNameAndCards) {
        int size = userNameAndCards.getValue().cards().size();
        return cardsToString(userNameAndCards.getKey().getName(), userNameAndCards.getValue(), size);
    }

    private String cardsToString(String name, Cards userCards, int cardCountToPrint) {
        List<Card> cards = userCards.cards();
        int cardCountNotToPrint = cards.size() - cardCountToPrint;
        String cardNames = String.join(", ", cards.stream()
                .skip(cardCountNotToPrint)
                .map(this::cardToString)
                .toList());
        return RECEIVED_CARD_MESSAGE.formatted(name, cardNames);
    }

    private String cardToString(Card card) {
        String denomination = denominationToString(card.denomination());
        String suit = suitToString(card.suit());
        return denomination + suit;
    }

    public void printDealerAddCard() {
        System.out.println(System.lineSeparator() + DEALER_ADD_CARD_MESSAGE);
    }

    private String denominationToString(Denomination denomination) {
        if (denomination == Denomination.ACE) {
            return "A";
        }
        if (denomination == Denomination.QUEEN) {
            return "Q";
        }
        if (denomination == Denomination.KING) {
            return "K";
        }
        if (denomination == Denomination.JACK) {
            return "J";
        }
        return String.valueOf(denomination.minimumNumber());
    }

    private String suitToString(Suit suit) {
        if (suit == Suit.SPACE) {
            return "스페이드";
        }
        if (suit == Suit.ClUBS) {
            return "클로버";
        }
        if (suit == Suit.HEART) {
            return "하트";
        }
        return "다이아몬드";
    }

}
