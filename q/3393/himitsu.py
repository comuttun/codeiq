import sys

def make_himitsu_mark(size):
    if size % 2 == 0:
        return 'invalid'

    if size == 1:
        return 'h'

    mark = []
    for i in range(0, size):
        mark.append(size*[None])

        for j in range(0, size):
            if j == 0:
                mark[i][j] = 'h'
            elif j == size - 1:
                mark[i][j] = 'h'
            elif i == int(size / 2):
                mark[i][j] = 'h'
            else:
                mark[i][j] = '.'

    return '\n'.join([''.join(x) for x in mark])


def main():
    size = int(sys.stdin.readline().rstrip())
    print(make_himitsu_mark(size))
    return 0


if __name__ == '__main__':
    sys.exit(main())
