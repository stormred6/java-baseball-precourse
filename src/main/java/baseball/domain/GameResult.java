package baseball.domain;

public enum GameResult {
    STRIKE("스트라이크")
    ,BALL("볼")
    ,NOTHING("낫싱")
    ,EXIT("게임 종료")
    ,FINAL_EXIT("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
    ,RESTART("게임이 재시작 됐습니다")
    , RESTART_MESSAGE("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

    final String value;

    GameResult(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
