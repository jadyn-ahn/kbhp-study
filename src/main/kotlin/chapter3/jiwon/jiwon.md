## chap3
#### 최대공약수
- 최대공약수 구하는 방법 -> [유클리드 호제법](https://ko.wikipedia.org/wiki/%EC%9C%A0%ED%81%B4%EB%A6%AC%EB%93%9C_%ED%98%B8%EC%A0%9C%EB%B2%95) 사용해봄

### 꼬리재귀
- 재귀 호출이 끝나면 다른 연산 없이 바로 결과만 반환한다
- 일반적인 재귀함수는 어떤 값을 받았을 때, 값에 연산을 하고 다음 함수에게 전달하는 반면 꼬리재귀는 연산을 하지 않고 반환
- 꼬리재귀를 사용할 경우, 현재 스택 프레임을 유지하면서 새로운 함수를 호출하는 방식으로 최적화한다
- tail call optimization (tco) 를 지원하는 언어인 경우 스택을 쌓지 않고 반복문 형태로 최적화 
- tco 를 지원하지 않는 언어인 경우 일반적인 재귀 함수처럼 스택에 계속해서 쌓인다
- javascript es6 에서도 tco 지원하지만 브라우저에서 지원하지 않는 경우가 많다고 함 

#### 모든 재귀는 꼬리 재귀로 변환할 수 있을까?
- gpt 에 의하면 그렇지 않다
- 이진 트리에서 중위 순회를 하는 경우, 왼쪽 자식을 방문 -> 현재 노드 -> 오른쪽 자식을 차례로 방문하기 때문에 아래와 같은 형태가 된다
- 재귀 함수가 마지막에 호출되는 것이 아니라, 결합 (연산) 하는 작업이 필해 꼬리 재귀로 만들 수 없다
```python
# gpt 가 알려준 코드 
class Node:
    def __init__(self, value, left=None, right=None):
        self.value = value
        self.left = left
        self.right = right

def inorder_traversal(node):
    if node is None:
        return []
    
    # 중위 순회: 왼쪽 자식 -> 현재 노드 -> 오른쪽 자식
    return inorder_traversal(node.left) + [node.value] + inorder_traversal(node.right)
```

