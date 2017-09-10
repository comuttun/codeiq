import sys

class Logic:
    def fibonacci(self, n, a=1, b=0):
        if n == 0:
            return 0
        elif n == 1:
            return a
        else:
            return self.fibonacci(n - 1, a + b, a)

    def solve(self, floor_number):
        solved = self.fibonacci(floor_number)
        if solved < 16:
            return solved
        else:
            return solved % 16


class LogicTest:
    def __init__(self):
        self._sut = Logic()

    def run_test(self):
        assert self._sut.solve(4) == 3, "solve(4) must return 3"
        assert self._sut.solve(5) == 5, "solve(5) must return 5"
        assert self._sut.solve(6) == 8, "solve(6) must return 8"
        assert self._sut.solve(8) == 5, "solve(8) must return 5"


def main():
    LogicTest().run_test()

    logic = Logic()
    for line in iter(sys.stdin.readline, ''):
        print(logic.solve(int(line)))

    return 0


if __name__ == '__main__':
    sys.exit(main())