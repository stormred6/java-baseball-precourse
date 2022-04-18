package baseball;

import baseball.domain.Game;
import baseball.domain.GameResult;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    private static boolean isExit = false;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Game game = new Game();

        while(!isExit){
            printResult(game.playGame(readLine()));
        }
    }

    private static void printResult(String outPutValue){
        System.out.println(outPutValue);

        if(outPutValue.equals(GameResult.EXIT.getValue())){
            isExit = true;
        }
    }
}
