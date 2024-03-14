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
- [x] 게임에 참여할 사람의 이름을 쉼표 기준으로 분리해서 입력한다.
- [x] 배팅 금액을 입력 받는다.
- [x] 카드 추가 여부를 입력 받는다.
  - [x] y, n 가 아니면 예외를 발생시킨다.

### 블랙잭
- [x] 참가자들과 딜러를 가진다.
  - [x] 참가자와 딜러는 null이면 안된다.
- [x] 참가자와 딜러에게 카드를 준다.
  - [x] 일부 참가자에게만 카드를 줄 수 있다.
- [x] 참가자와 딜러의 카드의 합이 21에 가까운지 확인하고 승패를 결정한다.
  - [x] 합이 서로 같을 때 무승부다.
  - [x] 플레이어의 카드의 합이 21을 초과하면 패다.

### 카드덱
- [x] 덱이 비어있는 경우 예외가 발생한다.
- [x] 모든 카드 리스트를 가지고 있다.
- [x] 램덤하게 카드를 선택하고 선택된 카드는 삭제된다.

### 사람
- [x] 이름을 가지고 있다.
  - [x] 빈 문자열, 공백이나 null이면 예외를 발생시킨다.

### 참가자들
- [x] 참가자 리스트를 가지고 있다.
  - [x] 이름이 중복되는 경우 예외가 발생시킨다.
  - [x] 참가자의 수는 2~8명이어야 한다.
- [x] 참가자에게 카드를 준다.

### 참가자
- [x] 카드의 합이 21이하인지 알린다.
- [x] 게임 머니를 가지고 있다.
- [ ] 승패와 블랙잭 상태로 수익을 알아낸다.
- 
### 딜러
- [x] 카드가 16이하인지 알린다.

### 승패(enum)
- [x] 승패무를 저장한다.
- [x] 승패무를 전환한다.

### 카드들
- [x] 카드 리스트를 가지고 있다.
  - [x] 블랙잭 게임을 진행할 때, 플레이어는 최소 2장의 카드를 가지고 있어야 한다.
- [x] 여러 장의 카드를 추가할 수 있다.
- [x] 카드의 합을 계산한다.
  - [x] Ace가 나왔을 때는 21을 초과하지 않으며 21과 가까운 수가 되도록 11이나 1을 고른다.
- [x] 카드의 합이 21을 넘는지 알린다. (hit 할 수 있는지)
- [x] 카드의 합이 21을 넘지않는지 알린다. (hit 할 수 없는지)
- [x] 블랙잭 상태를 알 수 있다.

### 카드
- [x] 카드 모양과 숫자를 가진다.
- [x] 같은 카드의 정의는 카드 모양과 숫자가 같은 경우이다.
- [x] 카드의 최대 숫자와 최소 숫자를 알 수 있다.

### 카드 모양(enum)
- [x] 스페이스, 클로버, 하트, 다이아를 가지고 있다.

### 숫자 (enum)
- [x] 2~9, Ace, King, Queen, Jack을 가지고 있다.
- [x] Ace는 1또는 11을 가진다.

### 블랙잭 상태 (enum)
- 스탠드(Stand)
- 버스트(Bust) 
- 블랙잭(Blackjack)
- [x] 카드의 합과 카드의 수를 통해 블랙잭 상태를 알 수 있다.
  - [x] 스탠드: 카드의 합이 21을 넘지 않은 경우 (버스트와 블랙잭이 아닌 경우)
  - [x] 버스트: 카드의 총합이 21을 넘는 경우
  - [x] 블랙잭: 2장의 합이 21인 경우 (A 한장과 10에 해당하는 패(10,J,Q,K)로 21을 이루는 경우)

### 게임 머니
- [x] 배팅 금액을 가지고 있다.
  - [x] 배팅 금액은 0보다 커야 한다.
- [x] 카드 상태와 승패에 따라 최종 수익을 알 수 있다.
  - [x] 블랙잭, 승 : 1.5배
  - [x] 승 : 1배
  - [x] 패 : -1배
  - [x] 무 : 0배

<br>

### 예시
```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi의 배팅 금액은?
10000

jason의 배팅 금액은?
20000

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
pobi카드: 2하트, 8스페이드, A클로버
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```
