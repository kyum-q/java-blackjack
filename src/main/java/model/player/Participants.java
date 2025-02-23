package model.player;

import model.card.Card;
import model.card.Cards;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Participants {
    public static final int MINIMUM_PARTICIPANT_SIZE = 2;
    public static final int MAXIMUM_PARTICIPANT_SIZE = 8;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateNotDuplicatedParticipant(participants);
        validateParticipantSize(participants);
        this.participants = participants;
    }

    private void validateNotDuplicatedParticipant(List<Participant> participants) {
        Set<Participant> duplicates = participants.stream()
                .filter(n -> Collections.frequency(participants, n) > 1)
                .collect(Collectors.toSet());

        if (!duplicates.isEmpty()) {
            String duplicatedName = duplicates.stream()
                    .map(user -> user.name().getName())
                    .collect(Collectors.joining(","));
            throw new IllegalArgumentException
                    ("중복된 이름(" + duplicatedName + ")가 있습니다, 참가자들의 이름은 중복되면 안됩니다.");
        }
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.size() < MINIMUM_PARTICIPANT_SIZE || participants.size() > MAXIMUM_PARTICIPANT_SIZE) {
            throw new IllegalArgumentException
                    ("참가자의 수는 " + MINIMUM_PARTICIPANT_SIZE + " ~ " + MAXIMUM_PARTICIPANT_SIZE + "명이어야 합니다.");
        }
    }

    public void offerCardToParticipants(Predicate<Name> inputForMoreCard,
                                        BiConsumer<Name, Cards> printParticipantsCard, Supplier<Card> selectCard) {
        for (Participant participant : participants) {
            participant.offerCard(inputForMoreCard, printParticipantsCard, selectCard);
        }
    }

    public List<Name> findParticipantsName() {
        return participants.stream()
                .map(Participant::name).toList();
    }

    public Map<Name, Cards> matchParticipantNameAndCards() {
        return participants.stream()
                .collect(toMap(
                        Participant::name,
                        User::cards));
    }

    public Map<Name, Integer> matchNameAndRevenues(Dealer dealer) {
        return participants.stream()
                .collect(toMap(
                        Participant::name,
                        participant -> participant.calculateRevenue(dealer)));
    }
}
