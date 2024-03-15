package controller;

import model.BlackJack;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardSize;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participants;
import model.player.ParticipantsBuilder;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        BlackJack blackJack = createBlackJack();

        outputView.printPlayerNames(blackJack.findParticipantsName());
        outputView.printPlayerCards(blackJack.matchUsersNameAndCards(), blackJack.mapToDealerNameAndCards());

        offerMoreCards(blackJack);

        outputView.printBlackJackScore(blackJack.matchUsersNameAndCards(), blackJack.mapToDealerNameAndCards());
        //outputView.printPlayersOutcome(blackJack.getDealerOutCome(), blackJack.matchParticipantsOutcome());
        outputView.printParticipantsRevenue(blackJack.findDealerRevenue(), blackJack.matchNameAndRevenues());
    }

    private BlackJack createBlackJack() {
        CardDeck cardDeck = new CardDeck(Card.createCardDeck());
        Participants participants = createParticipant(() -> cardDeck.selectRandomCards(CardSize.TWO));
        Dealer dealer = new Dealer(cardDeck.selectRandomCards(CardSize.TWO));
        return new BlackJack(participants, dealer, cardDeck);
    }

    private Participants createParticipant(Supplier<Cards> selectCard) {
        List<String> names = inputView.requestParticipantNames();

        return new ParticipantsBuilder().names(names)
                .gameMoneys(names.stream()
                        .map(inputView::requestParticipantMoney)
                        .toList())
                .cards(selectCard)
                .build();
    }

    private void offerMoreCards(BlackJack blackJack) {
        blackJack.decideParticipantsPlay(inputView::isOneMoreCard, outputView::printPlayerCardMessage);
        blackJack.decideDealerPlay(outputView::printDealerAddCard);
    }
}
