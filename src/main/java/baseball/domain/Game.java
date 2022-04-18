package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private static final String EXIT_CODE = "2";
    private static final String INIT_CODE = "1";
    private static final int FIANL_STRIKE_VALUE = 3;
    private List<String> inputNumbers;
    private List<String> targetNumbers;
    private boolean isExit;
    private boolean isInit;
    private int ballCount;
    private int strikeCount;

    public Game(){
        initVariables();
        newTargetNumbers();
    }

    public Game(List<String> targetVaues){
        initVariables();
        targetNumbers = new ArrayList<>();
        targetNumbers.addAll(targetVaues);
    }

    public void playGame(String inputValue){
        if(inputValue.length() > FIANL_STRIKE_VALUE){
            throw new IllegalArgumentException();
        }

        initVariables();

        if(INIT_CODE.equals(inputValue)){
            isInit = true;
            return;
        }

        if(EXIT_CODE.equals(inputValue)){
            isExit = true;
            return;
        }

        setInputNumbers(inputValue);

        for(int i = 0; i < targetNumbers.size() ; i++){
            ball(i,strike(i));
        }
    }

    private void ball(int i,boolean isStrike) {
        if(isStrike || i >= inputNumbers.size()){
            return;
        }

        if(targetNumbers.contains(inputNumbers.get(i))){
            ballCount++;
        }
    }


    private boolean strike(int i) {
        if(i >= inputNumbers.size()){
            return false;
        }

        if(Objects.equals(targetNumbers.get(i), inputNumbers.get(i))){
            strikeCount++;
            return true;
        }

        return false;
    }

    private void setInputNumbers(String inputValue){
        for(int i = 0 ; i < inputValue.length() ; i++){
            inputNumbers.add(inputValue.substring(i,i+1));
        }
    }

    public String getGameResult() {
        if(isExit){
            return GameResult.EXIT.getValue();
        }

        if(isInit){
            initVariables();
            newTargetNumbers();
            return GameResult.RESTART.getValue();
        }

        if(ballCount == 0 && strikeCount == 0){
            return GameResult.NOTHING.getValue();
        }

        return makeResultString();
    }

    private String makeResultString() {
        StringBuilder sb = new StringBuilder();

        if(ballCount > 0){
            sb.append(ballCount).append(GameResult.BALL.getValue());
        }

        if(ballCount > 0 && strikeCount > 0){
            sb.append(" ");
        }

        if(strikeCount > 0){
            sb.append(strikeCount).append(GameResult.STRIKE.getValue());
        }

        if(strikeCount == FIANL_STRIKE_VALUE){
            sb.append(System.getProperty("line.separator"))
                    .append(GameResult.FINAL_EXIT.getValue())
                    .append(System.getProperty("line.separator"))
                    .append(GameResult.RESTART_MESSAGE.getValue());
        }

        return sb.toString();
    }

    private void initVariables(){
        isInit = false;
        strikeCount = 0;
        ballCount = 0;
        inputNumbers = new ArrayList<>();
    }

    private void newTargetNumbers(){
        targetNumbers = new ArrayList<>();
        targetNumbers.add(String.valueOf(Randoms.pickNumberInRange(1, 9)));
        targetNumbers.add(String.valueOf(Randoms.pickNumberInRange(1, 9)));
        targetNumbers.add(String.valueOf(Randoms.pickNumberInRange(1, 9)));
    }
}
