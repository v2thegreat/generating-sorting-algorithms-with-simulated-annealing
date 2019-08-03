from itertools import permutations
from sys import argv
from tqdm import tqdm
from math import factorial
from os import listdir, remove

def dump(l, n, f):
	for x in l:
		for i in x:
			if x[-1]  == i:
				f.write(str(i) + "\n")
			else:
				f.write(str(i) + ",")
	f.flush()

if '-n' not in argv:
	n = int(input('Enter the total size of permutation array: '))
else:
	n = int(argv[2])

if "Permutations of {}.csv".format(n) in listdir():
	if input("Delete Previous Permutations of {}? (Y/n)".format(n)).lower() != "n":
		remove("Permutations of {}.csv".format(n))

f=open("Permutations of {}.csv".format(n), "a")

l=[]
for x in tqdm(permutations(range(n)), total = factorial(n)):
	l.append(tuple(x))
	if len(l) == 4325:
		dump(l, n, f)
		l=[]

dump(l,n, f)

f.close()

print('Finished my purpose, going to die now')
