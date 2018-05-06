# Text-Data-Analysis

## 기본 개념

### Language Model : 통계적인 방법(확률, 빈도수) , 모든 단어들의 가능성에 대해서 확률이 높은 sequence를 선택( 문장의 발생 확률이 높은 경우)
 
### Markov Assumption : 현재 단어는 바로 전 단어에 의해 영향을 받는다 라는 이론(last few words), 전 단어를 보고 다음 단어를 예측( 예를들면 음성인식의 에러가 많은데 그걸 극복하기 위해 다음 단어의 후보들 중에서 가장 가능성 많은 단어를 추측해서 에러를 줄임)
 
### N-gram Language Model : unigram, bigram, trigram

### Word prediction algorithm : 첫 2~3 어절이 주어졌을 때 다음 어절로 올 가능성이 가장 높은 어절을 생성
 
### Smoothing : sparseness problem 문제를 해결을 위해서 / 확률이 0이 되지 않도록 하는 방법
 - Back-off smoothing : trigram 확률 값을 구한 값이 0인 경우 bigram으로 높은 확률을 구해서 0을 만들지 않는다. 또한 bigram도 0이 나온다면 unigram 확률로 낮춰서 구함 (이때 알파를 적절한 값으로 곱한다.)
 
 - Linear Interpolation : 기본 베이스 값도 반영 ex) trigram 확률 구할때 unigram 확률, bigram 확률 각각 비율을 정해서 반영
 => 확률 0인 값이 나오지 않게 하기 위해서
 
 - Maximum Entropy : 전체 분포 h 중에서 wi가 차지할 비중이 얼마나 될까를 구하는 방식

```
• Chain rule of probability P(your cell phone is good) =
P(your) * P(cell | your) * P(phone | your cell) * P(is | your cell phone)
* P(good | your cell phone is)
```
