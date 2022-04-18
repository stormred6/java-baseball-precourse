package baseball.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class GameTest {
    private Game game;
    private Game targetSetGame;
    @BeforeEach
    void initGame(){
        game = new Game();
        List<String> targetList = Arrays.asList("1", "2", "3");
        targetSetGame = new Game(targetList);
    }
    @Test
    @DisplayName("예외 테스트")
    void exceptionPlayGameTest() {
        //given
        String invalidInputValue = "1234";
        //when then
        Assertions.assertThatThrownBy(() -> game.playGame(invalidInputValue))
                        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("볼 카운트 테스트")
    void ballCountPlayGame(){
        //given
        String oneBallInputValue = "299";

        //when
        targetSetGame.playGame(oneBallInputValue);

        //then
        Assertions.assertThat(targetSetGame.getGameResult()).isEqualTo("1"+GameResult.BALL.getValue());
    }

    @Test
    @DisplayName("스트라이크 카운트 테스트")
    void strikeCountPlayGame(){
        //given
        String oneStrikeInputValue = "199";

        //when
        targetSetGame.playGame(oneStrikeInputValue);

        //then
        Assertions.assertThat(targetSetGame.getGameResult()).isEqualTo("1"+GameResult.STRIKE.getValue());
    }

    @Test
    @DisplayName("1볼 1스트라이크 카운트 테스트")
    void ballAndStrikeCountPlayGame(){
        //given
        String oneBallAndoneStrikeInputValue = "192";

        //when
        targetSetGame.playGame(oneBallAndoneStrikeInputValue);

        //then
        Assertions.assertThat(targetSetGame.getGameResult()).isEqualTo("1볼 1스트라이크");
    }

    @Test
    @DisplayName("3스트라이크 카운트 테스트")
    void threeStrikeCountPlayGame(){
        //given
        String threeStrikeInputValue = "123";

        //when
        targetSetGame.playGame(threeStrikeInputValue);

        //then
        Assertions.assertThat(targetSetGame.getGameResult()).contains("3스트라이크");
    }

    @Test
    @DisplayName("INIT Game 테스트")
    void initGameTest(){
        //given
        String initCode = "1";

        //when
        targetSetGame.playGame(initCode);

        //then
        Assertions.assertThat(targetSetGame.getGameResult()).isEqualTo(GameResult.RESTART.getValue());
    }

    @Test
    @DisplayName("EXIT Game 테스트")
    void exitGameTest(){
        //given
        String exitCode = "2";

        //when
        targetSetGame.playGame(exitCode);

        //then
        Assertions.assertThat(targetSetGame.getGameResult()).isEqualTo(GameResult.EXIT.getValue());
    }
}