package model;

import model.card.CardDeck;
import model.card.CardSize;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import model.player.User;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

public class BlackJack {

    private final Participants participants;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJack(Participants participants, Dealer dealer, CardDeck cardDeck) {
        validateParticipantIsNotNull(participants);
        validateDealerIsNotNull(dealer);
        this.participants = participants;
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public void validateParticipantIsNotNull(Participants participants) {
        if (participants == null) {
            throw new IllegalArgumentException("참가자 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void validateDealerIsNotNull(Dealer dealer) {
        if (dealer == null) {
            throw new IllegalArgumentException("딜러가 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void decideParticipantsPlay(Predicate<String> stringForMoreCard, BiConsumer<String, Cards> callback) {
        participants.offerCardToParticipants(stringForMoreCard, callback, () -> cardDeck.selectRandomCards(CardSize.ONE));
    }

    public void decideDealerPlay(Runnable runnable) {
        while (dealer.isHit()) {
            runnable.run();
            dealer.addCards(cardDeck.selectRandomCards(CardSize.ONE));
        }
    }

    public Map<Participant, Outcome> matchParticipantsOutcome() {
        List<Participant> sumPlayers = participants.participants();
        return sumPlayers.stream().collect(toMap(participant -> participant, participant -> participant.findOutcome(dealer)));
    }

    public Map<Outcome, Long> getDealerOutCome() {
        Map<Participant, Outcome> participantOutcome = matchParticipantsOutcome();
        return participantOutcome.values().stream().collect(groupingBy(Outcome::reverse, counting()));
    }

    public Map<String, Cards> mapToUsersNameAndCards() {
        return participants.participants().stream().collect(toMap(User::getName, User::getCards));
    }

    public List<String> findParticipantsName() {
        return participants.findParticipantsName();
    }

    public Map<String, Cards> mapToDealerNameAndCards() {
        return Map.of(dealer.getName(), dealer.getCards());
    }
}
