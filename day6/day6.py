# In python was simple xd

from collections import Counter
 
def solve_part_1(timers, days):
    counts = Counter(timers)
    for _ in range(days):
        six = 0
        eight = 0
        for i in range(9):
            if counts[i] == 0:
                continue
            if i == 0:
                six += counts[i]
                eight += counts[i]
            else:
                counts[i - 1] += counts[i]
            counts[i] = 0
        counts[6] += six
        counts[8] += eight
    return sum(counts.values())
 
def solve_part_2(timers, days):
    return solve_part_1(timers, days)
 
if __name__ == '__main__':
    with open('input.txt') as f:
        timers = [int(x) for x in f.readline().split(',')]
        print(solve_part_1(timers, 80))
        print(solve_part_2(timers, 256))