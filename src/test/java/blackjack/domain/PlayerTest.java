package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    Deck deck;
    Player player;

    @BeforeEach
    void setUp() {
        deck = new Deck();
        player = new Player("pobi");
    }

    @Test
    @DisplayName("에이스를 포함하지 않는 점수 계산")
    void calcScoreTest() {
        // given
        player.pickCard(deck, 11); // QUEEN CLOVER
        player.pickCard(deck, 10); // JACK CLOVER

        // when
        int result = player.getScore();

        // then
        assertThat(result).isEqualTo(20);
    }

    @Test
    @DisplayName("1점 에이스를 포함할 때 점수 계산")
    void calcScoreTest2() {
        // given
        player.pickCard(deck, 11); // QUEEN CLOVER
        player.pickCard(deck, 10); // JACK CLOVER
        player.pickCard(deck, 0); // ACE CLOVER

        // when
        int result = player.getScore();

        // then
        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("11점 에이스를 포함할 때 점수 계산")
    void calcScoreTest3() {
        // given
        player.pickCard(deck, 11); // QUEEN CLOVER
        player.pickCard(deck, 0); // ACE CLOVER

        // when
        int result = player.getScore();

        // then
        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("21점보다 클 때 isDead() 테스트")
    void isDeadTest() {
        // given
        player.pickCard(deck, 11); // QUEEN CLOVER
        player.pickCard(deck, 10); // JACK CLOVER
        player.pickCard(deck, 1); // 2 CLOVER

        // when
        boolean result = player.isDead();

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("21점보다 작거나 같을 때 isDead() 테스트")
    void isDeadTest2() {
        // given
        player.pickCard(deck, 11); // QUEEN CLOVER
        player.pickCard(deck, 0); // ACE CLOVER

        // when
        boolean result = player.isDead();

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("21점보다 크거나 같을 때 canPickCard() 테스트")
    void canPickCardTest() {
        // given
        player.pickCard(deck, 11); // QUEEN CLOVER
        player.pickCard(deck, 0); // ACE CLOVER

        // when
        boolean result = player.canPickCard();

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("21점보다 작을 때 canPickCard() 테스트")
    void canPickCardTest2() {
        // given
        player.pickCard(deck, 11); // QUEEN CLOVER
        player.pickCard(deck, 10); // JACK CLOVER

        // when
        boolean result = player.canPickCard();

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("2점과 30점 플레이어 compareTo() 결과 확인")
    void compareToTest() {
        // given
        player.pickCard(deck, 12); // KING CLOVER
        player.pickCard(deck, 11); // QUEEN CLOVER
        player.pickCard(deck, 10); // JACK CLOVER
        Player other = new Player("crong");
        other.pickCard(deck, 1); // 2 CLOVER

        // when
        int result = player.compareTo(other);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("2점과 11점 플레이어 compareTo() 결과 확인")
    void compareToTest2() {
        // given
        player.pickCard(deck, 1); // 2 CLOVER
        Player other = new Player("crong");
        other.pickCard(deck, 0); // ACE CLOVER

        // when
        int result = player.compareTo(other);

        // then
        assertThat(result).isEqualTo(-1);
    }
}