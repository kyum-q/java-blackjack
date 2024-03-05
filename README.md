# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

<br>

![블랙잭 카드](https://www.7luck.com/common/images/contents/img_blackjack3.jpg)

<br>
<hr>

## 기능 요구 사항

### 입력
- [ ] 게임에 참여할 사람의 이름을 쉼표 기준으로 분리해서 입력한다. 
- [ ] 카드 추가 여부를 입력 받는다.

### 사람들
- [x] 사람 리스트를 가지고 있다.
- [x] 딜러가 1명 초과면 예외가 발생한다.
- [x] 딜러가 1명 미만이면 예외가 발생한다.

### 사람
- [x] 이름을 가진다.
- [x] 카드 리스트를 가지고 있다.
- [x] 카드를 추가할 수 있다.
- [x] 여러장의 카드를 추가할 수 있다.
- [x] 카드의 합을 계산한다.
  - [x] Ace가 나왔을 때는 21을 초과하지 않으며 21과 가까운 수가 되도록 11이나 1을 고른다.
- [x] 딜러인지 아닌지 여부를 알 수 있다.

### 참가자
- [x] 카드의 합이 21이하인지 알린다.

### 딜러
- [x] 카드가 16이하인지 알린다.

### 블랙잭
- [x] 플레이어들을 가진다.
- [x] 참가자에게 카드를 준다.
- [x] 카드의 합이 21을 초과하면 패한다.
- [x] 참가자와 딜러의 카드의 합이 21에 가까운지 확인하고 승패를 결정한다.

### 결과
- [x] 참가자와 딜러의 승패를 저장한다.

### 승패(enum)

### 카드들
- [x] 모든 카드 리스트를 가지고 있다.
- [x] 램덤하게 카드를 선택하고 선택된 카드는 삭제된다.

### 카드
- [x] 카드 모양과 숫자를 가진다.
- [x] 같은 카드의 정의는 카드 모양과 숫자가 같은 경우이다.

### 카드 모양(enum)
- [x] 스페이스, 클로버, 하트, 다이아를 가지고 있다.

### 숫자 (enum)
- [x] 2~9, Ace, King, Queen, Jack을 가지고 있다.
- [x] Ace는 1또는 11을 계산할 수 있다.



### 예시
```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승
jason: 패
```
