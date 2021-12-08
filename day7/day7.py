from statistics import median, mean

input = [int(i) for i in open('input.txt').read().strip().split(',')]
low, high = min(input), max(input)

# part 1
#target = int(median(input))
#fuel = sum([abs(target - pos) for pos in input])
#print('Part #1:', fuel)

# part 2
# average minimizes to distance ^ 2 but we have distance * (distance + 1)
# so we need to try target and target + 1
target = int(mean(input))
fuel = int(min([
    sum(abs(t - pos) * (abs(t - pos) + 1) / 2 for pos in input)
    for t in [target, target + 1]
]))

print('Part #2:', fuel)