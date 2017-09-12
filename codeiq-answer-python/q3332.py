import sys


class Logic:
    MOD16_VALUES = [5, 2, 7, 9, 0, 9, 9, 2, 11, 13, 8, 5, 13, 2, 15, 1, 0, 1, 1, 2, 3, 5, 8, 13]

    def fibonacci_mod16(self, n):
        if n == 0:
            return 0
        elif n == 1 or n == 2:
            return 1
        elif n < 8:
            a = 1
            b = 0
            for i in range(3, n + 2):
                x = (a + b) % 16
                y = a % 16
                a = x
                b = y

            return x
        else:
            index = (n - 8) % len(self.MOD16_VALUES)
            return self.MOD16_VALUES[index]

    def solve(self, floor_number):
        return self.fibonacci_mod16(floor_number)


class LogicTest:
    def __init__(self):
        self._sut = Logic()

    def run_test(self):
        assert self._sut.solve(4) == 3, "solve(4) must return 3 but %d" % self._sut.solve(4)
        assert self._sut.solve(5) == 5, "solve(5) must return 5 but %d" % self._sut.solve(5)
        assert self._sut.solve(6) == 8, "solve(6) must return 8 but %d" % self._sut.solve(6)
        assert self._sut.solve(8) == 5, "solve(8) must return 5 but %d" % self._sut.solve(8)


def main():
    LogicTest().run_test()

    logic = Logic()
    # print(', '.join([str(logic.solve(x)) for x in range(1, 50)]))

    for line in iter(sys.stdin.readline, ''):
        print(logic.solve(int(line)))

    return 0


if __name__ == '__main__':
    sys.exit(main())